package com.jksurajpuriya.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addData(MyEntity entity);

    @Delete
    public void deleteData(MyEntity entity);

    @Update
    public void updateData(MyEntity entity);

    @Query("SELECT * FROM TABLE_DATA order by id DESC")
    public LiveData<List<MyEntity>> getAllData();

}
