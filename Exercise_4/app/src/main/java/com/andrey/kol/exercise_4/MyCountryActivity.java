package com.andrey.kol.exercise_4;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCountryActivity extends ListActivity {

    private ArrayList<String> countriesArray;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countriesArray = new ArrayList<>();
        countriesArray.addAll(Arrays.asList(getResources().getStringArray(R.array.countries)));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesArray);

        setListAdapter(arrayAdapter);
    }
}
