package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class combat_encounter_generator extends AppCompatActivity {

    private Spinner mDifficultySpinner;
    private Spinner mRaceSpinner;
    private Spinner mThemeSpinner;
    private Spinner mGenderSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_encounter_generator);

        mDifficultySpinner = (Spinner) findViewById(R.id.npcNumSpinner);
        mRaceSpinner = (Spinner) findViewById(R.id.raceSpinner);
        mThemeSpinner = (Spinner) findViewById(R.id.classSpinner);
        mGenderSpinner = (Spinner) findViewById(R.id.genderSpinner);
    }

    public void onCombatGenerateClick(View v) {
        Intent intent = new Intent(this, combat_display.class);

        String difficultyChoice = mDifficultySpinner.getSelectedItem().toString();
        String raceChoice = mRaceSpinner.getSelectedItem().toString();
        String themeChoice = mThemeSpinner.getSelectedItem().toString();
        String genderChoice = mGenderSpinner.getSelectedItem().toString();


        intent.putExtra("difficultyChoice", difficultyChoice);
        intent.putExtra("raceChoice", raceChoice);
        intent.putExtra("themeChoice", themeChoice);
        intent.putExtra("genderChoice", genderChoice);

        startActivity(intent);
    }
}