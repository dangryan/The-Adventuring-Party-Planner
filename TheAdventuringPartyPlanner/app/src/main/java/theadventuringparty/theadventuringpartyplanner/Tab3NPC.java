package theadventuringparty.theadventuringpartyplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by kennykha on 3/7/17.
 */

public class Tab3NPC extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.npc_generator, container, false);
        return rootView;
    }
}
