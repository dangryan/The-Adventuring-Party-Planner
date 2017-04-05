package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class npc_generator extends AppCompatActivity {

    private Spinner mNpcNumSpinner;
    private Spinner mRaceSpinner;
    private Spinner mClassSpinner;
    private Spinner mGenderSpinner;
    private Spinner mAlignmentSpinner;
    private Spinner mOccupationSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_encounter_generator);

        mNpcNumSpinner = (Spinner) findViewById(R.id.difficultySpinner);
        mRaceSpinner = (Spinner) findViewById(R.id.enemyTypeSpinner);
        mClassSpinner = (Spinner) findViewById(R.id.lootSpinner);
        mGenderSpinner = (Spinner) findViewById(R.id.enemyNumSpinner);
        mAlignmentSpinner = (Spinner) findViewById(R.id.alignmentSpinner);
        mOccupationSpinner = (Spinner) findViewById(R.id.occupationSpinner);
    }

    public void onNpcGenerateClick(View v) {
        /*Intent intent = new Intent(this, combat_display.class);

        String NpcNumChoice = mNpcNumSpinner.getSelectedItem().toString();
        String RaceChoice = mRaceSpinner.getSelectedItem().toString();
        String ClassChoice = mClassSpinner.getSelectedItem().toString();
        String GenderChoice = mGenderSpinner.getSelectedItem().toString();
        String AlignmentChoice = mAlignmentSpinner.getSelectedItem().toString();
        String OccupationChoice = mOccupationSpinner.getSelectedItem().toString();



        intent.putExtra("NpcNumChoice", NpcNumChoice);
        intent.putExtra("RaceChoice", RaceChoice);
        intent.putExtra("ClassChoice", ClassChoice);
        intent.putExtra("GenderChoice", GenderChoice);
        intent.putExtra("AlignmentChoice", AlignmentChoice);
        intent.putExtra("OccupationChoice", OccupationChoice);

        startActivity(intent);*/
    }
}