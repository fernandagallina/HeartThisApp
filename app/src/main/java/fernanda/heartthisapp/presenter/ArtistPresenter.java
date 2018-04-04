package fernanda.heartthisapp.presenter;

import java.util.ArrayList;
import java.util.List;

import fernanda.heartthisapp.DataLayer;
import fernanda.heartthisapp.model.Artist;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 07/12/2016.
 */

public class ArtistPresenter implements ArtistContract.UserActionListener {

    private final ArtistContract.View view;
    private final DataLayer dataLayer;

    private List<Artist> artists;

    public ArtistPresenter(Retrofit retrofit, ArtistContract.View view) {
        this.view = view;
        dataLayer = new DataLayer(retrofit);
        artists = new ArrayList<>();
    }

    @Override
    public void loadArtists() {
        dataLayer.getPopularArtists("popular", 1, 10)
                .compose(DataLayer.applySingleSchedulers())
                .subscribe(this::onGetPopularArtistsSuccess,
                        this::onError);
    }

    @Override
    public void onItemClick(Artist artist) {
        view.showArtistTracks(artist);
    }

    private void onGetPopularArtistsSuccess(List<Artist> artists) {
        this.artists.addAll(artists);

        for (int i = 0; i < artists.size(); i++) {
            final int position = i;
            Artist artist = artists.get(i);
            dataLayer.getArtist(artist.user.permalink)
                    .compose(DataLayer.applySingleSchedulers())
                    .subscribe(artist1 -> onGetArtistSuccess(artist1, position),
                            this::onError);
        }

        view.showArtists(this.artists);
    }

    private void onGetArtistSuccess(Artist artist, int position) {
        this.artists.get(position).numberOfFollowers = artist.numberOfFollowers;
        this.artists.get(position).numberOfTracks = artist.numberOfTracks;

        view.updateItemList(position);
    }

    private void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }
}
