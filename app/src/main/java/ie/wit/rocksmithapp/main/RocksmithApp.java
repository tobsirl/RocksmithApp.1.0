package ie.wit.rocksmithapp.main;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ie.wit.rocksmithapp.api.RocksmithAppService;
import ie.wit.rocksmithapp.model.SongRecord;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Realm Cloud
import io.realm.ObjectServerError;
import static ie.wit.rocksmithapp.Constants.AUTH_URL;

public class RocksmithApp extends Application {

    private RequestQueue mRequestQueue;
    private static RocksmithApp mInstance;
    public RocksmithAppService RocksmithAppService;
    public List <SongRecord> songRecordList = new ArrayList<SongRecord>();

    /* Client used to interact with Google APIs. */
    public GoogleApiClient mGoogleApiClient;
    public GoogleSignInOptions mGoogleSignInOptions;

    public boolean signedIn = false;
    public String googleToken;
    public String googleName;
    public String googleMail;
    public String googlePhotoURL;
    public Bitmap googlePhoto;
    public int drawerID = 0;

    public static final String TAG = RocksmithApp.class.getName();

    public String serviceURL = "https://rocksmithprogress.herokuapp.com";

    // Realm Cloud
    //public Realm realm;
    //SyncCredentials credentials = SyncCredentials.nickname("newName", false);

    @Override
    public void onCreate() {
        super.onCreate();
        //Realm.init(this);
        Log.v("rocksmith", "Rocksmith App Started");



            mInstance =this;
            mRequestQueue = Volley.newRequestQueue(

            getApplicationContext());

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
        Log.v("rocksmithapp","RocksmithApp Service Created");

//        SyncUser.logInAsync(credentials, AUTH_URL, new SyncUser.Callback<SyncUser>() {
//            public void onSuccess(SyncUser user) {
//                Log.v("rocksmithapp", "Connected to Realm Cloud");
//            }
//
//            public void onError(ObjectServerError error) {
//                Log.v("rocksmithapp", "Failed to connect to Realm Cloud");
//            }
//        });

    }

    public static synchronized RocksmithApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}