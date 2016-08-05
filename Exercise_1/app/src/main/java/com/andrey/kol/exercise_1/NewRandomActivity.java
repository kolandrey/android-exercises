package com.andrey.kol.exercise_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NewRandomActivity extends AppCompatActivity implements View.OnClickListener {

    final static int RANDOM_NUMBER = 101;
    final static String STATE_NUMBER = "number";

    private Button buttonRandom;
    private TextView viewRandom;
    private Random randomNumber;
    private int numberState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_random);

        viewRandom = (TextView) findViewById(R.id.textView);

        buttonRandom = (Button) findViewById(R.id.button);

        randomNumber = new Random();

        buttonRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        numberState = randomNumber.nextInt(RANDOM_NUMBER);
        viewRandom.setText(String.valueOf(numberState));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUMBER, numberState);
    }

}
