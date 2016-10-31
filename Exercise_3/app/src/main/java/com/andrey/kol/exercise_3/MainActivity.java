package com.andrey.kol.exercise_3;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int COLOR_MAX_INT = 255;
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final int COLOR_MIN_INT = 0;

    private Button buttonColor;
    private EditText editTextRed;
    private EditText editTextGreen;
    private EditText editTextBlue;
    private View viewColorBackground;

    private int backgroundColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_display);

        buttonColor = (Button) findViewById(R.id.buttonDisplayColor);
        editTextRed = (EditText) findViewById(R.id.editTextRed);
        editTextGreen = (EditText) findViewById(R.id.editTextGreen);
        editTextBlue = (EditText) findViewById(R.id.editTextBlue);
        viewColorBackground = findViewById(R.id.rl);

        buttonColor.setOnClickListener(this);

        if (savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(BACKGROUND_COLOR);
            setActivityBackground(backgroundColor);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BACKGROUND_COLOR, backgroundColor);
    }

    @Override
    public void onClick(View v) {
        if (isFormValid()) {
            hideKeyboard();
            setBackgroundColor();
        }
    }

    private boolean isFormValid() {
        if (isEmpty(editTextRed) || (!isValidColor(Integer.parseInt(editTextRed.getText().toString())))) {
            Toast.makeText(getApplicationContext(), R.string.redColorWarning, Toast.LENGTH_SHORT).show();
            editTextRed.requestFocus();
            return false;
        }
        if (isEmpty(editTextGreen) || (!isValidColor(Integer.parseInt(editTextGreen.getText().toString())))) {
            Toast.makeText(getApplicationContext(), R.string.greenColorWarning, Toast.LENGTH_SHORT).show();
            editTextGreen.requestFocus();
            return false;
        }
        if (isEmpty(editTextBlue) || (!isValidColor(Integer.parseInt(editTextBlue.getText().toString())))) {
            Toast.makeText(getApplicationContext(), R.string.blueColorWarning, Toast.LENGTH_SHORT).show();
            editTextBlue.requestFocus();
            return false;
        }
        return true;
    }

    private void setActivityBackground(int color) {
        viewColorBackground.setBackgroundColor(color);
    }

    private void setBackgroundColor() {
        int redColor = Integer.parseInt(editTextRed.getText().toString());
        int greenColor = Integer.parseInt(editTextGreen.getText().toString());
        int blueColor = Integer.parseInt(editTextBlue.getText().toString());
        backgroundColor = Color.rgb(redColor, greenColor, blueColor);
        setActivityBackground(backgroundColor);

    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private boolean isValidColor(int color) {
        return (color >= COLOR_MIN_INT && color <= COLOR_MAX_INT);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = this.getCurrentFocus();
        if (v != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
    }
}
