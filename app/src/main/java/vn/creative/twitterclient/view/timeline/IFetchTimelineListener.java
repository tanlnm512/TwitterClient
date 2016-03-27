package vn.creative.twitterclient.view.timeline;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public interface IFetchTimelineListener {
    void onSuccess(JSONArray response);

    void onFail(JSONObject error, Throwable throwable);
}
