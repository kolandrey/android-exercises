package com.andrey.kol.exercise_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andrey.kol.exercise_4.adapter.CountryAdapter;
import com.andrey.kol.exercise_4.dao.CountryDao;
import com.andrey.kol.exercise_4.model.Country;

import java.util.List;

import static com.andrey.kol.exercise_4.AddCountryActivity.PARAMETER_COUNTRY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int REQUEST_CODE = 1;
    private ListView listView;
    private CountryDao dao;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView_MainActivity);

        dao = CountryDao.getInstance(this);

        List<Country> list = dao.getAll();
        adapter = new CountryAdapter(this, list);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);


        FloatingActionButton buttonAddNew = (FloatingActionButton) findViewById(R.id.buttonAddNew);
        buttonAddNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddCountryActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && requestCode == REQUEST_CODE) {
            Country item = data.getParcelableExtra(PARAMETER_COUNTRY);
            dao.create(item);
            adapter = new CountryAdapter(this, dao.getAll());
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buttonDbDelete) {
            dao.deleteAll();
            adapter = new CountryAdapter(this, dao.getAll());
            listView.setAdapter(adapter);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
                return (deleteItem(info));
            case R.id.edit_item:

                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    private boolean deleteItem (AdapterView.AdapterContextMenuInfo info) {
        dao.delete(adapter.getItem((int)info.id));
        adapter = new CountryAdapter(this, dao.getAll());
        listView.setAdapter(adapter);
        return true;
    }

    private boolean editItem (AdapterView.AdapterContextMenuInfo info) {


        return true;
    }


}
