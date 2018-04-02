package ie.wit.rocksmithapp.Main;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ie.wit.rocksmithapp.api.RocksmithAppService;
import ie.wit.rocksmithapp.model.SongRecord;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RocksmithApp extends Application {

    private static RocksmithApp mInstance;
    public RocksmithAppService RocksmithAppService;
    public List<SongRecord> songRecordList = new ArrayList<SongRecord>();

    public static final String TAG = RocksmithApp.class.getName();

    public String serviceURL = "http://coffeemateweb.herokuapp.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("rocksmith", "Rocksmith App Started");
        mInstance = this;

        Gson gson = new GsonBuilder().create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serviceURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        RocksmithAppService = retrofit.create(RocksmithAppService.class);
        Log.v("rocksmithapp", "RocksmithApp Service Created");
    }

    public static synchronized RocksmithApp getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}