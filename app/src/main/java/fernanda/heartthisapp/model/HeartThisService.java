package fernanda.heartthisapp.model;

import java.util.List;
import java.util.Map;

import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HeartThisService {

    @GET("/feed/")
    Single<List<Artist>> getPopularArtists(
            @Query(value = "type", encoded = true) String type,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "count", encoded = true) int count
    );

    @GET("/{permalink}/")
    Single<Artist> getArtist(
            @Path("permalink") String permalink
    );

    @GET("/{permalink}/")
    Single<List<Track>> getTracksFromArtist(
            @Path("permalink") String permalink,
            @Query(value = "type", encoded = true) String type,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "count", encoded = true) int count
    );
}
