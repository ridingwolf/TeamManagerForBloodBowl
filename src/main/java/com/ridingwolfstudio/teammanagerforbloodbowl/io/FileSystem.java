package com.ridingwolfstudio.teammanagerforbloodbowl.io;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSystem implements IAccessFiles {
    //http://stackoverflow.com/questions/2902689/how-can-i-read-a-text-file-from-the-sd-card-in-android

    private File _dataCard, _baseDataDirectory, _teamDirectory;

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

}

