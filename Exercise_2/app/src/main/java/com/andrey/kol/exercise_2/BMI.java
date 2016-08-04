package com.andrey.kol.exercise_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity implements View.OnClickListener {

    private Button compute;
    private Button reset;
    private TextView index;
    private EditText editLength;
    private EditText editWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        compute = (Button) findViewById(R.id.compute);
        reset = (Button) findViewById(R.id.reset);
        editLength = (EditText) findViewById(R.id.editLength);
        editWeight = (EditText) findViewById(R.id.editWeight);
        index = (TextView) findViewById(R.id.textIndex);

        if (compute != null || reset != null) {
            compute.setOnClickListener(this);
            reset.setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compute:
                try {
                    double length = Double.parseDouble(editLength.getText().toString());
                    double weight = Double.parseDouble(editWeight.getText().toString());
                    String formattedDouble = new DecimalFormat("#0.00").format((weight / (length * length)));
                    index.setText(String.valueOf(formattedDouble));
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Ha ha ha", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.reset:
                editLength.setText("");
                editWeight.setText("");
                index.setText("Body-Mass Index");
                break;
        }
    }
}
