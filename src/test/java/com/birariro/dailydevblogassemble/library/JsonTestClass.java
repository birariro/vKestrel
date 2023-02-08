package com.birariro.dailydevblogassemble.library;


public class JsonTestClass {
    String name;
    String url;

    @Override
    public String toString() {
        return "JsonTestClass{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
