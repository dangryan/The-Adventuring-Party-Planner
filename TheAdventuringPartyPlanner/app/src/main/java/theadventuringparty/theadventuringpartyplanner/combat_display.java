package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class combat_display extends AppCompatActivity {
    private TextView monster_display_view;
    private TextView loot_display_view;
    String cr;
    String monUrl;
    String lootUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_display);

        monster_display_view = (TextView)findViewById(R.id.monster_display);
        loot_display_view = (TextView)findViewById(R.id.loot_display);

        Intent intent = getIntent();

        String monDiff = intent.getStringExtra("difficultyChoice");
        String monType = intent.getStringExtra("enemyTypeChoice");
        String monLoot = intent.getStringExtra("lootChoice");


        //monDiff is changed here to account for the php results for fractions("1\/8", "1\/4", "1\/2")
        if (monDiff.equals("No Selection")){
            cr = "%";
        }
        else if (monDiff.equals("1/8")){
            cr = "1/8";
        }
        else if (monDiff.equals("1/4")){
            cr = "1/4";
        }
        else if (monDiff.equals("1/2")){
            cr = "1/2";
        }
        else{
            cr = monDiff;
        }

        String spaceVar="%20";

        //monType and monDiff are both checked here for "No Selection" and if true: monType/monDiff = "null"
        if (monType.equals("No Selection")){
            monType = "null";
        }
        if (monDiff.equals("No Selection")){
            cr = "null";
        }


        //if monType = "null", the url can simply end in "null"
        //if not, the url must end in monType + ",%20monster%20manual"
        if (monType.equals("null")){
            monUrl = "http://cgi.soic.indiana.edu/~team39/this.php?cr="+cr+"&type="+ monType;
        }
        else {
            monUrl = "http://cgi.soic.indiana.edu/~team39/this.php?cr=" + cr + "&type=" + monType.toLowerCase() + "," + spaceVar + "monster" + spaceVar + "manual";
        }
        displayOutput(monUrl);

        if (monLoot.equals("Very Rare")){
            monLoot = "Very" + spaceVar + "Rare";
        }
        if (monLoot.equals("No Selection")){
            monLoot = "null";
        }

        lootUrl = "http://cgi.soic.indiana.edu/~team39/loot.php?rarity=" + monLoot;

        displayLootOutput(lootUrl);
    }
    // Initialize a new JsonArrayRequest instance
    public void displayOutput(String url){
        Context mContext = getApplicationContext();


        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                monUrl,
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
                                JSONObject enemy = response.getJSONObject(i);

                                // Get the current enemy (json object) data

                                String name = enemy.getString("name");
                                String type = enemy.getString("type");
                                String alignment = enemy.getString("alignment");
                                String immune = enemy.getString("immune");
                                String hp = enemy.getString("hp");

                                String speed = enemy.getString("speed");
                                String ac = enemy.getString("ac");
                                String str = enemy.getString("str");
                                String dex = enemy.getString("dex");
                                String con = enemy.getString("con");
                                String intel = enemy.getString("intel");
                                String wis = enemy.getString("wis");
                                String cha = enemy.getString("cha");
                                String save = enemy.getString("save");
                                String skill = enemy.getString("skill");
                                String senses = enemy.getString("senses");
                                String languages = enemy.getString("languages");
                                String spells = enemy.getString("spells");
                                String resist = enemy.getString("resist");
                                String traits = enemy.getString("traits");
                                String actions = enemy.getString("actions");

                                monster_display_view.append(
                                        name + ": \n\n" +
                                        "Alignment: " + alignment + "\n" +
                                        "Immune: " + immune + "\n" +
                                        "Type: " + type + "\n" +
                                        "Hit points: " + hp + "\n" +
                                        "Senses: " + senses + "\n" +
                                        "Languages: " + languages + "\n" +
                                        "Resists: " + resist + "\n" +
                                        "Traits: " + traits + "\n" +
                                        "Speed: " + speed + "\t\t\t\t\t"  + "Armor Class: " + ac + "\n\n" +
                                        "STR: " + str + "\t\t\t\t\t" + "DEX: " + dex + "\n" +
                                        "CON: " + con + "\t\t\t\t\t" + "INT: " + intel + "\n" +
                                        "WIS: " + wis + "\t\t\t\t\t" + "CHA: " + cha + "\n\n" +
                                        "Saves: " + save + "\n" +
                                        "Skills: " + skill + "\n" +
                                        "Actions: " + actions + "\n" +
                                        "Spells: " + spells + "\n" +
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
                                monster_display_view.append("\n No enemies found with those parameters! \n");
                            }
                        }
                );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        monster_display_view.setText("");
    }

    public void displayLootOutput(String url){
        Context mContext = getApplicationContext();


        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                lootUrl,
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
                                JSONObject loot = response.getJSONObject(i);

                                // Get the current enemy (json object) data

                                String name = loot.getString("name");
                                String weight = loot.getString("type");
                                String ac = loot.getString("ac");

                                loot_display_view.append(
                                        name + ":" +"\t\t\t\t" + "AC: " + ac +"\n" +
                                        "Weight: " + weight);
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
                        loot_display_view.append("\n No loot found with those parameters! \n");
                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);

        loot_display_view.setText("");
    }
}