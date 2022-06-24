package com.denisbrisov.youlasearcher.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;

public class TimeParseService {
    @SneakyThrows
    public static boolean isAvailableForTime(String string) {
        if (string.equals("Круглосуточно")) {
            return true;
        }
        String[] elements = string.split(" ");
        String start = elements[1];
        String end = elements[3];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = simpleDateFormat.parse(start);
        Date endDate = simpleDateFormat.parse(end);
        Date currentTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        boolean a = currentTime.after(startDate);
        boolean b = currentTime.before(endDate);
        return a && b;
    }

    @SneakyThrows
    public static boolean isNewProduct(long unix, long period){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        Date productTimePublished = simpleDateFormat.parse(productTimePublishedString);
//        long startTime = System.currentTimeMillis() - period;
//        Date startDate = new Date(startTime);
//        boolean result = startDate.after(productTimePublished);


        long unixNow = System.currentTimeMillis();
        long difference = unixNow - unix;
        boolean result = difference <= period + 60000;
        return result;
    }
}
