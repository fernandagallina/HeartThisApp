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

    public User(String username, String avatar_url) {
        this.username = username;
        this.avatar_url = avatar_url;
    }
}
