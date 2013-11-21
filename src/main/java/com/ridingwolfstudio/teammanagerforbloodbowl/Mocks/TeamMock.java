package com.ridingwolfstudio.teammanagerforbloodbowl.Mocks;

import java.util.List;

public class TeamMock {
    public List<String> players;

    public TeamMock()
    {
        players = new java.util.ArrayList<String>();
        for (int i = 1; i <= 16; i++){
            players.add(new PlayerMock(i, "Lineman").toString());
        }
    }
}
