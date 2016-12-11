package fernanda.heartthisapp.model;

import java.util.List;

import retrofit2.http.PUT;

/**
 * Created by Fernanda on 07/12/2016.
 */

public class User {

    public String username;
    public String permalink;
    public String avatar_url;
    public String stream_url;

    public String getPermalink() {
        return permalink;
    }

    public String getStream_url() {
        return stream_url;
    }

    List<Track> trackList;

    public User(String username, String avatar_url) {
        this.username = username;
        this.avatar_url = avatar_url;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
