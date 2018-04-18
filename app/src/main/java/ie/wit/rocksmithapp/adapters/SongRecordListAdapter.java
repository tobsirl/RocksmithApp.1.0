package ie.wit.rocksmithapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.model.SongRecord;

public class SongRecordListAdapter extends ArrayAdapter<SongRecord> {
    private Context context;
    private View.OnClickListener deleteListener;
    public List<SongRecord> songRecordList;

    public SongRecordListAdapter(Context context, View.OnClickListener deleteListener,
                             List<SongRecord> songRecordList) {
        super(context, R.layout.songrecordrow, songRecordList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.songRecordList = songRecordList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongRecordItem item = new SongRecordItem(context, parent, deleteListener,
                songRecordList.get(position));
        return item.view;
    }

    @Override
    public int getCount() {
        return songRecordList.size();
    }
    public List<SongRecord> getSongList() {
        return this.songRecordList;
    }

    @Override
    public SongRecord getItem(int position) {
        return songRecordList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getPosition(SongRecord c) {
        return songRecordList.indexOf(c);
    }
}



