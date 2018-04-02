package ie.wit.rocksmithapp.api;

import java.util.List;

import ie.wit.rocksmithapp.model.SongRecord;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Paul on 3/8/2018.
 */

public interface RocksmithAppService {
    @GET("/SongRecord")
    Call<List<SongRecord>> getAllSongs();

    @GET("/SongRecord/{id}")
    Call<List<SongRecord>> getSong(@Path("id") String id);

    @DELETE("/SongRecord/{id}")
    Call<SongRecord> deleteSong(@Path("id") String id);

    @POST("/SongRecord")
    Call<SongRecord> createSong(@Body SongRecord song);

    @PUT("/SongRecord/{id}/votes")
    Call<SongRecord> upVoteSong(@Path("id") String id,
                                  @Body SongRecord song);
}
