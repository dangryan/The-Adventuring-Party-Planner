package theadventuringparty.theadventuringpartyplanner;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by kennykha on 3/7/17.
 */

public class Tab1Combat extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.combat_encounter_generator, container, false);
        return rootView;
    }
}
