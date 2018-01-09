package com.example.octavian.livecoding2;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.nisrulz.sensey.MovementDetector;
import com.github.nisrulz.sensey.Sensey;

public class Main2Activity extends AppCompatActivity {

    MovementDetector.MovementListener movementListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ((Button) findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sensey.getInstance().stopMovementDetection(movementListener);
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });

        Sensey.getInstance().init(getBaseContext());

        movementListener=new MovementDetector.MovementListener() {
            @Override public void onMovement() {
                ((ConstraintLayout) findViewById(R.id.monitoringLayout)).setBackgroundColor(Color.RED);
            }

            @Override public void onStationary() {
                // Movement stopped, do something
            }
        };

        Sensey.getInstance().startMovementDetection(movementListener);


    }
}
