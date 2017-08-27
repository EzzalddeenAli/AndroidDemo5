package com.example.lzl.a40demo;

/**
 * Created by lzl on 16/8/24.
 */
public class T {
    private static T ourInstance = new T();

    public static T getInstance() {
        return ourInstance;
    }

    private T() {
    }
}
