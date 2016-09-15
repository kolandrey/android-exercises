package com.andrey.kol.exercise_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DECIMAL_FORMAT = "#0.0";
    public static final int ZERO = 0;
    public static final int LENGTH_DIVIDER = 10000;
    public static final double MIN_BMI_LIMIT = 18.5;
    public static final int MAX_BMI_LIMIT = 25;

    private Button buttonCompute;
    private Button buttonReset;
    private TextView textViewIndex;
    private TextView textViewInfo;
    private EditText editTextLength;
    private EditText editTextWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        buttonCompute = (Button) findViewById(R.id.buttonCompute);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        editTextLength = (EditText) findViewById(R.id.editTextLength);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        textViewIndex = (TextView) findViewById(R.id.textViewIndex);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);

        buttonCompute.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompute:
                bmiCalculate();
                break;
            case R.id.buttonReset:
                bmiStatesReset();
                break;
        }
    }

    private void bmiCalculate() {
        try {
            String bmiResult = getString(R.string.textEmpty);
            double bmiNumber;
            double length = Double.parseDouble(editTextLength.getText().toString());
            double weight = Double.parseDouble(editTextWeight.getText().toString());
            if (length < ZERO || weight < ZERO) {
                Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_SHORT).show();
            } else {
               bmiNumber = bmiFormulaCompute(weight, length);
               bmiResult = bmiNumberFormat(bmiNumber);
            }
            bmiInit(bmiResult);
            bmiInfo(bmiResult);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_SHORT).show();
        }
    }

    private void bmiStatesReset() {
        editTextLength.setText(R.string.textEmpty);
        editTextWeight.setText(R.string.textEmpty);
        textViewIndex.setText(R.string.textEmpty);
        textViewInfo.setText(R.string.textEmpty);
    }

    private void bmiInit(String bmiResult) {
        textViewIndex.setText(bmiResult);
    }

    private void bmiInfo(String bmiResult){
        double number = Double.parseDouble(bmiResult);
        if (number < MIN_BMI_LIMIT) {
            textViewInfo.setText(R.string.bmiInfoFrail);
        } else if (number >= MIN_BMI_LIMIT && number < MAX_BMI_LIMIT){
            textViewInfo.setText(R.string.bmiInfoNormal);
        } else {
            textViewInfo.setText(R.string.bmiInfoFat);
        }

    }

    private double bmiFormulaCompute(double weight, double length) {
        return (weight / ((length * length) / LENGTH_DIVIDER));
    }

    private String bmiNumberFormat(double bmiNumber){
        return new DecimalFormat(DECIMAL_FORMAT, new DecimalFormatSymbols(Locale.US)).format(bmiNumber);
    }

}
