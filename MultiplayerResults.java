package com.example.ajbohr.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiplayerResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_results);

        Button goToPlayer1 = (Button) findViewById(R.id.play_again);
        goToPlayer1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MultiplayerResults.this, Player1.class);
                startActivity(intent);
            }
        });

        Button goToHome = (Button) findViewById(R.id.main_menu);
        goToHome.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MultiplayerResults.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
