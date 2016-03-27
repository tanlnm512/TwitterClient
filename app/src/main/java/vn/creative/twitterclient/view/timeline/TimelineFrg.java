package vn.creative.twitterclient.view.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.creative.twitterclient.R;
import vn.creative.twitterclient.model.PostModel;

/**
 * Created by tanlnm on 3/25/2016.
 */
public class TimelineFrg extends Fragment implements ITimelineView {
    private static final String TAG = TimelineFrg.class.getSimpleName();

    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private long nCurID = 1;

    private ActionBar actionBar;
    private TimelinePresenter timelinePresenter;

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
                nCurID = 1;
                timelinePresenter.fetchTimeline(nCurID);
            }
        });

        timelinePresenter.fetchTimeline(nCurID);

        return view;
    }

    @Override
    public void onFetchTimelineSuccess(List<PostModel> posts) {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }

        for (PostModel post : posts) {
            System.out.println(post.toString());
        }
    }

    @Override
    public void onFetchTimelineFail() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        Toast.makeText(getContext(), "Get Twitter timeline fail!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}