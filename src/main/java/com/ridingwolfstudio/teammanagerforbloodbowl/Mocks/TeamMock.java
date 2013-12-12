package com.ridingwolfstudio.teammanagerforbloodbowl.Mocks;

import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Player;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class TeamMock {
    public List<Team> Teams;
    public TeamMock()
    {
        Teams = new ArrayList<Team>();
        Teams.add(GetGoblins());
        Teams.add(GetWeAreNotGoblins());
    }

    private Team GetGoblins()
    {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(1, "Nr 1", Minion()));
        players.add(new Player(2, "Loemp", Troll()));
        players.add(new Player(3, "Stoem", Troll()));
        players.add(new Player(4, "De Gek!", Looney()));
        players.add(new Player(5, "Die Gevaarlijke", Fanatic()));
        players.add(new Player(6, "Nr meer", Goblin()));
        players.add(new Player(9, "Nr veel", Goblin()));
        players.add(new Player(10, "Nr heel veel", Goblin()));
        players.add(new Player(11, "Nr massas", Goblin()));
        players.add(new Player(12, "Nr euhm...", Goblin()));
        players.add(new Player(13, "Nr meer dan veel", Goblin()));
        players.add(new Player(14, "Nr minder dan massas", Goblin()));
        players.add(new Player(15, "Nr -7589", Goblin()));

        Team globins = new Team();
        globins.Name = "The Expendables";
        globins.Players = players;

        return globins;
    }

    private Team GetWeAreNotGoblins()
    {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(1, "euhm", Minion()));
        players.add(new Player(3, "not-a-goblin", Troll()));
        players.add(new Player(9, "iditiot #1", Goblin()));
        players.add(new Player(10, "gobbo", Goblin()));
        players.add(new Player(11, "get-the-gobbo", Goblin()));
        players.add(new Player(12, "yezzzzzzz", Goblin()));
        players.add(new Player(13, "I", Goblin()));
        players.add(new Player(14, "dA bOSS", Goblin()));
        players.add(new Player(15, "x", Goblin()));

        Team globins = new Team();
        globins.Name = "Not-Goblins";
        globins.Players = players;

        return globins;
    }

    public PlayerType Minion()
    {
        PlayerType gobbo = new PlayerType();
        gobbo.Type = "Minion of Nurgle";
        gobbo.Movement = 5;
        gobbo.Strength = 2;
        gobbo.Agility = 2;
        gobbo.Armour = 8;
        gobbo.StartingSkills = new String[]{ "Dodge", "Fan Favorite" };

        return gobbo;
    }

    public PlayerType Goblin()
    {
        PlayerType gobbo = new PlayerType();
        gobbo.Type = "Goblin";
        gobbo.Movement = 6;
        gobbo.Strength = 2;
        gobbo.Agility = 3;
        gobbo.Armour = 7;
        gobbo.StartingSkills = new String[]{ "Right Stuff", "Dodge", "Stunty" };

        return gobbo;
    }

    public PlayerType Looney()
    {
        PlayerType gobbo = new PlayerType();
        gobbo.Type = "Looney";
        gobbo.Movement = 6;
        gobbo.Strength = 2;
        gobbo.Agility = 3;
        gobbo.Armour = 7;
        gobbo.StartingSkills = new String[]{ "Chainsaw", "Secret Weapon", "Stunty" };

        return gobbo;
    }

    public PlayerType Fanatic()
    {
        PlayerType gobbo = new PlayerType();
        gobbo.Type = "Fanatic";
        gobbo.Movement = 3;
        gobbo.Strength = 7;
        gobbo.Agility = 3;
        gobbo.Armour = 7;
        gobbo.StartingSkills = new String[]{ "Ball & Chain", "Secret Weapon", "Stunty", "No Hands" };

        return gobbo;
    }

    public PlayerType Troll()
    {
        PlayerType troll = new PlayerType();
        troll.Type = "Troll";
        troll.Movement = 4;
        troll.Strength = 5;
        troll.Agility = 1;
        troll.Armour = 9;
        troll.StartingSkills = new String[]{ "Loner", "Always Hungry", "Mighty Blow", "Really Stupid", "Regeneration", "Throw Team-Mate" };

        return troll;
    }



}
