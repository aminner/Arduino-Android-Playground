package com.arduino.myfunstuff.org.arduinofun;

public class TwitterStreamConsumer extends Thread {

    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";

    public void run(){
        System.out.println("Starting Twitter public stream consumer thread.");
    }
}