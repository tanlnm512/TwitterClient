package vn.creative.twitterclient.view.timeline;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public interface ITimelineInteractor {
    void getHomeTimeline(long id, IFetchTimelineListener listener);
}
