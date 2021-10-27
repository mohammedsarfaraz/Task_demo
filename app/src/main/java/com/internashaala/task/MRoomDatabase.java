package com.internashaala.task;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Path.class}, version = 1)
public abstract class MRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static MRoomDatabase mRoomDatabase;
    public static final String DATABASE_NAME = "my-database";


    public static MRoomDatabase initialize(Context context) {
        if (mRoomDatabase != null) {
            return mRoomDatabase;
        }


        mRoomDatabase = Room.databaseBuilder(context,
                MRoomDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        return mRoomDatabase;

    }


}
