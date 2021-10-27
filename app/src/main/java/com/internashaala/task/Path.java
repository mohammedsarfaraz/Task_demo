package com.internashaala.task;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Path {

    @PrimaryKey(autoGenerate = true)
    int ID;

    String path;

    int userID;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
