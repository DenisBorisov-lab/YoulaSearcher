package com.example.youlasearcher;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.example.youlasearcher.models.Modes;

import java.util.HashSet;
import java.util.Set;

import lombok.SneakyThrows;

public class NotificationService extends Service {
    private Set<String> searching;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        searching = new HashSet<>();

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

        if (mode == Modes.CREATE) {
            if (searching.contains(name)) {
                searching.remove(name);
                Thread.sleep(1000);
                searching.add(name);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (searching.contains(name)) {
                            // TODO: 13.06.2022 Логика для отправки запросов
//                            System.out.println(name);
                        }
                        System.out.println("Цикл остановлен");
                    }
                }).start();
            } else {
                searching.add(name);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (searching.contains(name)) {
                            // TODO: 13.06.2022 Логика для отправки запросов
                            System.out.println(name);
                        }
                        System.out.println("Цикл остановлен");
                    }
                }).start();
            }

        } else {
            if (searching.contains(name)) {
                searching.remove(name);
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
}