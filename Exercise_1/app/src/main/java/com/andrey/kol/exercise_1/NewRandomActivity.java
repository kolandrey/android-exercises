package com.andrey.kol.exercise_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NewRandomActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int RANDOM_NUMBER = 101;

    private Button buttonRandom;
    private TextView textViewRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_random);

        textViewRandom = (TextView) findViewById(R.id.textViewRandom);

        buttonRandom = (Button) findViewById(R.id.buttonRandom);

        buttonRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int numberState = new Random().nextInt(RANDOM_NUMBER);
        textViewRandom.setText(String.valueOf(numberState));
    }
}
