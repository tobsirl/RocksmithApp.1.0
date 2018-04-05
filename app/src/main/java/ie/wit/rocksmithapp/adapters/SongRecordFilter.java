package ie.wit.rocksmithapp.adapters;


import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.wit.rocksmithapp.model.SongRecord;

public class SongRecordFilter extends Filter {
    private List<SongRecord> originalSongRecordList;
    private String 				filterText;
    private SongRecordListAdapter 	adapter;

    public SongRecordFilter(List<SongRecord> originalSongRecordList, String filterText,
                            SongRecordListAdapter adapter) {
        super();
        this.originalSongRecordList = originalSongRecordList;
        this.filterText = filterText;
        this.adapter = adapter;
    }

    public void setFilter(String filterText) {
        this.filterText = filterText;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();

        if (originalSongRecordList == null) {
            originalSongRecordList = new ArrayList<SongRecord>();
        }
        if (prefix == null || prefix.length() == 0) {
            List<SongRecord> newSongs = new ArrayList<SongRecord>();
            if (filterText.equals("all")) {
                results.values = originalSongRecordList;
                results.count = originalSongRecordList.size();
            } else {
                if (filterText.equals("favourites")) {
                    for (SongRecord c : originalSongRecordList)
                        if (c.favourite)
                            newSongs.add(c);
                }
                results.values = newSongs;
                results.count = newSongs.size();
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            final ArrayList<SongRecord> newSongs = new ArrayList<SongRecord>();

            for (SongRecord c : originalSongRecordList) {
                final String itemName = c.songName.toLowerCase();
                if (itemName.contains(prefixString)) {
                    if (filterText.equals("all")) {
                        newSongs.add(c);
                    } else if (c.favourite) {
                        newSongs.add(c);
                    }}}
            results.values = newSongs;
            results.count = newSongs.size();
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence prefix, FilterResults results) {

        adapter.songRecordList = (ArrayList<SongRecord>) results.values;

        if (results.count >= 0)
            adapter.notifyDataSetChanged();
        else {
            adapter.notifyDataSetInvalidated();
            adapter.songRecordList = originalSongRecordList;
        }
    }
}
