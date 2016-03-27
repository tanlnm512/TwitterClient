package vn.creative.twitterclient;

import android.content.Context;

import com.activeandroid.app.Application;

import vn.creative.twitterclient.service.RestClient;

/**
 * Created by tanlnm on 3/24/2016.
 */
public class TwitterApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static RestClient getRestClient() {
        return (RestClient) RestClient.getInstance(RestClient.class, TwitterApplication.mContext);
    }
}
