package com.ridingwolfstudio.teammanagerforbloodbowl.Mocks;

public class PlayerMock {

    public int Number;
    public String Name;

    public PlayerMock(int nr, String name)
    {
        Number = nr;
        Name = name;
    }

    @Override
    public String toString()
    {
        return String.format("<%1$02d> %2$s", Number, Name);
    }
}
