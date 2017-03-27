package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class combat_encounter_generator extends AppCompatActivity {

    private Spinner mDifficultySpinner;
    private Spinner mEnemyTypeSpinner;
    private Spinner lootSpinner;
    private Spinner enemyNumSpinner;
    private Button generatorButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_encounter_generator);

        mDifficultySpinner = (Spinner) findViewById(R.id.difficultySpinner);
        mEnemyTypeSpinner = (Spinner) findViewById(R.id.enemyTypeSpinner);
        lootSpinner = (Spinner) findViewById(R.id.lootSpinner);
        enemyNumSpinner = (Spinner) findViewById(R.id.enemyNumSpinner);
        generatorButton = (Button)findViewById(R.id.generateButton);
    }

    public void combatGenerateClick(View view) {
        Intent intent = new Intent(this, combat_display.class);

        String difficultyChoice = mDifficultySpinner.getSelectedItem().toString();
        String enemyTypeChoice = mEnemyTypeSpinner.getSelectedItem().toString();
        String lootChoice = lootSpinner.getSelectedItem().toString();
        String enemyNumChoice = enemyNumSpinner.getSelectedItem().toString();


        intent.putExtra("difficultyChoice", difficultyChoice);
        intent.putExtra("enemyTypeChoice", enemyTypeChoice);
        intent.putExtra("lootChoice", lootChoice);
        intent.putExtra("enemyNumChoice", enemyNumChoice);

        startActivity(intent);
    }
}