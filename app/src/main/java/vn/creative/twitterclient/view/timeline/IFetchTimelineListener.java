package vn.creative.twitterclient.view.timeline;

import org.json.JSONObject;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public interface IFetchTimelineListener {
    void onSuccess(JSONObject response);

    void onFail(Throwable throwable);
}
