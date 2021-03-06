package ie.wit.rocksmithapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.model.SongRecord;

public class SongRecordItem {
    View view;

    public SongRecordItem(Context context, ViewGroup parent,
                          View.OnClickListener deleteListener, SongRecord songRecord)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.songrecordrow, parent, false);
        view.setTag(songRecord._id);

        updateControls(songRecord);

        ImageView imgDelete = view.findViewById(R.id.imgDelete);
        imgDelete.setTag(songRecord);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(SongRecord songRecord) {
        ((TextView) view.findViewById(R.id.rowSongName)).setText(songRecord.songName);
        ((TextView) view.findViewById(R.id.rowArtistName)).setText(songRecord.artistName);
        //((TextView) view.findViewById(R.id.rowRating)).setText(songRecord.ratingValue + " *");
        ((TextView) view.findViewById(R.id.rowDifficulty)).setText(String.valueOf(songRecord.difficulty));
        ((TextView) view.findViewById(R.id.rowSpeed)).setText(String.valueOf(songRecord.speed));

        //ImageView imgIcon = view.findViewById(R.id.RowImage);

//        if (songRecord.favourite)
//            imgIcon.setImageResource(R.drawable.ic_favourite_on);
//        else
//            imgIcon.setImageResource(R.drawable.ic_favourite_off);
    }
}
