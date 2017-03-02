package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class noncombat_encounter_generator extends AppCompatActivity {

    private Spinner mEventTypeSpinner;
    private Spinner mNpcNumSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_encounter_generator);

        mEventTypeSpinner = (Spinner) findViewById(R.id.event_dropdown);
        mNpcNumSpinner = (Spinner) findViewById(R.id.num_of_npcs_dropdown);
    }

    public void onNonCombatGenerateClick(View v) {
        Intent intent = new Intent(this, combat_display.class);

        String eventTypeChoice = mEventTypeSpinner.getSelectedItem().toString();
        String NpcNumChoice = mNpcNumSpinner.getSelectedItem().toString();


        intent.putExtra("eventTypeChoice", eventTypeChoice);
        intent.putExtra("NpcNumChoice", NpcNumChoice);

        startActivity(intent);
    }
}