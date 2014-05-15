package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayerMapper implements IMap<JSONObject, Player> {
    @Override
    public Player Map(JSONObject json) {

        if(json == null)
            return null;
        Player player = new Player();
        try {
            player.Name = json.getString("Name");
            player.Type = json.getString("Type");
            player.Nr = json.getInt("Nr");
            player.Movement = json.getInt("Movement");
            player.Strength = json.getInt("Strength");
            player.Agility = json.getInt("Agility");
            player.Armour = json.getInt("Armour");
            player.StartingSkills = GetSkills(json, "StartingSkills");
            player.ExtraSkills = GetSkills(json, "ExtraSkills");

            return player;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> GetSkills(JSONObject json, String skillSetName) throws JSONException {
        JSONArray skillData = json.getJSONArray(skillSetName);
        List<String> skills = new ArrayList<String>();
        if(null == skillData)
            return skills;

        int skillCount = skillData.length();
        for(int i = 0; i < skillCount; i++)
            skills.add(skillData.getString(i));

        return skills;
    }
}
