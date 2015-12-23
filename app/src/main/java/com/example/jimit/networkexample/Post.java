package com.example.jimit.networkexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jimit on 24-12-2015.
 */
public class Post implements Parcelable {

    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(String body, int id, String title, int userId) {
        this.body = body;
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    protected Post(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
    }

    @Override
    public String toString() {
        return "Post{" +
                "body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
