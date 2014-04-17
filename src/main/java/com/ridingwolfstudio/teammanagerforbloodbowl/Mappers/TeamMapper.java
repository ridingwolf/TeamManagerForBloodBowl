package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;

import org.json.JSONException;
import org.json.JSONObject;

public class TeamMapper implements IMapJsonTo<Team> {

    @Override
    public Team Create(JSONObject json) {
        if(json == null)
            return null;
        Team team = new Team();
        try {
            team.Name = json.getString("Team-name");
            return team;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
