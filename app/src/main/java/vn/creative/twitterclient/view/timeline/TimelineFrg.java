package vn.creative.twitterclient.view.timeline;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterclient.R;
import vn.creative.twitterclient.adapter.TimelineAdapter;
import vn.creative.twitterclient.common.NetworkUtils;
import vn.creative.twitterclient.model.DBPost;
import vn.creative.twitterclient.model.PostModel;
import vn.creative.twitterclient.view.tweet.TweetDlg;

/**
 * Created by tanlnm on 3/25/2016.
 */
public class TimelineFrg extends Fragment implements ITimelineView, ITimelineActionListener, DialogInterface.OnDismissListener {
    private static final String TAG = TimelineFrg.class.getSimpleName();

    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.rv_timeline)
    RecyclerView rvTimeline;

    private long nCurID = 1;

    private ActionBar actionBar;
    private TimelinePresenter timelinePresenter;
    private TimelineAdapter timelineAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);
        ButterKnife.bind(this, view);
        timelinePresenter = new TimelinePresenter(this);

        setHasOptionsMenu(true);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.mipmap.twitter_white);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        // Configure the refreshing colors
        swipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkUtils.isNetworkAvailable(getContext())) {
                    nCurID = 1;
                    timelinePresenter.fetchTimeline(nCurID);

                } else {
                    swipeLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTimeline.setLayoutManager(linearLayoutManager);
        timelineAdapter = new TimelineAdapter(getContext(), this);
        rvTimeline.setAdapter(timelineAdapter);

        rvTimeline.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (NetworkUtils.isNetworkAvailable(getContext())) {
                    timelinePresenter.fetchTimeline(nCurID);

                } else {
                    Toast.makeText(getContext(), "Network error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // First time load data
        if (NetworkUtils.isNetworkAvailable(getContext())) {
            timelinePresenter.fetchTimeline(nCurID);

        } else {
            List<PostModel> posts = new ArrayList<>();
            for (DBPost dbPost : DBPost.getPosts()) {
                PostModel post = new Gson().fromJson(dbPost.getPostJson(), PostModel.class);
                if (post != null) {
                    posts.add(post);
                }
            }
            onFetchTimelineSuccess(posts);
        }

        return view;
    }

    @Override
    public void onFetchTimelineSuccess(List<PostModel> posts) {
        swipeLayout.setRefreshing(false);

        if (nCurID == 1) {
            timelineAdapter.update(posts);

        } else {
            timelineAdapter.updateMore(posts);
        }

        if (!posts.isEmpty()) {
            nCurID = posts.get(posts.size() - 1).getId();
        }
    }

    @Override
    public void onFetchTimelineFail() {
        swipeLayout.setRefreshing(false);
        Toast.makeText(getContext(), "Get Twitter timeline fail!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(PostModel post) {
        String url = "https://twitter.com/" + post.getUser().getScreenName() + "/status/" + post.getId();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onReplyClick(PostModel post) {
        TweetDlg tweetDlg = new TweetDlg();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", post);
        tweetDlg.setArguments(bundle);
        tweetDlg.show(getFragmentManager(), "TweetDlg");
    }

    @Override
    public void onRetweetClick(PostModel post) {

    }

    @Override
    public void onLikeClick(PostModel post) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.compose_tweet:
                TweetDlg tweetDlg = new TweetDlg();
                tweetDlg.show(getFragmentManager(), "TweetDlg");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (NetworkUtils.isNetworkAvailable(getContext())) {
            nCurID = 1;
            timelinePresenter.fetchTimeline(nCurID);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}