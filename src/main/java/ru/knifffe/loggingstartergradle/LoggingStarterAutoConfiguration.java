package ru.knifffe.loggingstartergradle;

public class LoggingStarterAutoConfiguration {
    public static void println(String message) {
        System.out.println("gradle: "+message);
    }
}
