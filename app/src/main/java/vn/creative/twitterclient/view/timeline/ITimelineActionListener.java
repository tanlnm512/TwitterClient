package vn.creative.twitterclient.view.timeline;

import vn.creative.twitterclient.model.PostModel;

/**
 * Created by tanlnm on 3/28/2016.
 */
public interface ITimelineActionListener {
    void onItemClick(PostModel post);
    void onReplyClick(PostModel post);
    void onRetweetClick(PostModel post);
    void onLikeClick(PostModel post);
}
