package com.example.ajbohr.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        Button goToMultiplayerResults = (Button) findViewById(R.id.rock);
        goToMultiplayerResults.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player2.this, MultiplayerResults.class);
                startActivity(intent);
            }

        });
        Button goToMultiplayerResults1 = (Button) findViewById(R.id.paper);
        goToMultiplayerResults1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player2.this, MultiplayerResults.class);
                startActivity(intent);
            }
        });

        Button goToMultiplayerResults2 = (Button) findViewById(R.id.scissors);
        goToMultiplayerResults2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player2.this, MultiplayerResults.class);
                startActivity(intent);
            }
        });
    }
}
