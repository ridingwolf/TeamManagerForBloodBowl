package com.ridingwolfstudio.teammanagerforbloodbowl.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Team {
    public String Name;
    public List<Player> Players;
    public File File;

    public Team()
    {
        Name = "";
        Players = new ArrayList<Player>();
    }
}
