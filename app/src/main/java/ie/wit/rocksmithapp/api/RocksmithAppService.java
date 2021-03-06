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

    @GET("/api/songrecords/")
    Call<List<SongRecord>> getAllSongs();

//   @GET("/api/songrecords/{token}")
//   Call<List<SongRecord>> getAllSongs(@Path("token") String token);

    @GET("/api/songrecords/{id}")
    Call<SongRecord> retrieveSongs(@Path("id") String id);

    @DELETE("/api/songrecords/{id}")
    Call<List<SongRecord>> deleteSong(@Path("id") String id);

    @POST("/api/songrecords/")
    Call<SongRecord> createSong(@Body SongRecord songRecord);

    @PUT("/api/songrecords/{id}")
    Call<SongRecord> updateSong(@Path("id") String id,
                                  @Body SongRecord songRecord);
}
