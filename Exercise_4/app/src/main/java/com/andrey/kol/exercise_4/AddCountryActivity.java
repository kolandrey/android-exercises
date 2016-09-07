package com.andrey.kol.exercise_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCountryActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String STR = "STR";
    public static final String SPACE = " ";
    private EditText editTextYear;
    private EditText editTextCountry;
    private Button buttonAddNew;
    private Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        editTextYear = (EditText) findViewById(R.id.editTextYear);
        editTextCountry = (EditText) findViewById(R.id.editTextCountry);
        buttonAddNew = (Button) findViewById(R.id.buttonAddNew);
        buttonReturn = (Button) findViewById(R.id.buttonReturn);

        buttonAddNew.setOnClickListener(this);
        buttonReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddNew:
                buttonAddNew();
                break;
            case R.id.buttonReturn:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    private void buttonAddNew() {
        try {
            String year = editTextYear.getText().toString();
            String country = editTextCountry.getText().toString();
            String str = year + SPACE + country;
            Intent intentAddNew = new Intent(this, MyCountryActivity.class);
            intentAddNew.putExtra(STR, str);
            setResult(RESULT_OK, intentAddNew);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
        }

    }
}
