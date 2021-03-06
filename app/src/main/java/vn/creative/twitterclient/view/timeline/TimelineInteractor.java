package vn.creative.twitterclient.view.timeline;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.creative.twitterclient.TwitterApplication;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class TimelineInteractor implements ITimelineInteractor {
    @Override
    public void getHomeTimeline(long id, final IFetchTimelineListener listener) {
        TwitterApplication.getRestClient().getHomeTimeline(id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                listener.onSuccess(response);
//                try {
//                    for(int i = 0; i < response.length(); i++) {
//                        System.out.println(">>> " + response.getJSONObject(i).toString());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFail(errorResponse, throwable);
            }
        });
    }
}
