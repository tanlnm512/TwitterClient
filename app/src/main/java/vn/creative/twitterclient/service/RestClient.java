package vn.creative.twitterclient.service;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;

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

    public void getHomeTimeline(long id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("/statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("since_id", id);
        getClient().get(apiUrl, params, handler);
    }
}
