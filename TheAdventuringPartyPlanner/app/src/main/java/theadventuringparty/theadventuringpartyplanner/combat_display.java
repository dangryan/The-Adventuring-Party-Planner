package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class combat_display extends AppCompatActivity {
    TextView diffView;
    TextView enemyTypeView;
    TextView lootView;
    TextView enemyNumView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_display);
        Intent intent = getIntent();
        String diffChoice = intent.getStringExtra("difficultyChoice");
        String enemyTypeChoice = intent.getStringExtra("enemyTypeChoice");
        String lootChoice = intent.getStringExtra("lootChoice");
        String enemyNumChoice = intent.getStringExtra("enemyNumChoice");

        diffView = (TextView)findViewById(R.id.difficultyTextView);
        enemyTypeView = (TextView)findViewById(R.id.enemyTypeTextView);
        lootView = (TextView)findViewById(R.id.lootTextView);
        enemyNumView = (TextView)findViewById(R.id.enemyNumTextView);

        diffView.setText(diffChoice);
        enemyTypeView.setText(enemyTypeChoice);
        lootView.setText(lootChoice);
        enemyNumView.setText(enemyNumChoice);
    }
}