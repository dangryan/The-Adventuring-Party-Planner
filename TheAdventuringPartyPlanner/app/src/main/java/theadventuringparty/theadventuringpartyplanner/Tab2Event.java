package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


public class Tab2Event extends Fragment{
    Spinner mEventTypeSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.noncombat_encounter_generator, container, false);

        mEventTypeSpinner = (Spinner) rootView.findViewById(R.id.eventTypeSpinner);

        rootView.findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), combat_display.class);

                String eventTypeChoice = mEventTypeSpinner.getSelectedItem().toString();

                intent.putExtra("difficultyChoice", eventTypeChoice);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
