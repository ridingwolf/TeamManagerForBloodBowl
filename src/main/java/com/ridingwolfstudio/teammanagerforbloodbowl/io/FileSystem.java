package com.ridingwolfstudio.teammanagerforbloodbowl.io;
import android.os.Environment;
import android.provider.MediaStore;

import com.ridingwolfstudio.teammanagerforbloodbowl.Data.Team;
import com.ridingwolfstudio.teammanagerforbloodbowl.Mappers.IMap;
import com.ridingwolfstudio.teammanagerforbloodbowl.Mappers.PlayerMapper;
import com.ridingwolfstudio.teammanagerforbloodbowl.Mappers.TeamMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileSystem implements IAccessFiles {
    //http://stackoverflow.com/questions/2902689/how-can-i-read-a-text-file-from-the-sd-card-in-android

    private File _dataCard, _baseDataDirectory, _teamDirectory;
    final IMap<JSONObject, Team> TeamMapper;

    public FileSystem()
    {
        TeamMapper = new TeamMapper(new PlayerMapper());
    }

    public FileSystem(IMap<JSONObject, Team> teamMapper)
    {
        TeamMapper = teamMapper;
    }

    @Override
    public File getTeamDirectory()
    {
        return fetchDirectory(getBaseDataDirectory(), "Teams", _teamDirectory);
    }

    @Override
    public String[] getAllFileIdsFrom(File directory)
    {
        File[] files = directory.listFiles();
        List<String> fileIds = new ArrayList<String>();
        for(File file : files)
        {
            if(file.canRead())
                fileIds.add(file.getName());
        }

        return fileIds.toArray(new String[files.length]);
    }

    @Override
    public List<File> getJsonFilesFrom(File directory)
    {
        List<File> jsonFiles = new ArrayList<File>();
        if(false == directory.isDirectory())
            return jsonFiles;

        File[] files = directory.listFiles();
        for(File file : files)
        {
            if(file.canRead() && file.getName().endsWith(".json"))
                jsonFiles.add(file);
        }
        return jsonFiles;
    }

    private File getDataCard() {
        if(null == _dataCard)
            _dataCard = Environment.getExternalStorageDirectory();
        return _dataCard;
    }

    private File getBaseDataDirectory() {
        return fetchDirectory(getDataCard(), "TeamManagerData", _baseDataDirectory);
    }

    private File fetchDirectory(File parentPath, String directory, File cachedDir)
    {
        if(null != cachedDir)
            return cachedDir;
        try {
            cachedDir = new File(parentPath, directory);
            if(false == cachedDir.exists())
                cachedDir.mkdir();
        }
        catch (Exception ex)
        {
            //log exception
        }
        return cachedDir;
    }

    @Override
    public List<Team> loadTeams()
    {
        List<Team> teams = new ArrayList<Team>();
        for(File file : getJsonFilesFrom(getTeamDirectory()))
        {
            Team team = loadTeam(file);
            if(null != team)
                teams.add(team);
        }
        return teams;
    }

    @Override
    public Team loadTeam(File file)
    {
        Team team = TeamMapper.Map(loadJson(file));
        if(null != team)
            team.File = file;
        return team;
    }

    public JSONObject loadJson(File file)
    {
        if(false == file.canRead() || false ==  file.getName().endsWith(".json"))
            return null;

        JSONObject json = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream stream = new BufferedInputStream(fileInputStream);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String rawJson = new String(buffer, "UTF-8");
            return new JSONObject(rawJson);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException ex){
            ex.printStackTrace();
        }

        return json;
    }
}

