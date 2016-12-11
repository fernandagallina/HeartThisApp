package fernanda.heartthisapp.presenter;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.HeartThisService;
import fernanda.heartthisapp.ui.MainView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 07/12/2016.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView view;
    Retrofit retrofit;

    @Inject
    public MainPresenterImpl(Retrofit retrofit, MainView view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    int i = 0;
    @Override
    public void loadArtists() {

        Call<List<Artist>> callArtists;
        final HeartThisService service = retrofit.create(HeartThisService.class);
        callArtists = service.getPopularArtists("popular",1,10);

        callArtists.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                final List<Artist> artists = response.body();

                for(Iterator<Artist> i = artists.iterator(); i.hasNext();) {
                    final Artist item = i.next();
                    final Call<Artist> artistCall = service.getArtist(item.getUser().getPermalink());
                    artistCall.enqueue(new Callback<Artist>() {
                        @Override
                        public void onResponse(Call<Artist> call, Response<Artist> response) {
                            final Artist artist = response.body();
                            item.setFollowers_count(artist.getFollowers_count());
                            item.setTrack_count(artist.getTrack_count());
                        }

                        @Override
                        public void onFailure(Call<Artist> call, Throwable t) {

                        }
                    });

                }
                try {
                    Thread.sleep(1000,0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.showArtitsts(artists);
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                RequestBody requestBody = call.request().body();
                view.showError(call.toString());
            }
        });
    }
}
