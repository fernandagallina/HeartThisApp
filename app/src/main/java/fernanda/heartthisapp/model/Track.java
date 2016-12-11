package fernanda.heartthisapp.model;

/**
 * Created by Fernanda on 06/12/2016.
 */

public class Track {

    public String description, duration;
    public String genre;
    public String stream_url;

    public String getDuration() {
        int time = Integer.parseInt(duration);
        int minutes = (time%3600)/60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public String getStream_url() {
        return stream_url;
    }

    public String title;
    public String artwork_url;
    User user;

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getArtwork_url() {
        return artwork_url;
    }

    public User getUser() {
        return user;
    }
}
