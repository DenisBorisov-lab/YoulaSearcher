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
import com.denisbrisov.youlasearcher.services.PeriodTimeService;
import com.denisbrisov.youlasearcher.services.PostService;
import com.denisbrisov.youlasearcher.services.PostServiceImpl;
import com.denisbrisov.youlasearcher.services.TimeParseService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.SneakyThrows;

public class NotificationService extends Service {
    private static final String CHANNEL_ID = "youla";
    private Set<String> searching;
    private DataService dataService;
    private PostService postService;
    private NotificationManager notificationManager;
    private int NOTIFY_ID = 1;
    private SharedPreferences sharedPreferences;
    private boolean vibration;
    private boolean wifiSearching;
    private Set<String> oldIds;

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
        oldIds = new HashSet<>();
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
        dataService = new DataService(domain, attributes, location);
        Request model = dataService.getModelRequest();
        postService = new PostServiceImpl(model);
        if (mode == Modes.CREATE) {
            if (searching.contains(id)) {
                // TODO: 19.06.2022 изменить поиск и стартануть его заново
                searching.remove(id);
                try {
                    Thread.sleep(1000 / 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                searching.add(id);
                new Thread(() -> {
                    System.out.println("Цикл был изменён и запущен заново");
                    String stringObjects = postService.post();
                    YoulaResponse objects = dataService.getResultedObject(stringObjects);
                    Item[] items = objects.getData().getFeed().getItems();
                    for(Item item : items){
                        if (item != null && item.getProduct() != null){
                            oldIds.add(item.getProduct().getID());
                        }
                    }
                    long period = PeriodTimeService.getMilliseconds(time);
                    while (searching.contains(id) && active.equals("true")) {
                        boolean isConnected = isConnectedViaWifi();
                        if (TimeParseService.isAvailableForTime(workTime) && (!wifiSearching || wifiSearching == isConnected)) {
                            System.out.println("Цикл запущен" + name);
                            try {
                                Thread.sleep(period);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String anotherStringObjects = postService.post();
                            YoulaResponse anotherObjects = dataService.getResultedObject(anotherStringObjects);
                            Item[] anotherItems = anotherObjects.getData().getFeed().getItems();
                            for (Item item : anotherItems){
                                if (item != null && item.getProduct() != null){
                                    if(!oldIds.contains(item.getProduct().getID())){
                                        sendNotification(item.getProduct().getName(), item.getProduct().getPrice().getRealPriceText(), item.getProduct().getImages()[0].getURL(), item.getProduct().getURL());
                                        oldIds.add(item.getProduct().getID());
                                    }
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                }).start();

            } else {
                searching.add(id);
                new Thread(() -> {
                    String stringObjects = postService.post();
                    YoulaResponse objects = dataService.getResultedObject(stringObjects);
                    Item[] items = objects.getData().getFeed().getItems();
                    for(Item item : items){
                        if (item != null && item.getProduct() != null){
                            oldIds.add(item.getProduct().getID());
                        }
                    }
                    long period = PeriodTimeService.getMilliseconds(time);
                    while (searching.contains(id) && active.equals("true")) {
                        boolean isConnected = isConnectedViaWifi();
                        if (TimeParseService.isAvailableForTime(workTime) && (!wifiSearching || wifiSearching == isConnected)) {
                            System.out.println("Цикл запущен " + name);
                            try {
                                Thread.sleep(period);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String anotherStringObjects = postService.post();
                            YoulaResponse anotherObjects = dataService.getResultedObject(anotherStringObjects);
                            Item[] anotherItems = anotherObjects.getData().getFeed().getItems();
                            for (Item item : anotherItems){
                                if (item != null && item.getProduct() != null){
                                    if(!oldIds.contains(item.getProduct().getID())){
                                        sendNotification(item.getProduct().getName(), item.getProduct().getPrice().getRealPriceText(), item.getProduct().getImages()[0].getURL(), item.getProduct().getURL());
                                        oldIds.add(item.getProduct().getID());
                                    }
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Цикл остановлен");
                }).start();

            }

        } else {
            if (searching.contains(id)) {
                searching.remove(id);
                System.out.println("Пришёл запрос на остановку цикла");
            } else {
                System.out.println("Такого поиска не было найдено");
            }

        }

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public List<Item> getDifferentItems(Item[] first, Item[] second) {
        List<Item> itemsFirst = Arrays.asList(first);
        List<Item> itemsSecond = Arrays.asList(second);
        List<Item> result = new ArrayList<>();
        for (int i = 1; i < itemsSecond.size(); i++) {
            Item newItem = itemsSecond.get(i);
            if (newItem.getProduct() == null) {
                continue;
            }
            boolean isExist = false;
            for (int j = 1; j < itemsFirst.size(); j++) {
                Item oldItem = itemsFirst.get(j);
                if (oldItem.getProduct() == null) {
                    continue;
                }
                if (newItem.getProduct().getID().equals(oldItem.getProduct().getID())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                result.add(newItem);
            }
        }
        return result;
    }

    // TODO: 18.06.2022 поставить иконку приложения
    // TODO: 18.06.2022 установить рингтон
    public void sendNotification(String title, String price, String url, String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youla.ru" + link));
        PendingIntent contentIntent = PendingIntent.getActivity(NotificationService.this,
                0, browserIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setLargeIcon(getBitmapFromURl(url))
                .setSmallIcon(R.drawable.ic_search)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentText(price)
                .setContentIntent(contentIntent)
                .setPriority(PRIORITY_HIGH);
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