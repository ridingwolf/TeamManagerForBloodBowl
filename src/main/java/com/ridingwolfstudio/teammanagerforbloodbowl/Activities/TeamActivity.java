package com.ridingwolfstudio.teammanagerforbloodbowl.Activities;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;
import com.ridingwolfstudio.teammanagerforbloodbowl.Mocks.TeamMock;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;
import com.ridingwolfstudio.teammanagerforbloodbowl.R;

public class TeamActivity extends FragmentActivity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private Team[] LoadedTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        TeamMock mock = new TeamMock();
        LoadedTeams = mock.Teams.toArray(new Team[mock.Teams.size()]);

        String[] teamNames = new String[LoadedTeams.length];
        for(int i = 0; i < LoadedTeams.length; i++)
                teamNames[i] = LoadedTeams[i].Name;

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.

                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        teamNames),
                        this
        );
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.team, menu);
        return true;
    }
    
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        ListView lv = (ListView) findViewById(R.id.playerlist);
        // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter

        ArrayAdapter<Player> arrayAdapter =
                new ArrayAdapter<Player>(this,android.R.layout.simple_list_item_1, LoadedTeams[position].Players);
                // inflate & populate own item
                // new ArrayAdapter<Player>(this,R.layout.team_playerlist_item, LoadedTeams[position].Players);

        lv.setAdapter(arrayAdapter);
//
//        Fragment fragment = new DummySectionFragment();
//        Bundle args = new Bundle();
//        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//        fragment.setArguments(args);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit();
        return true;
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_team_dummy, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

}
