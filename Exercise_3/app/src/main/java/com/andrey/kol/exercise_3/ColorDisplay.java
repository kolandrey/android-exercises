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

    public static final int COLOR_MAX_INT = 255;
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final int COLOR_MIN_INT = 0;

    private Button buttonColor;
    private EditText editTextRed;
    private EditText editTextGreen;
    private EditText editTextBlue;
    private RelativeLayout relativeLayout;

    private int backgroundColor;


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
            int red = Integer.parseInt(editTextRed.getText().toString());
            int green = Integer.parseInt(editTextGreen.getText().toString());
            int blue = Integer.parseInt(editTextBlue.getText().toString());
            setActivityBackground();
            if (red < COLOR_MIN_INT || red > COLOR_MAX_INT || green < COLOR_MIN_INT || green > COLOR_MAX_INT || blue < COLOR_MIN_INT || blue > COLOR_MAX_INT) {
                Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
            } else {
                backgroundColor = Color.rgb(red, green, blue);
                setActivityBackground();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_LONG).show();
        }
    }

    private void setActivityBackground() {
        relativeLayout.setBackgroundColor(backgroundColor);
    }
}
