package com.ridingwolfstudio.teammanagerforbloodbowl.Mocks;

import java.util.List;

public class TeamMock {
    public List<String> ElfTeam, HumanTeam, GoblinTeam;

    public TeamMock()
    {
        ElfTeam = populateTeam("Nancy Elf");
        HumanTeam = populateTeam("Lineman");
        GoblinTeam = populateTeam("Insane Gobo");
    }

    private List<String> populateTeam(String playerType) {
        List<String> players = new java.util.ArrayList<String>();
        for (int i = 1; i <= 16; i++){
            players.add(new PlayerMock(i, playerType).toString());
        }
        return players;
    }
}
