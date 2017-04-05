package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        Toast.makeText(this, "Loading please wait...", Toast.LENGTH_LONG).show();
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
                            for(int i=0;i<response.length();i++) {
                                // Get current json object
                                JSONObject loot = response.getJSONObject(i);

                                // Get the current enemy (json object) data
                                String type = loot.getString("type");
                                String name = loot.getString("name");
                                String weight = loot.getString("type");
                                String ac = loot.getString("ac");
                                String stealth = loot.getString("stealth");
                                String rarity = loot.getString("rarity");
                                String strength = loot.getString("strength");
                                String dmg1 = loot.getString("dmg1");
                                String dmg2 = loot.getString("dmg2");
                                String dmgType = loot.getString("dmgType");
                                String property = loot.getString("property");
                                //String dmgRange = loot.getString("dmgRange");

                                //Changing type to the full type name
                                if (type.equals("A")){
                                    type = "Ammunition";
                                }
                                else if (type.equals("G")){
                                    type = "General";
                                }
                                else if (type.equals("HA")){
                                    type = "Heavy Armor";
                                }
                                else if (type.equals("LA")){
                                    type = "Light Armor";
                                }
                                else if (type.equals("M")){
                                    type = "Melee Weapon";
                                }
                                else if (type.equals("MA")){
                                    type = "Medium Armor";
                                }
                                else if (type.equals("P")){
                                    type = "Potion";
                                }
                                else if (type.equals("R")){
                                    type = "Ranged Weapon";
                                }
                                else if (type.equals("RD")){
                                    type = "Rod";
                                }
                                else if (type.equals("RG")){
                                    type = "Ring";
                                }
                                else if (type.equals("S")){
                                    type = "Shield";
                                }
                                else if (type.equals("SC")){
                                    type = "Scroll";
                                }
                                else if (type.equals("ST")){
                                    type = "Staff";
                                }
                                else if (type.equals("W")){
                                    type = "Wondrous Item";
                                }
                                else if (type.equals("WD")){
                                    type = "Wand";
                                }

                                //changing loot dmgType to the full damage type
                                if (dmgType.equals("B")){
                                    dmgType = "Bludgeoning";
                                }
                                else if (dmgType.equals("P")){
                                    dmgType = "Piercing";
                                }
                                else if (dmgType.equals("S")){
                                    dmgType = "Slashing";
                                }


                                //TODO FIX THIS PROPERTY OUTPUT
                                //changing loot property to the full property
                                String formattedProperty = property.replace("2H","Two-handed");
                                formattedProperty = formattedProperty.replace("G","General");
                                formattedProperty = formattedProperty.replace("HA","Heavy Armor");
                                formattedProperty = formattedProperty.replace("LA","Light Armor");
                                formattedProperty = formattedProperty.replace("M","Melee Weapon");
                                formattedProperty = formattedProperty.replace("MA","Medium Armor");
                                formattedProperty = formattedProperty.replace("P","Potion");
                                formattedProperty = formattedProperty.replace("R","Ranged Weapon");
                                formattedProperty = formattedProperty.replace("RD","Rod");
                                formattedProperty = formattedProperty.replace("RG","Ring");
                                formattedProperty = formattedProperty.replace("S","Shield");
                                formattedProperty = formattedProperty.replace("SC","Scroll");
                                formattedProperty = formattedProperty.replace("ST","Staff");
                                formattedProperty = formattedProperty.replace("W","Wondrous Item");
                                formattedProperty = formattedProperty.replace("WD","Wand");


                                //appending the correct info to the text for each type of item
                                if (type.equals("Ammunition")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                else if (type.equals("General")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Heavy Armor")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\n" +
                                                    "Armor Class: " + ac + "\t\t\t\t" + "Strength: " + strength);
                                }
                                if (type.equals("Light Armor")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\n" +
                                                    "Armor Class: " + ac);
                                }
                                if (type.equals("Melee Weapon")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\n" +
                                                    "Damage 1: " + dmg1 + "\t\t\t\t" + "Damage 2: " + dmg2 + "\t\t\t\t" + dmgType + "\n" +
                                                    "Property: " + formattedProperty);
                                }
                                if (type.equals("Medium Armor")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\t\t\t\t" + "Armor Class: " + ac);
                                }
                                if (type.equals("Potion")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Ranged Weapon")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\t\t\t\t" + dmgType + "\n" +
                                                    "Property: " + formattedProperty + "\t\t\t\t" //+ "Damage Range: " + dmgRange);
                                    );}
                                if (type.equals("Rod")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Ring")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Shield")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity + "\t\t\t\t" + "Armor Class: " + ac);
                                }
                                if (type.equals("Scroll")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Staff")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Wondrous Item")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
                                if (type.equals("Wand")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);
                                }
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