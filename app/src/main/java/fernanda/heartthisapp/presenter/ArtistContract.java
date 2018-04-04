package fernanda.heartthisapp.presenter;

import android.content.Context;

import java.util.List;

import fernanda.heartthisapp.ArtistRecyclerViewAdapter;
import fernanda.heartthisapp.model.Artist;

/**
 * Created by Fernanda on 07/12/2016.
 */

public interface ArtistContract {

    interface View {

        Context getContext();

        void showArtists(List<Artist> artists);

        void showError(String message);

        void showArtistTracks(Artist artist);

        void updateItemList(int position);
    }

    interface UserActionListener extends ArtistRecyclerViewAdapter.ArtistItemListener {

        void loadArtists();
    }
}
