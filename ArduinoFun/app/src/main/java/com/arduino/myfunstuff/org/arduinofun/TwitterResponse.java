package com.arduino.myfunstuff.org.arduinofun;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by minnera on 3/22/2016.
 */
public class TwitterResponse {
    @SerializedName("accounts")
    public List<TwitterPost> accounts;
}
