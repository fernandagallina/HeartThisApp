package fernanda.heartthisapp.model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Fernanda on 06/12/2016.
 */

public interface HeartThisService {

    @GET("/feed/")
    Call<List<Artist>> getPopularArtists(
            @Query(value = "type", encoded = true) String type,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "count", encoded = true) int count
    );

    @GET("/{permalink}/")
    Call<Artist> getArtist(
            @Path("permalink") String permalink
    );

    @GET("/{permalink}/")
    Call<List<Track>> getTracksFromArtist(
            @Path("permalink") String permalink,
            @Query(value = "type", encoded = true) String type,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "count", encoded = true) int count
    );
}
