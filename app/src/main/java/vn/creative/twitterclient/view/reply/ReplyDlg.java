package vn.creative.twitterclient.view.reply;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.creative.twitterclient.R;
import vn.creative.twitterclient.TwitterApplication;
import vn.creative.twitterclient.common.RoundedTransformation;
import vn.creative.twitterclient.model.PostModel;

/**
 * Created by tanlnm on 3/28/2016.
 */
public class ReplyDlg extends DialogFragment {
    @Bind(R.id.dlg_tv_screen_name)
    TextView tvScreenName;

    @Bind(R.id.dlg_tv_name)
    TextView tvName;

    @Bind(R.id.dlg_tv_char_count)
    TextView tvCharCount;

    @Bind(R.id.dlg_et_tweet)
    EditText etTweet;

    @Bind(R.id.dlg_iv_avatar)
    ImageView ivAvatar;

    @Bind(R.id.dlg_btn_tweet)
    Button btnTweet;

    private PostModel post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reply, null);
        ButterKnife.bind(this, view);
        setCancelable(false);
        btnTweet.setEnabled(false);

        view.findViewById(R.id.dlg_iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        etTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charCount = 140 - s.length();
                tvCharCount.setText(String.valueOf(charCount));

                if (charCount > 140) {
                    btnTweet.setEnabled(false);
                } else {
                    btnTweet.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        post = (PostModel) getArguments().get("post");
        if (post != null) {
            Picasso.with(getContext())
                    .load(post.getUser().getAvatar())
                    .transform(new RoundedTransformation(20, 0))
                    .resize(150, 150)
                    .centerCrop()
                    .tag(getContext())
                    .into(ivAvatar);

            tvName.setText(post.getUser().getName());
            tvScreenName.setText("@" + post.getUser().getScreenName());

            etTweet.setText("@" + post.getUser().getScreenName());
        }

        return view;
    }

    @OnClick(R.id.dlg_iv_close)
    void close() {
        dismiss();
    }

    @OnClick(R.id.dlg_btn_tweet)
    void tweet() {
        TwitterApplication.getRestClient().replyTweet(etTweet.getText().toString(), post.getId(), new JsonHttpResponseHandler());
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
