package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String[] GetSkills(JSONObject json, String skillSetName) throws JSONException {
        JSONArray skillData = json.getJSONArray(skillSetName);
        if(null == skillData)
            return new String[0];

        int skillCount = skillData.length();
        String[] skills = new String[skillCount];
        for(int i = 0; i < skillCount; i++)
        {
            skills[i] = skillData.getString(i);
        }

        return skills;
    }
}
