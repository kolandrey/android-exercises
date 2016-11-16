package com.andrey.kol.exercise_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrey.kol.exercise_4.model.Country;

import static com.andrey.kol.exercise_4.MainActivity.*;

public class AddCountryActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAMETER_COUNTRY = "PARAMETER_COUNTRY";

    private EditText editTextYear;
    private EditText editTextCountry;
    private Button buttonAddNew;
    private Button buttonReturn;
    private int requestCode;
    Country item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        editTextYear = (EditText) findViewById(R.id.editTextYear);
        editTextCountry = (EditText) findViewById(R.id.editTextCountry);
        buttonAddNew = (Button) findViewById(R.id.buttonAddEdit);
        buttonReturn = (Button) findViewById(R.id.buttonReturn);

        buttonAddNew.setOnClickListener(this);
        buttonReturn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            requestCode = extras.getInt(REQUEST_CODE, 0);
        }

//        Intent sourceIntent = getIntent();
//        if (sourceIntent != null) {
//            requestCode = sourceIntent.getIntExtra(REQUEST_CODE, 0);
//        }

        switch (requestCode) {
            case ADD_REQUEST_CODE:
                buttonAddNew.setText(getText(R.string.add_button_text));
                setTitle(R.string.title_add);
                break;
            case EDIT_REQUEST_CODE:
                buttonAddNew.setText(getText(R.string.edit_button_text));
                setTitle(R.string.title_edit);
                item = extras.getParcelable(EDIT_PARAMETER_COUNTRY);
                editTextYear.setText(String.valueOf(item.getYear()));
                editTextCountry.setText(item.getName());
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddEdit:
                switch (requestCode) {
                    case ADD_REQUEST_CODE:
                        buttonAddEdit();
                        break;
                    case EDIT_REQUEST_CODE:
                        buttonEdit();
                        break;
                }

                break;
            case R.id.buttonReturn:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    private void buttonEdit() {
        try {
            Intent intentAddNew = new Intent(this, MainActivity.class);
            item.setYear(Integer.parseInt(editTextYear.getText().toString()));
            item.setName(editTextCountry.getText().toString());
            intentAddNew.putExtra(PARAMETER_COUNTRY, item);
            setResult(RESULT_OK, intentAddNew);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
        }

    }


    private void buttonAddEdit() {
        try {
            Integer year = Integer.parseInt(editTextYear.getText().toString());
            String country = editTextCountry.getText().toString();
            Intent intentAddNew = new Intent(this, MainActivity.class);
            intentAddNew.putExtra(PARAMETER_COUNTRY, new Country(
                    year, country
            ));
            setResult(RESULT_OK, intentAddNew);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
        }

    }
}
