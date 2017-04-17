package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Tab1Combat extends Fragment{
    Spinner mDifficultySpinner;
    Spinner mEnemyTypeSpinner;
    Spinner mLootSpinner;
    EditText mEnemyNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.combat_encounter_generator, container, false);

        mDifficultySpinner = (Spinner) rootView.findViewById(R.id.difficultySpinner);
        mEnemyTypeSpinner = (Spinner) rootView.findViewById(R.id.enemyTypeSpinner);
        mLootSpinner = (Spinner) rootView.findViewById(R.id.lootSpinner);
        mEnemyNum = (EditText) rootView.findViewById(R.id.numOfEnemyTextEdit);


        rootView.findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), combat_display.class);

                String difficultyChoice = mDifficultySpinner.getSelectedItem().toString();
                String enemyTypeChoice = mEnemyTypeSpinner.getSelectedItem().toString();
                String lootChoice = mLootSpinner.getSelectedItem().toString();
                String enemyNumChoice = mEnemyNum.getText().toString();


                enemyTypeChoice.toLowerCase();

                intent.putExtra("difficultyChoice", difficultyChoice);
                intent.putExtra("enemyTypeChoice", enemyTypeChoice);
                intent.putExtra("lootChoice", lootChoice);

                if (enemyNumChoice.equals("")){
                    intent.putExtra("enemyNumChoice", "null");
                }
                else{
                    intent.putExtra("enemyNumChoice", enemyNumChoice);
                }

                startActivity(intent);
            }
        });
        return rootView;
    }
}