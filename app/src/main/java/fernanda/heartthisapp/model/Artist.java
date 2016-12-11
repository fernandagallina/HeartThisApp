package fernanda.heartthisapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fernanda on 06/12/2016.
 */

public class Artist {

    String track_count;
    String followers_count;

    public void setTrack_count(String track_count) {
        this.track_count = track_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }

    public String getTrack_count() {
        return track_count;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    User user;

    public Artist() {
    }

    public User getUser() {
        return user;
    }
 }
