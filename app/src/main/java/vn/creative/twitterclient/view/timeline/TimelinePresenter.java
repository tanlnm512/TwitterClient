package vn.creative.twitterclient.view.timeline;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import vn.creative.twitterclient.model.PostModel;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class TimelinePresenter implements ITimelinePresenter, IFetchTimelineListener {
    private ITimelineView timelineView;
    private TimelineInteractor timelineInteractor;

    public TimelinePresenter(ITimelineView timelineView) {
        this.timelineView = timelineView;
        timelineInteractor = new TimelineInteractor();
    }

    @Override
    public void fetchTimeline(long id) {
        timelineInteractor.getHomeTimeline(id, this);
    }

    @Override
    public void onSuccess(JSONArray response) {
        Type type = new TypeToken<List<PostModel>>() {
        }.getType();
        List<PostModel> posts = new Gson().fromJson(response.toString(), type);
        timelineView.onFetchTimelineSuccess(posts);
    }

    @Override
    public void onFail(JSONObject error, Throwable throwable) {
        timelineView.onFetchTimelineFail();
        Log.e("DEBUG", error.toString(), throwable);
    }
}
