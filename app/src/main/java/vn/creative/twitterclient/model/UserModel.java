package vn.creative.twitterclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class UserModel {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("screen_name")
    private String screenName;

    @SerializedName("profile_image_url")
    private String avatar;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
