package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class combat_encounter_generator extends AppCompatActivity {

    private Spinner mDifficultySpinner;
    private Spinner mEnemyTypeSpinner;
    private Spinner lootSpinner;
    private Button generatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_encounter_generator);

        mDifficultySpinner = (Spinner) findViewById(R.id.difficultySpinner);
        mEnemyTypeSpinner = (Spinner) findViewById(R.id.enemyTypeSpinner);
        lootSpinner = (Spinner) findViewById(R.id.lootSpinner);
        generatorButton = (Button)findViewById(R.id.generateButton);

    }

    public void combatGenerateClick(View view) {
        Intent intent = new Intent(this, combat_display.class);

        String difficultyChoice = mDifficultySpinner.getSelectedItem().toString();
        String enemyTypeChoice = mEnemyTypeSpinner.getSelectedItem().toString();
        String lootChoice = lootSpinner.getSelectedItem().toString();

        enemyTypeChoice.toLowerCase();

        intent.putExtra("difficultyChoice", difficultyChoice);
        intent.putExtra("enemyTypeChoice", enemyTypeChoice);
        intent.putExtra("lootChoice", lootChoice);

        startActivity(intent);
    }
}