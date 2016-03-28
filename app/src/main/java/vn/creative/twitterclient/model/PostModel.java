package vn.creative.twitterclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class PostModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdAt);
        dest.writeLong(this.id);
        dest.writeString(this.text);
        dest.writeParcelable(this.entities, flags);
        dest.writeParcelable(this.user, flags);
    }

    public PostModel() {
    }

    protected PostModel(Parcel in) {
        this.createdAt = in.readString();
        this.id = in.readLong();
        this.text = in.readString();
        this.entities = in.readParcelable(EntitiesModel.class.getClassLoader());
        this.user = in.readParcelable(UserModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<PostModel> CREATOR = new Parcelable.Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel source) {
            return new PostModel(source);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };
}
