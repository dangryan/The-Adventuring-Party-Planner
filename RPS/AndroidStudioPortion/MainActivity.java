package com.example.ajbohr.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToMultiplayer = (Button) findViewById(R.id.multiplayer);
        goToMultiplayer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Player1.class);
                startActivity(intent);
            }
        });

        Button goToSinglePlayer = (Button) findViewById(R.id.single);
        goToSinglePlayer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SinglePlayer.class);
                startActivity(intent);
            }
        });
    }
}
