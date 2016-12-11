package fernanda.heartthisapp.presenter;

import java.util.List;

import javax.inject.Inject;

import fernanda.heartthisapp.model.HeartThisService;
import fernanda.heartthisapp.model.Track;
import fernanda.heartthisapp.ui.TrackView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 08/12/2016.
 */

public class TrackPresenterImpl implements TrackPresenter {

    TrackView view;
    Retrofit retrofit;

    @Inject
    public TrackPresenterImpl(TrackView view, Retrofit retrofit) {
        this.view = view;
        this.retrofit = retrofit;
    }

    @Override
    public void loadTrack(String permalink) {
        Call<List<Track>> callTracks;
        final HeartThisService service = retrofit.create(HeartThisService.class);
        callTracks = service.getTracksFromArtist(permalink, "tracks", 1, 10);

        callTracks.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                List<Track> tracks = response.body();
                view.showTracks(tracks);
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                view.showError("Fail");
            }
        });
    }
}
