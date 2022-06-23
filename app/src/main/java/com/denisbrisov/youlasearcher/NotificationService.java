package com.denisbrisov.youlasearcher;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.denisbrisov.youlasearcher.models.Modes;
import com.denisbrisov.youlasearcher.models.request.Request;
import com.denisbrisov.youlasearcher.models.response.Item;
import com.denisbrisov.youlasearcher.models.response.YoulaResponse;
import com.denisbrisov.youlasearcher.services.DataService;
import com.denisbrisov.youlasearcher.services.GetService;
import com.denisbrisov.youlasearcher.services.GetServiceImpl;
import com.denisbrisov.youlasearcher.services.PeriodTimeService;
import com.denisbrisov.youlasearcher.services.PostService;
import com.denisbrisov.youlasearcher.services.PostServiceImpl;
import com.denisbrisov.youlasearcher.services.TimeParseService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import lombok.SneakyThrows;

public class NotificationService extends Service {
    private static final String CHANNEL_ID = "youla";
    private Set<String> searching;
    private NotificationManager notificationManager;
    private int NOTIFY_ID = 1;
    private SharedPreferences sharedPreferences;
    private boolean vibration;
    private boolean wifiSearching;
    private GetService getService;

    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getService = new GetServiceImpl();
        searching = new HashSet<>();
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        vibration = sharedPreferences.getBoolean("vibration", true);
        wifiSearching = sharedPreferences.getBoolean("wifi_searching", false);

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_search)
                .setContentTitle("Запущена фоновая работа приложения")
                .setContentText("Выполняются запросы на сервер")
                .setContentIntent(pendingIntent)
                .build();

        createChannelIfNeeded(notificationManager);

        startForeground(1337, notification);

    }

    @Override
    @SneakyThrows
    public int onStartCommand(Intent intent, int flags, int startId) {


        Bundle extras = intent.getExtras();
        Modes mode = (Modes) extras.get("mode");
        String name = extras.getString("name");
        String url = extras.getString("url");
        String workTime = extras.getString("workTime");
        String time = extras.getString("time");
        String id = extras.getString("id");
        String active = extras.getString("active");
        String[] array = url.split("\\?");
        String attributes = "";
        String domain = array[0];
        if (array.length > 1) {
            attributes = array[1];
        }
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        String location = settings.getString("location", null);
        if (mode == Modes.CREATE) {
            String finalAttributes = attributes;
            searching.add(id);

            new Thread(() -> {
                String params = finalAttributes;
                String finalName = name;
                String finalId = id;
                String finalActive = active;
                long period = PeriodTimeService.getMilliseconds(time);
                String finalDomain = domain;
                String finalWokTime = workTime;

                while (searching.contains(finalId) && finalActive.equals("true")) {
                    System.out.println("Цикл запущен " + finalName);
                    try {
                        Thread.sleep(period);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DataService dataService = new DataService(finalDomain, params, location);
                    Request model = dataService.getModelRequest();
                    PostService postService = new PostServiceImpl(model);
                    boolean isConnected = isConnectedViaWifi();
                    if (TimeParseService.isAvailableForTime(finalWokTime) && (!wifiSearching || wifiSearching == isConnected)) {
                        String objects = postService.post();
                        YoulaResponse anotherObjects = dataService.getResultedObject(objects);
                        Item[] items = anotherObjects.getData().getFeed().getItems();
                        for (Item item : items) {
                            if (item != null) {
                                if (item.getProduct() != null) {
                                    String productId = item.getProduct().getID();
                                    long productTimePublished = getService.get(productId);
                                    if (TimeParseService.isNewProduct(productTimePublished, period) && searching.contains(finalId)) {
                                        sendNotification(item.getProduct().getName(), item.getProduct().getPrice().getRealPriceText(), item.getProduct().getImages()[0].getURL(), item.getProduct().getURL(), finalName);
                                    }
                                }
                            }
                        }
                    }

                    System.out.println("цикл остановлен");

                }
            }).start();

        } else {
            if (searching.contains(id)) {
                searching.remove(id);
                System.out.println("Пришёл запрос на остановку цикла " + name);
            } else {
                System.out.println("Такого поиска не было найдено " + name);
            }

        }

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void sendNotification(String title, String price, String url, String link, String group) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youla.ru" + link));
        PendingIntent contentIntent = PendingIntent.getActivity(NotificationService.this,
                0, browserIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setLargeIcon(getBitmapFromURl(url))
                .setSmallIcon(R.drawable.ic_search)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentText(price)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(PRIORITY_HIGH)
                .setGroup(group);
        if (vibration) {
            notificationBuilder.setVibrate(new long[]{1000, 1000});
        }

        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
        NOTIFY_ID++;
    }

    private boolean isConnectedViaWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    @SneakyThrows
    public Bitmap getBitmapFromURl(String link) {
        InputStream in;
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        in = connection.getInputStream();
        return BitmapFactory.decodeStream(in);
    }
}