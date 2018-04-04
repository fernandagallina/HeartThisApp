package fernanda.heartthisapp.presenter;

import java.util.List;

import fernanda.heartthisapp.TrackRecyclerViewAdapter;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.Track;

public interface TrackContract {

    interface View {

        void showTracks(List<Track> tracks);

        void showError(String message);

        void playTrack(String stream_url);
    }

    interface UserActionListener extends TrackRecyclerViewAdapter.TrackItemListener {

        void loadTrack();

        void setArtist(Artist artist);
    }
}
