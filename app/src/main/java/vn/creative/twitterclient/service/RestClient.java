package vn.creative.twitterclient.service;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by tanlnm on 3/24/2016.
 */
public class RestClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "ecpLGehzD2io5tKtzvs6hYHWK";
    public static final String REST_CONSUMER_SECRET = "80P5NBofX8gGy7qJTrT2Jxc4gSTPWDkomXUK7P631bHyfA589Y";
    public static final String REST_CALLBACK_URL = "oauth://codepathtweets";

    public RestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
}
