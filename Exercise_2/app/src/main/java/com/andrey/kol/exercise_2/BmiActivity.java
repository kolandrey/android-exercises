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

    private Button computeButton;
    private Button resetButton;
    private TextView textViewIndex;
    private EditText editTextLength;
    private EditText editTextWeight;
    private double length;
    private double weight;
    private String bmiNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        computeButton = (Button) findViewById(R.id.buttonCompute);
        resetButton = (Button) findViewById(R.id.buttonReset);
        editTextLength = (EditText) findViewById(R.id.editLength);
        editTextWeight = (EditText) findViewById(R.id.editWeight);
        textViewIndex = (TextView) findViewById(R.id.textIndex);

        bmiNumber = getString(R.string.textIndex);

        computeButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        if (savedInstanceState != null) {
            length = savedInstanceState.getDouble("length");
            weight = savedInstanceState.getDouble("weight");
            bmiNumber = savedInstanceState.getString("bmiNumber");
            bmiInit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("length", length);
        outState.putDouble("weight", weight);
        outState.putString("bmiNumber", bmiNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompute:
                length = Double.parseDouble(editTextLength.getText().toString());
                weight = Double.parseDouble(editTextWeight.getText().toString());
                bmiNumber = new DecimalFormat("#0.00").format((weight / (length * length)));
                bmiInit();
                break;
            case R.id.buttonReset:
                editTextLength.setText("");
                editTextWeight.setText("");
                textViewIndex.setText(getString(R.string.textIndex));
                break;
        }
    }

    private void bmiInit() {
        try {
            if (this.length > 999 || this.weight > 999) {
                throw new InvalidValueException();
            } else {
                textViewIndex.setText(this.bmiNumber);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Wrong values", Toast.LENGTH_LONG).show();
        } catch (InvalidValueException e) {
            Toast.makeText(getApplicationContext(), "Wrong values", Toast.LENGTH_LONG).show();
        }

    }
}
