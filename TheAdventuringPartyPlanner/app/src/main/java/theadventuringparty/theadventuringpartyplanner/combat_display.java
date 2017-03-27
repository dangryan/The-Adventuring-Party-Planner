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
    //private TextView urlView;
    String displayInfo;
    int avPartyLvl = 10;
    String cr;
    String monCr;
    String url;

    String fullSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_display);

        monster_display_view = (TextView)findViewById(R.id.monster_display);

        Intent intent = getIntent();

        //String diffChoice = intent.getStringExtra("difficultyChoice");
        //String enemyTypeChoice = intent.getStringExtra("enemyTypeChoice");
        //String lootChoice = intent.getStringExtra("lootChoice");
        //String enemyNumChoice = intent.getStringExtra("enemyNumChoice");

        //diffView = (TextView)findViewById(R.id.difficultyTextView);
        //enemyTypeView = (TextView)findViewById(R.id.enemyTypeTextView);
        //lootView = (TextView)findViewById(R.id.lootTextView);
        //enemyNumView = (TextView)findViewById(R.id.enemyNumTextView);

        //diffView.setText(diffChoice);
        //enemyTypeView.setText(enemyTypeChoice);
        //lootView.setText(lootChoice);
        //enemyNumView.setText(enemyNumChoice);

        String monDiff = intent.getStringExtra("difficultyChoice");
        String monType = intent.getStringExtra("enemyTypeChoice");
        String monLoot = intent.getStringExtra("lootChoice");
        String monNum = intent.getStringExtra("enemyNumChoice");


        if (monDiff.equals("No Selection")){
            cr = "%";
        }
        if (monDiff.equals("Easy")){
            cr = "2";
        }
        if(monDiff.equals("Medium")){
            cr = "10";
        }
        if (monDiff.equals("Hard")){
            cr = "15";
        }



        //urlView.append(Integer.toString(cr));

        String spaceVar="%20";
        if (monType.equals("No Selection")){
            monType = "null";
        }
        if (monDiff.equals("No Selection")){
            cr = "null";
        }

        if (monType.equals("null")){
            url = "http://cgi.soic.indiana.edu/~team39/this.php?cr="+cr+"&type="+ monType;
        }
        else {
            url = "http://cgi.soic.indiana.edu/~team39/this.php?cr=" + cr + "&type=" + monType + "," + spaceVar + "monster" + spaceVar + "manual";
        }
        //urlView.append(url);

        displayOutput(url);
    }
    // Initialize a new JsonArrayRequest instance
    public void displayOutput(String url){
        Context mContext = getApplicationContext();


        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
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
                                String size = enemy.getString("size");

                                /*if (size == "T"){
                                    fullSize = "Tiny";
                                }
                                else if (size == "S"){
                                    fullSize = "Small";
                                }
                                else if (size == "M"){
                                    fullSize = "Medium";
                                }
                                else if (size == "L"){
                                    fullSize = "Large";
                                }
                                else if (size == "H"){
                                    fullSize = "Huge";
                                }
                                else if (size == "G"){
                                    fullSize = "Gigantic";
                                }*/

                                String name = enemy.getString("name");
                                String type = enemy.getString("type");
                                String alignment = enemy.getString("alignment");
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


                                monster_display_view.append(name + ": \n" +
                                        "Alignment: " + alignment + "\n" +
                                        "Size: " + size + "\n" +
                                        "Type: " + type + "\n" +
                                        "Hit points: " + hp + "\n" +
                                        "Speed: " + speed + "\n" +
                                        "Armor Class: " + ac + "\n" +
                                        "STR: " + str + "\n" +
                                        "DEX: " + dex + "\n" +
                                        "CON: " + con + "\n" +
                                        "INT: " + intel + "\n" +
                                        "WIS: " + wis + "\n" +
                                        "CHA: " + cha + "\n" +
                                        "Saves: " + save + "\n" +
                                        "Skills: " + skill + "\n\n" +
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

        //displayInfo += "";//this is where I will grab the monster name(s);
        //displayInfo += "";//this is where I will grab the monster name(s);


        monster_display_view.setText("");
        monster_display_view.setText(displayInfo);
    }
}