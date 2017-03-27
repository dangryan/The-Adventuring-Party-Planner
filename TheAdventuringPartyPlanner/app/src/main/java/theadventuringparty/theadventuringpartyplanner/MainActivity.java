package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner mDifficultySpinner;
    private Spinner mEnemyTypeSpinner;
    private Spinner lootSpinner;
    private Spinner enemyNumSpinner;
    private Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        //setContentView(R.layout.combat_encounter_generator);



        mDifficultySpinner = (Spinner) findViewById(R.id.difficultySpinner);
        mEnemyTypeSpinner = (Spinner) findViewById(R.id.enemyTypeSpinner);
        lootSpinner = (Spinner) findViewById(R.id.lootSpinner);
        enemyNumSpinner = (Spinner) findViewById(R.id.enemyNumSpinner);
        generateButton = (Button)findViewById(R.id.generateButton);
    }


}

