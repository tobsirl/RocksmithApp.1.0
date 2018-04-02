package ie.wit.rocksmithapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


import ie.wit.rocksmithapp.Main.RocksmithApp;
import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.model.SongRecord;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements View.OnClickListener, Callback<SongRecord> {
    private TextView titleBar;
    private String 		songName, artistName;
    private Integer 		difficulty, speed;
    private EditText song, artist, difficultyText, speedText;
    private RatingBar ratingBar;
    public RocksmithApp app = RocksmithApp.getInstance();

    public Call<SongRecord> callCreate;


    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        Button saveButton = (Button) v.findViewById(R.id.saveSongBtn);
        song = (EditText) v.findViewById(R.id.songEditText);
        artist = (EditText) v.findViewById(R.id.artistEditText);
        difficultyText = (EditText) v.findViewById(R.id.difficultyEditText);
        speedText = (EditText) v.findViewById(R.id.speedEditText);
        ratingBar = (RatingBar) v.findViewById(R.id.songRatingBar);
        saveButton.setOnClickListener(this);
        return v;
    }



    public void onClick(View v) {

    }

}
