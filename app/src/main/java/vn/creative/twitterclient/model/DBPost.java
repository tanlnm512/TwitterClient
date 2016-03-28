package vn.creative.twitterclient.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by minhtan512 on 3/28/2016.
 */
@Table(name = "posts")
public class DBPost extends Model {
    @Column(name = "post_json")
    private String postJson;

    public DBPost() {
    }

    public DBPost(String postJson) {
        this.postJson = postJson;
    }

    public String getPostJson() {
        return postJson;
    }

    public static List<DBPost> getPosts() {
        return new Select().from(DBPost.class).execute();
    }


}
