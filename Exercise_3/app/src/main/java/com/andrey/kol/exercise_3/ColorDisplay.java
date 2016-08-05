package com.andrey.kol.exercise_3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class ColorDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_display);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        rl.setBackgroundColor(Color.BLUE);
    }
}
