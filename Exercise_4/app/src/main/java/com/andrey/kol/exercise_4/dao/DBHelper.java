package com.andrey.kol.exercise_4.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_COUNTRIES = "countriesDB";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_NAME = "name";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "countriesDB.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_COUNTRIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_YEAR + " INTEGER NOT NULL,"
                + COLUMN_NAME + " TEXT NOT NULL" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF IT EXISTS " + TABLE_COUNTRIES);
        onCreate(sqLiteDatabase);
    }
}
