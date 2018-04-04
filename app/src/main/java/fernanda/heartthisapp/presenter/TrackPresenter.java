package fernanda.heartthisapp.presenter;

import java.util.List;

import javax.inject.Inject;

import fernanda.heartthisapp.DataLayer;
import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.Track;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 08/12/2016.
 */

public class TrackPresenter implements TrackContract.UserActionListener {

    private final TrackContract.View view;
    private final DataLayer dataLayer;

    private Artist artist;

    @Inject
    public TrackPresenter(TrackContract.View view, Retrofit retrofit) {
        this.view = view;
        dataLayer = new DataLayer(retrofit);
    }

    @Override
    public void loadTrack() {
        dataLayer.getTracksFromArtist(artist.user.permalink, "tracks", 1, 10)
                .compose(DataLayer.applySingleSchedulers())
                .subscribe(this::onGetTracksForArtist, this::onError);
    }

    @Override
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public void onItemClick(Track track) {
        view.playTrack(track.stream_url);
    }

    private void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    private void onGetTracksForArtist(List<Track> tracks) {
        view.showTracks(tracks);
    }
}
