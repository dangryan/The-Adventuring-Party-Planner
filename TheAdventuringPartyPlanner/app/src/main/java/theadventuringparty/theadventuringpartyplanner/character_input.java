package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class character_input extends AppCompatActivity {

    private EditText mNameEdit;
    private EditText mLevelEdit;
    private EditText mArmorClassEdit;
    private EditText mHitPointsEdit;
    private EditText mNotesEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_input);

        mNameEdit = (EditText) findViewById(R.id.name_input);
        mLevelEdit = (EditText) findViewById(R.id.level_input);
        mArmorClassEdit = (EditText) findViewById(R.id.armorClass_input);
        mHitPointsEdit = (EditText) findViewById(R.id.hitPoints_input);
        mNotesEdit = (EditText) findViewById(R.id.notes_input);
    }

    public void onCharacterInputClick(View v) {
        Intent intent = new Intent(this, combat_display.class);

        String nameInput = mNameEdit.getText().toString();
        String levelInput = mLevelEdit.getText().toString();
        String armorClassInput = mArmorClassEdit.getText().toString();
        String hitPointsInput = mHitPointsEdit.getText().toString();
        String notesInput = mNotesEdit.getText().toString();


        intent.putExtra("nameInput", nameInput);
        intent.putExtra("levelInput", levelInput);
        intent.putExtra("armorClassInput", armorClassInput);
        intent.putExtra("hitPointsInput", hitPointsInput);
        intent.putExtra("notesInput", notesInput);

        startActivity(intent);
    }
}