package com.andrey.kol.exercise_3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ColorDisplay extends AppCompatActivity implements View.OnClickListener {

    private Button buttonColor;
    private int red;
    private int green;
    private int blue;
    private int backgroundColor;
    private EditText editTextRed;
    private EditText editTextGreen;
    private EditText editTextBlue;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_display);

        buttonColor = (Button) findViewById(R.id.buttonDisplayColor);
        editTextRed = (EditText) findViewById(R.id.editTextRed);
        editTextGreen = (EditText) findViewById(R.id.editTextGreen);
        editTextBlue = (EditText) findViewById(R.id.editTextBlue);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);

        buttonColor.setOnClickListener(this);

        if (savedInstanceState != null) {
            red = savedInstanceState.getInt("red");
            green = savedInstanceState.getInt("green");
            blue = savedInstanceState.getInt("blue");
            setActivityBackground();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("red", red);
        outState.putInt("green", green);
        outState.putInt("blue", blue);
    }

    @Override
    public void onClick(View v) {
        try {
            red = Integer.parseInt(editTextRed.getText().toString());
            green = Integer.parseInt(editTextGreen.getText().toString());
            blue = Integer.parseInt(editTextBlue.getText().toString());
            setActivityBackground();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Wrong values", Toast.LENGTH_LONG).show();
        }


    }

    private void setActivityBackground() {
        try {
            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
                throw new NumberFormatException();
            } else {
                backgroundColor = Color.rgb(this.red, this.green, this.blue);
                relativeLayout.setBackgroundColor(backgroundColor);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Wrong values", Toast.LENGTH_LONG).show();
        }

    }
}
