package com.arduino.myfunstuff.org.arduinofun;

import retrofit2.http.GET;

/**
 * Created by minnera on 3/22/2016.
 */
public interface ITwitterApi {
    @GET("/1.1/user.json")
    rx.Observable<TwitterResponse> UserFeed();
}
