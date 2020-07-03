package com.example.dkdus.cashtrans.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String kind;
    String contents;
    String time;

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        kind = in.readString();
        contents = in.readString();
        time = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Recipe(String name, String kind, String contents, String time) {
        this.name = name;
        this.kind = kind;
        this.contents = contents;
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(kind);
        dest.writeString(contents);
        dest.writeString(time);
    }
}
