package com.andrey.kol.exercise_3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int COLOR_MAX_INT = 255;
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final int COLOR_MIN_INT = 0;

    private Button buttonColor;
    private EditText editTextRed;
    private EditText editTextGreen;
    private EditText editTextBlue;
    private RelativeLayout relativeLayoutColorSet;

    private int backgroundColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_display);

        buttonColor = (Button) findViewById(R.id.buttonDisplayColor);
        editTextRed = (EditText) findViewById(R.id.editTextRed);
        editTextGreen = (EditText) findViewById(R.id.editTextGreen);
        editTextBlue = (EditText) findViewById(R.id.editTextBlue);
        relativeLayoutColorSet = (RelativeLayout) findViewById(R.id.rl);

        buttonColor.setOnClickListener(this);

        if (savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(BACKGROUND_COLOR);
            setActivityBackground();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BACKGROUND_COLOR, backgroundColor);
    }

    @Override
    public void onClick(View v) {
        try {
            backgroundColorSet(redColorRead(), greenColorRead(), blueColorRead());
        } catch (NumberFormatException e){

        }
    }

    private void setActivityBackground() {
        relativeLayoutColorSet.setBackgroundColor(backgroundColor);
    }

    private void backgroundColorSet(int red, int green, int blue) {
        if (red != -1 || green != -1 || blue != -1) {
            backgroundColor = Color.rgb(red, green, blue);
            setActivityBackground();
        }
    }

    private int redColorRead() {
        try {
            int red = Integer.parseInt(editTextRed.getText().toString());
            if (red < COLOR_MIN_INT || red > COLOR_MAX_INT) {
                throw new NumberFormatException();
            } else {
                return red;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.redColorWarning, Toast.LENGTH_SHORT).show();
        }
        return -1;
    }

    private int greenColorRead() {
        try {
            int green = Integer.parseInt(editTextGreen.getText().toString());
            if (green < COLOR_MIN_INT || green > COLOR_MAX_INT) {
                throw new NumberFormatException();
            } else {
                return green;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.greenColorWarning, Toast.LENGTH_SHORT).show();
        }
        return -1;
    }

    private int blueColorRead() {
        try {
            int blue = Integer.parseInt(editTextBlue.getText().toString());
            if (blue < COLOR_MIN_INT || blue > COLOR_MAX_INT) {
                throw new NumberFormatException();
            } else {
                return blue;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.blueColorWarning, Toast.LENGTH_SHORT).show();
        }
        return -1;
    }


}
