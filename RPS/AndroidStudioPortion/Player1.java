package com.example.ajbohr.rock_paper_scissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1);

        Button goToPlayer2 = (Button) findViewById(R.id.rock);
        goToPlayer2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player1.this, Player2.class);
                startActivity(intent);
            }
        });

        Button goToPlayer2a = (Button) findViewById(R.id.paper);
        goToPlayer2a.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player1.this, Player2.class);
                startActivity(intent);
            }
        });

        Button goToPlayer2b = (Button) findViewById(R.id.scissors);
        goToPlayer2b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Player1.this, Player2.class);
                startActivity(intent);
            }
        });
    }
}
