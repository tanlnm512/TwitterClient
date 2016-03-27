package vn.creative.twitterclient.view.timeline;

import java.util.List;

import vn.creative.twitterclient.model.PostModel;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public interface ITimelineView {
    void onFetchTimelineSuccess(List<PostModel> posts);

    void onFetchTimelineFail();
}
