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

    @GET("/api/songrecords/{token}")
    Call<List<SongRecord>> getAllSongs(@Path("token") String token);

    @GET("/api/songrecords/{token}/{id}")
    Call<SongRecord> retrieveSongs(@Path("token") String token,
                                          @Path("id") String id);

    @DELETE("/api/songrecords/{token}/{id}")
    Call<List<SongRecord>> deleteSong(@Path("token") String token,
                                @Path("id") String id);

    @POST("/api/songrecords/{token}")
    Call<SongRecord> createSong(@Path("token") String token,
                                @Body SongRecord songRecord);

    @PUT("/api/songrecords/{token}/{id}")
    Call<SongRecord> updateSong(@Path("token") String token,
                                  @Path("id") String id,
                                  @Body SongRecord songRecord);
}
