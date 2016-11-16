package com.andrey.kol.exercise_4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.andrey.kol.exercise_4.model.Country;

import java.util.ArrayList;
import java.util.List;

import static com.andrey.kol.exercise_4.dao.DBHelper.TABLE_COUNTRIES;

public class CountryDao {
    private static CountryDao INSTANCE;
    private DBHelper dbHelper;
    private String[] allColumns = {DBHelper.COLUMN_ID,
            DBHelper.COLUMN_YEAR, DBHelper.COLUMN_NAME};

    private CountryDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static synchronized CountryDao getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CountryDao(context);
        }
        return INSTANCE;
    }

    public Country create(Country item) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_YEAR, item.getYear());
        values.put(DBHelper.COLUMN_NAME, item.getName());

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long insertId = database.insert(TABLE_COUNTRIES, null, values);

//        Cursor  cursor = database.query(DBHelper.TABLE_COUNTRIES,
//                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
//                null, null, null);
//        cursor.moveToFirst();
//        Country newItem = cursorToCountry(cursor);

//        cursor.close();
        item.setId(insertId);
        return item;
    }

    public void delete(Country item) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(TABLE_COUNTRIES, DBHelper.COLUMN_ID
                + " = " + item.getId(), null);
        database.close();
    }

    public void update(Country item) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_YEAR, item.getYear());
        values.put(DBHelper.COLUMN_NAME, item.getName());
        database.update(TABLE_COUNTRIES, values, DBHelper.COLUMN_ID
                + " = " + item.getId(), null);
        database.close();
    }

    public void deleteAll() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(TABLE_COUNTRIES, null, null);
        database.close();
    }

    public List<Country> getAll() {
        List<Country> list = new ArrayList<Country>();

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(TABLE_COUNTRIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Country item = cursorToCountry(cursor);
            list.add(item);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }

    private Country cursorToCountry(Cursor cursor) {
        Country item = new Country();
        item.setId(cursor.getLong(0));
        item.setYear(cursor.getInt(1));
        item.setName(cursor.getString(2));
        return item;
    }

}
