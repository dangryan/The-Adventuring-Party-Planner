package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class Tab3NPC extends Fragment{
    private EditText mNpcNumEditText;
    private Spinner mRaceSpinner;
    private Spinner mClassSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.npc_generator, container, false);

        mNpcNumEditText = (EditText)rootView.findViewById(R.id.npcNumEdit);
        mRaceSpinner = (Spinner) rootView.findViewById(R.id.enemyTypeSpinner);
        mClassSpinner = (Spinner) rootView.findViewById(R.id.lootSpinner);


        rootView.findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), npc_display.class);

                String npcNumChoice = mNpcNumEditText.getText().toString();
                String npcRaceChoice = mRaceSpinner.getSelectedItem().toString();
                String classChoice = mClassSpinner.getSelectedItem().toString();

                intent.putExtra("npcRaceChoice", npcRaceChoice);
                intent.putExtra("classChoice", classChoice);

                if (npcNumChoice.equals("")){
                    intent.putExtra("npcNumChoice", "null");
                }
                else{
                    intent.putExtra("npcNumChoice", npcNumChoice);
                }

                startActivity(intent);
            }
        });
        return rootView;
    }
}
