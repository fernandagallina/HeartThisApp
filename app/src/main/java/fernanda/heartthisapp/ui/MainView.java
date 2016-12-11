package fernanda.heartthisapp.ui;

import android.content.Context;

import java.util.List;

import fernanda.heartthisapp.model.Artist;

/**
 * Created by Fernanda on 06/12/2016.
 */

public interface MainView {

    Context getContext();

    void showArtitsts(List<Artist> artists);

    void showError(String message);
}
