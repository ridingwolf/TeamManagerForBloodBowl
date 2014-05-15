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
import com.ridingwolfstudio.teammanagerforbloodbowl.R;
import com.ridingwolfstudio.teammanagerforbloodbowl.io.FileSystem;
import com.ridingwolfstudio.teammanagerforbloodbowl.io.IAccessFiles;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends FragmentActivity implements ActionBar.OnNavigationListener {
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private List<File> LoadedTeams;
    private IAccessFiles FileSystem = new FileSystem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        List<String> teamNames = new ArrayList<String>();
        List<Team> teams = FileSystem.loadTeams();
        LoadedTeams = new ArrayList<File>();
        for(Team team : teams)
        {
            LoadedTeams.add(team.File);
            teamNames.add(team.Name);
        }

        teamNames.add("(+)New Team");

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
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        ListView listView = (ListView) findViewById(R.id.playerlist);

        Team team = new Team();
        if(position < LoadedTeams.size())
            team = FileSystem.loadTeam(LoadedTeams.get(position));

        ArrayAdapter<Player> arrayAdapter = new ListViewPlayerAdapter(this, team.Players);
        listView.setAdapter(arrayAdapter);
        return true;
    }
}
