package com.ridingwolfstudio.teammanagerforbloodbowl.Activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ridingwolfstudio.teammanagerforbloodbowl.Adapters.ListViewPlayerAdapter;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;
import com.ridingwolfstudio.teammanagerforbloodbowl.Mocks.TeamMock;
import com.ridingwolfstudio.teammanagerforbloodbowl.R;

public class TeamActivity extends FragmentActivity implements ActionBar.OnNavigationListener {
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private Team[] LoadedTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

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
        getMenuInflater().inflate(R.menu.team, menu);
        return true;
    }
    
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        ListView listView = (ListView) findViewById(R.id.playerlist);

        ArrayAdapter<Player> arrayAdapter = new ListViewPlayerAdapter(this, LoadedTeams[position].Players);
        listView.setAdapter(arrayAdapter);
        return true;
    }
}
