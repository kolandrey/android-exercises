package com.andrey.kol.exercise_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.andrey.kol.exercise_4.adapter.CountriesAdapter;
import com.andrey.kol.exercise_4.dao.CountryDao;
import com.andrey.kol.exercise_4.model.Country;

import java.util.List;

import static com.andrey.kol.exercise_4.AddCountryActivity.PARAMETER_COUNTRY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CountriesAdapter.ItemActionListener {


    public static final int ADD_REQUEST_CODE = 1;
    public static final int EDIT_REQUEST_CODE = 2;
    public static final String REQUEST_CODE = "REQUEST_CODE";
    public static final String EDIT_PARAMETER_COUNTRY = "EDIT_PARAMETER_COUNTRY";
    private RecyclerView recyclerView;
    private CountryDao dao;
    private CountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_MainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dao = CountryDao.getInstance(this);

        List<Country> list = dao.getAll();
        adapter = new CountriesAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton buttonAddNew = (FloatingActionButton) findViewById(R.id.buttonAddEdit);
        buttonAddNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddCountryActivity.class);
        intent.putExtra(REQUEST_CODE, ADD_REQUEST_CODE);
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && requestCode == ADD_REQUEST_CODE) {
            Country item = data.getParcelableExtra(PARAMETER_COUNTRY);
            dao.create(item);
            adapter = new CountriesAdapter(this, dao.getAll(), this);
            recyclerView.setAdapter(adapter);
        }
        if (data != null && requestCode == EDIT_REQUEST_CODE) {
            Country item = data.getParcelableExtra(PARAMETER_COUNTRY);
            dao.update(item);
            adapter = new CountriesAdapter(this, dao.getAll(), this);
            recyclerView.setAdapter(adapter);
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
            adapter = new CountriesAdapter(this, dao.getAll(),this);
            recyclerView.setAdapter(adapter);
        }
        return true;
    }


    private boolean deleteItem(Country itemSource) {
        dao.delete(itemSource);
        adapter = new CountriesAdapter(this, dao.getAll(),this);
        recyclerView.setAdapter(adapter);
        return true;
    }

    private boolean editItem(Country itemSource) {
        Intent intent = new Intent(this, AddCountryActivity.class);
        intent.putExtra(EDIT_PARAMETER_COUNTRY, itemSource);
        intent.putExtra(REQUEST_CODE, EDIT_REQUEST_CODE);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
        return true;
    }


    @Override
    public void onEdit(Country item) {
        editItem(item);
    }

    @Override
    public void onDelete(Country item) {
        deleteItem(item);
    }
}
