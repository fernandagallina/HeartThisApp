package fernanda.heartthisapp;

import java.util.List;

import fernanda.heartthisapp.model.Artist;
import fernanda.heartthisapp.model.HeartThisService;
import fernanda.heartthisapp.model.Track;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.http.Query;

/**
 * Created by fernandagallina on 4/3/18.
 */

public class DataLayer {

    private HeartThisService heartThisService;

    public DataLayer(Retrofit retrofit) {
        this.heartThisService = retrofit.create(HeartThisService.class);
    }

    public Single<List<Artist>> getPopularArtists(String type, int page, int count) {
        return heartThisService.getPopularArtists(type, page, count);
    }

    public Single<Artist> getArtist(String link) {
        return heartThisService.getArtist(link);
    }

    public Single<List<Track>> getTracksFromArtist(String permalink, String type, int page, int count) {
        return heartThisService.getTracksFromArtist(permalink, type, page,count);}

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> applyObservableSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
