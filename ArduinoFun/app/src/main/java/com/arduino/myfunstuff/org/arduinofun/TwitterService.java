package com.arduino.myfunstuff.org.arduinofun;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TwitterService extends Service {
    private static final String TAG = TwitterService.class.getSimpleName();

    public TwitterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        ITwitterApi twitterApi = TwitterApi.createTwitterApi();
        twitterApi.UserFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TwitterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "subscribe completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(TwitterResponse accounts) {
                        try {
                        } catch (Exception e) {
                            Log.d(TAG, e.getMessage());
                        }
                    }
                });
    }
}
