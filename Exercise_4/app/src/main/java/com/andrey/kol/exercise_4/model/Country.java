package com.andrey.kol.exercise_4.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by feliss on 11/5/16.
 */

public class Country implements Parcelable {
    private Long id;
    private Integer year;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country() {
    }

    public Country( Integer year, String name) {
        this.year = year;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.year);
        dest.writeString(this.name);
    }

    protected Country(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.year = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
