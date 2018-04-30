package ie.wit.rocksmithapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import ie.wit.rocksmithapp.main.RocksmithApp;
import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.activities.Home;
import ie.wit.rocksmithapp.model.SongRecord;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddFragment extends Fragment implements View.OnClickListener,
        Callback<SongRecord> {
    private TextView titleBar;
    private String 		songName, artistName;
    private Integer 		difficulty, speed;
    private double ratingValue;
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

        Button saveButton = v.findViewById(R.id.saveSongBtn);
        song = v.findViewById(R.id.songEditText);
        artist = v.findViewById(R.id.artistEditText);
        difficultyText = v.findViewById(R.id.difficultyEditText);
        speedText = v.findViewById(R.id.speedEditText);
        ratingBar = v.findViewById(R.id.songRatingBar);
        saveButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        titleBar = getActivity().findViewById(R.id.recentAddedBarTextView);
        titleBar.setText(R.string.addASongLbl);
    }

    public void onClick(View v) {
        songName = song.getText().toString();
        artistName = artist.getText().toString();
        try {
            difficulty = Integer.parseInt(difficultyText.getText().toString());
        } catch (NumberFormatException e) {
            difficulty = 0;
        }

        try {
            speed = Integer.parseInt(speedText.getText().toString());
        } catch (NumberFormatException e) {
            speed = 0;
        }
        ratingValue = ratingBar.getRating();

        if ((songName.length() > 0) && (artistName.length() > 0)
                && (difficulty >= 0) && (speed >= 0)){
            SongRecord c = new SongRecord(songName, artistName,
                    difficulty, speed, ratingValue, false, app.googleToken, app.googlePhotoURL);

            app.songRecordList.add(c);
            callCreate = app.RocksmithAppService.createSong(c);
            callCreate.enqueue(this);

        } else
            Toast.makeText(
                    getActivity(),
                    "You must Enter Something for "
                            + "\'Song Name\', \'Artist Name\' and \'difficulty\' and \'Speed\'",
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(Call<SongRecord> call, Response<SongRecord> response) {
        Toast.makeText(getActivity(),"Successfully Added a Song" ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), Home.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void onFailure(Call<SongRecord> call, Throwable t) {
        Toast.makeText(getActivity(),"Unable to Add a Song" ,Toast.LENGTH_LONG).show();
    }

}
