package com.ridingwolfstudio.teammanagerforbloodbowl.Data;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public String Name;
    public List<Player> Players;
    public List<String> GetPlayersDisplayList()
    {
        List<String> display = new ArrayList<String>();
        for(Player player : Players)
            display.add(player.toString());

        return display;
    }
    public Team()
    {
        Name = "";
        Players = new ArrayList<Player>();
    }
}
