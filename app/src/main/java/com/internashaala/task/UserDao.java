package com.internashaala.task;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {


    @Insert(onConflict = REPLACE)
    void insertTitle(User user);

    @Insert(onConflict = REPLACE)
    void insertPath(Path path);

    @Query("Select * from User")
    List<User> getAllData();

    @Query("Select * from Path where userID = :userID")
    List<Path> getAllPaths(int userID);

}
