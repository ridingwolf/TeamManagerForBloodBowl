package com.ridingwolfstudio.teammanagerforbloodbowl.Data;

public class Player extends PlayerTemplate
{
	public Integer Nr;
	public String Name;	
	public String[] ExtraSkills;
	public Integer StarPlayerPoints;
	
	public Player(int nr, String name, PlayerType playerType)
	{
		Nr = nr;
		Name = name;
		Type = playerType.Type;
		Movement = playerType.Movement;
		Strength = playerType.Strength;
		Agility = playerType.Agility;
		Armour = playerType.Armour;
		StartingSkills = playerType.StartingSkills;
		Price = playerType.Price;
	}
	
	public String toString()
	{
		
		return 
		"#" + Nr.toString()
		+ " " + Name 
		+ " " + Type 
		+ " " + Movement 
		+ " " + Strength 
		+ " " + Agility  
		+ " " + Armour
		+ " " + getSkills()
		+ "  SPP:" + StarPlayerPoints;
	}
	
	private String getSkills()
	{
		String skills = "";
		if(StartingSkills != null)
		{
            for (String skill : StartingSkills) {
                if (skill != null && skill != "") {
                    skills += skill + ";";
                }
            }
		}
		if(ExtraSkills != null)
		{
            for (String skill : ExtraSkills) {
                if (skill != null && skill != "") {
                    skills += skill + ";";
                }
            }
		}
		return skills;
	}
	
}
