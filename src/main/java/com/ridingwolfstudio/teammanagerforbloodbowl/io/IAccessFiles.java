package com.ridingwolfstudio.teammanagerforbloodbowl.io;
import java.io.File;

public interface IAccessFiles {
    File getTeamDirectory();
    String[] getAllFileIdsFrom(File directory);
}
