package com.jksurajpuriya.notes;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {
    private MyDao myDao;

    public MyRepository(MyDao myDao) {
        this.myDao = myDao;
    }
    public void addData(MyEntity entity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDao.addData(entity);
            }
        }).start();
    }
    public void deleteData(MyEntity entity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDao.deleteData(entity);
            }
        }).start();
    }
    public void updateData(MyEntity entity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDao.updateData(entity);
            }
        }).start();
    }
    public LiveData<List<MyEntity>> getAllData(){
        return myDao.getAllData();
    }
}
