package ie.wit.rocksmithapp.fragments;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ie.wit.rocksmithapp.Main.RocksmithApp;
import ie.wit.rocksmithapp.R;
import ie.wit.rocksmithapp.adapters.SongRecordFilter;
import ie.wit.rocksmithapp.adapters.SongRecordListAdapter;
import ie.wit.rocksmithapp.model.SongRecord;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SongRecordFragment extends Fragment implements AdapterView.OnItemClickListener,
        View.OnClickListener,
        AbsListView.MultiChoiceModeListener,
        Callback<List<SongRecord>> {
    protected static SongRecordListAdapter listAdapter;
    protected ListView listView;
    protected SongRecordFilter songRecordFilter;
    public boolean favourites = false;
    protected TextView titleBar;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public Call<List<SongRecord>> callRetrieve;

    public RocksmithApp app = RocksmithApp.getInstance();

    public SongRecordFragment() {
        // Required empty public constructor
    }

    public static SongRecordFragment newInstance() {
        SongRecordFragment fragment = new SongRecordFragment();
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
        View v = null;
        v = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) v.findViewById(R.id.coffeeList);

        mSwipeRefreshLayout =   (SwipeRefreshLayout) v.findViewById(R.id.coffee_swipe_refresh_layout);
        setSwipeRefreshLayout();

        return v;
    }

    public void setListView(ListView listview) {

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(this);
        listview.setAdapter (listAdapter);
        listview.setOnItemClickListener(this);
        listview.setEmptyView(getActivity().findViewById(R.id.empty_list_view));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void getAllSongRecords() {
        callRetrieve = app.RocksmithAppService.getAllSongs(app.googleToken);
        callRetrieve.enqueue(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        titleBar = (TextView)getActivity().findViewById(R.id.recentAddedBarTextView);
        titleBar.setText(R.string.recentlyViewedLbl);
        getAllSongRecords();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof SongRecord)
        {
            onSongRecordDelete ((SongRecord) view.getTag());
        }
    }

    public void onSongRecordDelete(final SongRecord songRecord)
    {
        String stringName = songRecord.songName;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to Delete the \'Song\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                deleteASong(songRecord._id);
                getAllSongRecords();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void deleteASong(String id) {
        Call<List<SongRecord>> callDelete = app.RocksmithAppService.deleteSong(app.googleToken,id);
        callDelete.enqueue(new Callback<List<SongRecord>>() {
            @Override
            public void onResponse(Call<List<SongRecord>> call, Response<List<SongRecord>> response) {
                Toast.makeText(getActivity(),"Successfully Deleted Coffee " ,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<SongRecord>> call, Throwable t) {
                Toast.makeText(getActivity(),"Unable to Delete Coffee : " + "ERROR: " +
                        t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle activityInfo = new Bundle();
        activityInfo.putString("coffeeID", (String) view.getTag());

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = EditFragment.newInstance(activityInfo);
        ft.replace(R.id.homeFrame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu)
    {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_coffee:
                deleteSongRecords(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteSongRecords(ActionMode actionMode)
    {
        SongRecord c = null;
        for (int i = listAdapter.getCount() - 1; i >= 0; i--) {
            if (listView.isItemChecked(i)) {
                deleteASong(listAdapter.getItem(i)._id);
            }
        }
        actionMode.finish();

        if (favourites) {
            //Update the filters data
            songRecordFilter = new SongRecordFilter(app.songRecordList,"favourites",listAdapter);
            songRecordFilter.filter(null);
        }
        getAllSongRecords();
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {}

    /* ************ MultiChoiceModeListener methods (end) *********** */

    @Override
    public void onResponse(Call<List<SongRecord>> call, Response<List<SongRecord>> response) {
        app.songRecordList = response.body();

        listAdapter = new SongRecordListAdapter(getActivity(), this, app.songRecordList);
        songRecordFilter = new SongRecordFilter(app.songRecordList,"all",listAdapter);
        setListView(listView);

        if (favourites) {
            titleBar.setText(R.string.favouritesSongsLbl);
            ((TextView)getActivity().findViewById(R.id.empty_list_view)).setText(R.string.favouritesEmptyMessage);

            songRecordFilter.setFilter("favourites"); // Set the filter text field from 'all' to 'favourites'
            songRecordFilter.filter(null); // Filter the data, but don't use any prefix

            listAdapter.notifyDataSetChanged(); // Update the adapter
        }

        if(app.songRecordList.isEmpty())
            ((TextView)getActivity().findViewById(R.id.empty_list_view)).setText(R.string.recentlyViewedEmptyMessage);
        else
            ((TextView)getActivity().findViewById(R.id.empty_list_view)).setText("");

        listAdapter.notifyDataSetChanged(); // Update the adapter

        if(mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onFailure(Call<List<SongRecord>> call, Throwable t) {
        Toast.makeText(getActivity(),
                "Coffee Service Unavailable. Try again later",
                Toast.LENGTH_LONG).show();
    }

    protected void setSwipeRefreshLayout()
    {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllSongRecords();
            }
        });
    }




}
