package com.arduino.myfunstuff.org.arduinofun;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by minnera on 3/22/2016.
 */
public class TwitterApi{
    private static String key = "OAuth oauth_consumer_key=\"LT0uDEW3zPYDb0mJX9oGv279p\", oauth_nonce=\"1b1dde3aa2a96b08efa0031800f63544\", oauth_signature=\"YMrPUn3HkzcETVBphveH%2B%2BC7c6U%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1458674182\", oauth_token=\"2875106237-pRoCaQWPrejyAGWNwqIKgqeVl92gaFZ9xXqiN4b\", oauth_version=\"1.0\"";
    private static String baseUrl = "https://userstream.twitter.com/";

    public TwitterApi(){}

    public static ITwitterApi createTwitterApi() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newReq = request.newBuilder()
                        .addHeader("Authorization", key)
                        .method(request.method(), request.body())
                        .build();
                return chain.proceed(newReq);
            }
        }).build();
        builder.client(client);
        return builder.build().create(ITwitterApi.class);
    }

}
