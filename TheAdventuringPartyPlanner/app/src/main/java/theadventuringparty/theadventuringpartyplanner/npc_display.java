package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class npc_display extends AppCompatActivity{
    private TextView npc_display_view;
    String npcUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.npc_display);

        npc_display_view = (TextView) findViewById(R.id.npc_display);

        Intent intent = getIntent();

        String npcRaceChoice = intent.getStringExtra("npcRaceChoice");
        String npcClassChoice = intent.getStringExtra("classChoice");
        String npcNumChoice = intent.getStringExtra("npcNumChoice");


        String spaceVar = "%20";

        //npcRaceChoice and npcClassChoice are both checked here for "No Selection" and if true:  = "null"
        if (npcRaceChoice.equals("No Selection")) {
            npcRaceChoice = "null";
        }
        if (npcClassChoice.equals("No Selection")) {
            npcClassChoice = "null";
        }


        //build the url
        npcUrl = "http://cgi.soic.indiana.edu/~team39/npc.php?race=" + npcRaceChoice + "&class=" + npcClassChoice;

        if (npcNumChoice.equals("null")){
            Toast.makeText(this, "Please choose a number of NPCs.", Toast.LENGTH_LONG).show();
        }
        else {
            int npcNum = Integer.parseInt(npcNumChoice);

            if (npcNum > 20) {
                npcNum = 20;
            }

            if (npcNum == 0){
                Toast.makeText(this, "Please choose a number of NPCs.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Loading please wait...", Toast.LENGTH_LONG).show();
                while (npcNum > 0) {
                    displayNpcOutput(npcUrl);
                    npcNum -= 1;
                }
            }
        }
    }

    // Initialize a new JsonArrayRequest instance
    public void displayNpcOutput(String url){
        Context mContext = getApplicationContext();


        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                npcUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject npc = response.getJSONObject(i);

                                // Get the current enemy (json object) data

                                String quality = npc.getString("voice");
                                String accent = npc.getString("accent");

                                String fname_first_syllable = npc.getString("fname_syll_1");
                                String fname_second_syllable = npc.getString("fname_syll_2");

                                String lname_first_syllable = npc.getString("lname_syll_1");
                                String lname_second_syllable = npc.getString("lname_syll_2");

                                String race = npc.getString("race");
                                String npcClass = npc.getString("class");
                                String profession = npc.getString("profession");


                                npc_display_view.append(
                                        fname_first_syllable + fname_second_syllable + " " +  lname_first_syllable + lname_second_syllable + "\n" +
                                                " is a(n) "+ race + " " + npcClass + " with a(n) " + quality + " " +  accent + " voice. \n" +
                                                "They work as a " + profession +" in the local town. \n" +
                                                "_______________________________" + "\n\n");

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        npc_display_view.append("\n No enemies found with those parameters! \n");
                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        npc_display_view.setText("");
    }
}