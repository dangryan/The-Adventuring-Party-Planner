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
    private TextView textView1;
    private TextView urlView;
    String displayInfo;
    int avPartyLvl = 10;
    String cr;
    String monCr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_display);

        textView1 = (TextView)findViewById(R.id.fightView);
        Intent intent = getIntent();
        String monDiff = intent.getStringExtra("difficultyChoice");
        String monType = intent.getStringExtra("enemyTypeChoice");
        String monLoot = intent.getStringExtra("lootChoice");
        String monNum = intent.getStringExtra("enemyNumChoice");

        urlView = (TextView)findViewById(R.id.urlTextView);
        urlView.setText(" ");
        //urlView.setText(monDiff);
        //urlView.append(monType);

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

        String url = "http://cgi.soic.indiana.edu/~team39/this.php?cr="+cr+"&type="+ monType+","+spaceVar+"monster"+ spaceVar +"manual"
                +"&loot="+monLoot+"&num="+monNum;

        urlView.append(url);

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
                                String name = enemy.getString("name");
                                String type = enemy.getString("type");
                                String alignment = enemy.getString("alignment");
                                String hp = enemy.getString("hp");

                                textView1.append("The party is fighting a "+ alignment +" "+ size +" "
                                        + name + " with "+ hp + " HP. \n\n");

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
                                textView1.append("\n No enemies found with those parameters! \n");
                            }
                        }
                );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);

        //displayInfo += "";//this is where I will grab the monster name(s);
        //displayInfo += "";//this is where I will grab the monster name(s);


        textView1.setText("");
        textView1.setText(displayInfo);
    }
}