package com.jksurajpuriya.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    public LiveData<List<MyEntity>> allData;
    public MyViewModel(@NonNull Application application) {
        super(application);

        MyDao dao = MyDatabase.getInstance(application).myDao();
        allData=dao.getAllData();
    }
}
