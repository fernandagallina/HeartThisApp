package fernanda.heartthisapp.ui;

import java.util.List;

import fernanda.heartthisapp.model.Track;

/**
 * Created by Fernanda on 08/12/2016.
 */

public interface TrackView {

    void showTracks(List<Track> tracks);

    void showError(String message);
}
