package ie.wit.rocksmithapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.wit.rocksmithapp.main.RocksmithApp;
import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.model.SongRecord;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    TextView titleBar,titleSongName,titleArtistName;
    SongRecord aSong;
    Boolean     isFavourite;

    EditText songName, artistName, difficulty;
    RatingBar ratingBar;
    ImageView favouriteImage;

    String      songID;
    Context     context;

    public RocksmithApp app = RocksmithApp.getInstance();



    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(Bundle songRecordBundle) {
        EditFragment fragment = new EditFragment();
        fragment.setArguments(songRecordBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            songID = getArguments().getString("songID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        titleSongName = ((TextView)v.findViewById(R.id.songNameTitleTextView));
        titleArtistName = ((TextView)v.findViewById(R.id.artistNameTextView));
        songName = (EditText)v.findViewById(R.id.songNameEditText);
        artistName = (EditText)v.findViewById(R.id.artistNameEditText);
        difficulty = (EditText)v.findViewById(R.id.difficultyEditText);
        ratingBar = (RatingBar) v.findViewById(R.id.songRatingBar);
        favouriteImage = (ImageView) v.findViewById(R.id.favouriteImageView);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        Call<SongRecord> callRetrieve = app.RocksmithAppService.retrieveSongs(app.googleToken, songID);
        callRetrieve.enqueue(new Callback<SongRecord>() {
            @Override
            public void onResponse(Call<SongRecord> call, Response<SongRecord> response) {
                aSong = response.body();
                updateUI();
            }

            @Override
            public void onFailure(Call<SongRecord> call, Throwable t) {
                Toast.makeText(getActivity(),"Unable to Retrieve Song Details" ,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateUI() {
        titleSongName.setText(aSong.songName);
        titleArtistName.setText(aSong.artistName);
        songName.setText(aSong.songName);
        artistName.setText(aSong.artistName);
        difficulty.setText(aSong.difficulty);
        ratingBar.setRating((float)aSong.ratingValue);

        if (aSong.favourite == true) {
            favouriteImage.setImageResource(R.drawable.ic_favourite_on);
            isFavourite = true;
        } else {
            favouriteImage.setImageResource(R.drawable.ic_favourite_off);
            isFavourite = false;
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void toggle(View v);
        void update(View v);
    }

    public void toggle(View v) {

        if (isFavourite) {
            aSong.favourite = false;
            toastMessage("Removed From Favourites");
            isFavourite = false;
            favouriteImage.setImageResource(R.drawable.ic_favourite_off);
        } else {
            aSong.favourite = true;
            toastMessage("Added to Favourites !!");
            isFavourite = true;
            favouriteImage.setImageResource(R.drawable.ic_favourite_on);
        }
    }

    public void update(View v) {
        if (mListener != null) {
            String SongName = songName.getText().toString();
            String ArtistName = artistName.getText().toString();
            String Difficulty = difficulty.getText().toString();
            double ratingValue = ratingBar.getRating();

            Integer difficultyVal;
            try {
                difficultyVal = Integer.parseInt(Difficulty);
            } catch (NumberFormatException e)
            {            difficultyVal = 0;        }

            if ((SongName.length() > 0) && (ArtistName.length() > 0) && (Difficulty.length() > 0)) {
                aSong.songName = SongName;
                aSong.artistName = ArtistName;
                aSong.difficulty = difficultyVal;
                aSong.ratingValue = ratingValue;
            }

            Call<SongRecord> callUpdate = app.RocksmithAppService.updateSong(app.googleToken,
                    aSong._id,
                    aSong);
            callUpdate.enqueue(new Callback<SongRecord>() {
                @Override
                public void onResponse(Call<SongRecord> call, Response<SongRecord> response) {
                    Toast.makeText(context,"Successfully Updated Song" ,
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<SongRecord> call, Throwable t) {
                    Toast.makeText(context,"Unable to Update Song" ,
                            Toast.LENGTH_LONG).show();
                }
            });


            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
                return;
            }
        } else
            toastMessage("You must Enter Something for Song and Artist");
    }



    protected void toastMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
