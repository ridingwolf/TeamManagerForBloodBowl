package com.ridingwolfstudio.teammanagerforbloodbowl.io;
import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

public interface IAccessFiles {
    File getTeamDirectory();
    String[] getAllFileIdsFrom(File directory);
    List<File> getJsonFilesFrom(File directory);
    List<Team> loadTeams();
    Team loadTeam(File file);
}
