package vn.creative.twitterclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vn.creative.twitterclient.R;
import vn.creative.twitterclient.common.RoundedTransformation;
import vn.creative.twitterclient.common.TimeUtils;
import vn.creative.twitterclient.model.PostModel;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<PostModel> posts;

    public TimelineAdapter(Context context) {
        mContext = context;
        posts = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View coverView = inflater.inflate(R.layout.item_post, viewGroup, false);
        viewHolder = new TimelineHolder(coverView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TimelineHolder timelineHolder = (TimelineHolder) holder;

        PostModel post = posts.get(position);
        Picasso.with(mContext)
                .load(post.getUser().getAvatar())
                .transform(new RoundedTransformation(20, 0))
                .resize(150, 150)
                .centerCrop()
                .tag(mContext)
                .into(timelineHolder.ivAvatar);
        timelineHolder.tvScreenName.setText(post.getUser().getScreenName());
        timelineHolder.tvName.setText("@" + post.getUser().getName());
        timelineHolder.tvTime.setText(TimeUtils.getRelativeTimeAgo(post.getCreatedAt()));

        if (!TextUtils.isEmpty(post.getText())) {
            timelineHolder.tvPost.setText(post.getText());
        } else {
            timelineHolder.tvPost.setVisibility(View.GONE);
        }

        if (post.getEntities().getMedia() != null && post.getEntities().getMedia().size() > 0) {
            Picasso.with(mContext)
                    .load(post.getEntities().getMedia().get(0).getUrl())
                    .transform(new RoundedTransformation(10, 0))
                    .tag(mContext)
                    .into(timelineHolder.ivPhoto);
        } else {
            timelineHolder.ivPhoto.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void update(List<PostModel> postList) {
        posts.clear();
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    public void updateMore(List<PostModel> postList) {
        int curSize = getItemCount();
        posts.addAll(postList);
        notifyItemRangeInserted(curSize, posts.size() - 1);
    }
}
