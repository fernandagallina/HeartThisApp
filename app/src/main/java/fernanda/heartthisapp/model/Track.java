package fernanda.heartthisapp.model;

public class Track {

    public String duration;
    public String genre;
    public String stream_url;

    public String getDuration() {
        int time = Integer.parseInt(duration);
        int minutes = (time%3600)/60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public String title;
    public String artwork_url;
}
