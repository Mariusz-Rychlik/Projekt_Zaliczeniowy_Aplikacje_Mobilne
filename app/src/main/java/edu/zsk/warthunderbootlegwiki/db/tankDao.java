package edu.zsk.warthunderbootlegwiki.db;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import edu.zsk.warthunderbootlegwiki.db.entity.Tank;

import java.util.List;

@Dao
public interface tankDao {
    @Insert
    void insert(Tank tank);

    @Insert
    void insertAll(List<Tank> tanks);


    @Query("SELECT * FROM Tank")
    List<Tank> getAllTanks();

    @Query("SELECT * FROM Tank WHERE name = :tankName LIMIT 1")
    Tank getTankByName(String tankName);


    @Delete
    void delete(Tank tank);

    @Query("DELETE FROM Tank")
    void deleteAll();



}
