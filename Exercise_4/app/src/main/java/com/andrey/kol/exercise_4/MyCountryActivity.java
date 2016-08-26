package com.andrey.kol.exercise_4;

import android.app.ListActivity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCountryActivity extends ListActivity implements View.OnClickListener {


    public static final String STR = "STR";
    public static final int REQUEST_CODE = 1;
    private ArrayList<String> countriesArray;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_country);

        countriesArray = new ArrayList<>();
        countriesArray.addAll(Arrays.asList(getResources().getStringArray(R.array.countries)));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesArray);

        setListAdapter(arrayAdapter);

        Button buttonAddNew = (Button) findViewById(R.id.buttonAddNew);
        buttonAddNew.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddCountryActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        countriesArray.add(data.getStringExtra(STR));
    }
}
