package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;

import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TeamMapper implements IMap<JSONObject, Team> {

    private IMap<JSONObject, Player> PlayerMapper;

    public TeamMapper(IMap<JSONObject, Player> playerMapper)
    {
        PlayerMapper = playerMapper;
    }

    @Override
    public Team Map(JSONObject json) {
        if(json == null)
            return null;
        Team team = new Team();
        try {
            team.Name = json.getString("Team-name");
            team.Players = GetPlayers(json);

            return team;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Player> GetPlayers(JSONObject json) throws JSONException {
        List<Player> players = new ArrayList<Player>();
        JSONArray playerData = json.getJSONArray("Players");
        if(null == playerData)
            return players;

        int playersLength = playerData.length();
        for(int i = 0; i < playersLength; i++)
        {
            Player player = PlayerMapper.Map(playerData.getJSONObject(i));
            if(null != player)
                players.add(player);
        }
        return players;
    }
}
