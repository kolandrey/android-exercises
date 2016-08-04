package com.andrey.kol.exercise_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NewRandom extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView view;
    final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        view = (TextView) findViewById(R.id.textView);


        if (button != null) {
            button.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        view.setText(String.valueOf(random.nextInt(100)));
    }

}
