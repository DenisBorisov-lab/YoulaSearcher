package com.denisbrisov.youlasearcher.services;

public class PeriodTimeService {
    public static long getMilliseconds(String period) {
        long time = 0L;
        switch (period) {
            case "Каждую минуту":
                time = 60 * 1000;
                break;
            case "Каждые 3 минуты":
                time = 3 * 60 * 1000;
                break;
            case "Каждые 5 минут":
                time = 5 * 60 * 1000;
                break;
            case "Каждые 10 минут":
                time = 10 * 60 * 1000;
                break;
            case "Каждые 15 минут":
                time = 15 * 60 * 1000;
                break;
            case "Каждые 30 минут":
                time = 30 * 60 * 1000;
                break;
            case "Каждый час":
                time = 3600 * 1000;
                break;
            case "Каждые 2 часа":
                time = 2 * 3600 * 1000;
                break;
            case "Каждые 4 часа":
                time = 4 * 3600 * 1000;
                break;
            case "Каждые 6 часов":
                time = 6 * 3600 * 1000;
                break;
            case "Каждые 12 часов":
                time = 12 * 3600 * 1000;
                break;
        }
        return time;
    }
}
