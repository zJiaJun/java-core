package com.github.zjiajun.java.core.java8;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by zhujiajun
 * 16/1/15 22:09
 */
public class ClockExample {

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println(date);

        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Asia/Chongqing");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

    }
}
