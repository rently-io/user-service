package io.rently.userservice.utils;

import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

public class Broadcaster {
    private static final String PREFIX = "[USER SERVICE]";

    public static void debug(Object obj) {
        System.out.println(defaultFormat("[DEBUG]") + obj);
    }

    public static void info(Object obj) {
        System.out.println(defaultFormat("[INFO]") + obj);
    }

    public static void warn(Object obj) {
        System.out.println(defaultFormat("[WARN]") + obj);
    }

    public static void error(Exception ex) {
        System.out.println(defaultFormat("[ERROR]") + ex.getMessage());
    }

    public static void httpError(ResponseStatusException ex) {
        System.out.println(defaultFormat("[HTTP ERROR]") + "[" + ex.getStatus() + "] " + ex.getReason());
    }

    private static String defaultFormat(String type) {
        return String.format("%-24s ", new Timestamp(System.currentTimeMillis())) + PREFIX + String.format(" %17s ", type);
    }
}
