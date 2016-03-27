package vn.creative.twitterclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class PostModel {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private long id;

    @SerializedName("text")
    private String text;

    @SerializedName("entities")
    private EntitiesModel entities;

    @SerializedName("user")
    private UserModel user;

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public EntitiesModel getEntities() {
        return entities;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "createdAt='" + createdAt + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", entities=" + entities +
                ", user=" + user +
                '}';
    }
}
