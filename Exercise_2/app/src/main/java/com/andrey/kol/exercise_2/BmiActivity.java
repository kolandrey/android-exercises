package com.andrey.kol.exercise_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BmiActivity extends AppCompatActivity implements View.OnClickListener {

    final static String DECIMAL_FORMAT = "#0.00";
    final static String EMPTY_SET_TEXT = "";
    final static String WARNING = "Wrong values";
    final static String STATE_LENGTH = "length";
    final static String STATE_WEIGHT = "weight";
    final static String STATE_BMI = "bmiNumber";

    private Button buttonCompute;
    private Button buttonReset;
    private TextView textViewIndex;
    private EditText editTextLength;
    private EditText editTextWeight;
    private String bmiNumber;
    private double length;
    private double weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        buttonCompute = (Button) findViewById(R.id.buttonCompute);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        editTextLength = (EditText) findViewById(R.id.editLength);
        editTextWeight = (EditText) findViewById(R.id.editWeight);
        textViewIndex = (TextView) findViewById(R.id.textIndex);

        bmiNumber = getString(R.string.textIndex);

        buttonCompute.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

        if (savedInstanceState != null) {
            length = savedInstanceState.getDouble(STATE_LENGTH);
            weight = savedInstanceState.getDouble(STATE_WEIGHT);
            bmiNumber = savedInstanceState.getString(STATE_BMI);
            bmiInit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(STATE_LENGTH, length);
        outState.putDouble(STATE_WEIGHT, weight);
        outState.putString(STATE_BMI, bmiNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompute:
                length = Double.parseDouble(editTextLength.getText().toString());
                weight = Double.parseDouble(editTextWeight.getText().toString());
                bmiNumber = new DecimalFormat(DECIMAL_FORMAT).format((weight / (length * length)));
                bmiInit();
                break;
            case R.id.buttonReset:
                editTextLength.setText(EMPTY_SET_TEXT);
                editTextWeight.setText(EMPTY_SET_TEXT);
                textViewIndex.setText(getString(R.string.textIndex));
                break;
        }
    }

    private void bmiInit() {
        try {
            if (this.length < 0 || this.weight < 0) {
                Toast.makeText(getApplicationContext(), WARNING, Toast.LENGTH_LONG).show();
            } else {
                textViewIndex.setText(this.bmiNumber);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), WARNING, Toast.LENGTH_LONG).show();
        }
    }
}
