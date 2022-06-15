package com.jksurajpuriya.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_data")
public class MyEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

    public MyEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected MyEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<MyEntity> CREATOR = new Creator<MyEntity>() {
        @Override
        public MyEntity createFromParcel(Parcel in) {
            return new MyEntity(in);
        }

        @Override
        public MyEntity[] newArray(int size) {
            return new MyEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
    }
}
