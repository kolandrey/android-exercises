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

    final static String DECIMAL_FORMAT = "#0.0";
    final static String STATE_BMI = "bmiNumber";
    public static final int Zero = 0;

    private Button buttonCompute;
    private Button buttonReset;
    private TextView textViewIndex;
    private EditText editTextLength;
    private EditText editTextWeight;
    private String bmiNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        buttonCompute = (Button) findViewById(R.id.buttonCompute);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        editTextLength = (EditText) findViewById(R.id.editLength);
        editTextWeight = (EditText) findViewById(R.id.editWeight);
        textViewIndex = (TextView) findViewById(R.id.textIndex);

        buttonCompute.setOnClickListener(this);
        buttonReset.setOnClickListener(this);

        if (savedInstanceState != null) {
            bmiNumber = savedInstanceState.getString(STATE_BMI);
            bmiInit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_BMI, bmiNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompute:
                buttonCompute();
                break;
            case R.id.buttonReset:
                buttonReset();
                break;
        }
    }

    private void buttonCompute() {
        try {
            double length = Double.parseDouble(editTextLength.getText().toString());
            double weight = Double.parseDouble(editTextWeight.getText().toString());
            if (length < Zero || weight < Zero) {
                Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
            } else {
                bmiNumber = new DecimalFormat(DECIMAL_FORMAT).format((weight / (length * length)));
            }
            bmiInit();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
        }
    }

    private void buttonReset() {
        editTextLength.setText(getString(R.string.textEmpty));
        editTextWeight.setText(getString(R.string.textEmpty));
        textViewIndex.setText(getString(R.string.textEmpty));
    }

    private void bmiInit() {
        textViewIndex.setText(this.bmiNumber);
    }

}
