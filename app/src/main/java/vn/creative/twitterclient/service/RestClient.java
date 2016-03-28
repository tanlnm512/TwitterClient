package vn.creative.twitterclient.service;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

    public void getHomeTimeline(long sinceId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("/statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", 40);
        params.put("since_id", sinceId);
        getClient().get(apiUrl, params, handler);
    }

    public void replyTweet(String tweet, long tweetId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("/statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", tweet);
        params.put("in_reply_to_status_id", tweetId);
        getClient().post(apiUrl, params, handler);
    }

    public void tweet(String tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("/statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", tweet);
        getClient().post(apiUrl, params, handler);
    }

    public void getUserInfo(AsyncHttpResponseHandler handler){
        String apiUrl = getApiUrl("/account/verify_credentials.json");
        RequestParams params = new RequestParams();
        params.put("skip_status", true);
        params.put("include_entities", false);
        getClient().get(apiUrl, params, handler);
    }
}
