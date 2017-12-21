package com.example.vladl.myapplication;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PokemonDao {
    @Query("select * from Pokemon")
    List<Pokemon> getEntries();

    @Insert
    void insert(Pokemon pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Update
    void update(Pokemon pokemon);

    @Query("DELETE FROM Pokemon")
    void nukeAll();
}