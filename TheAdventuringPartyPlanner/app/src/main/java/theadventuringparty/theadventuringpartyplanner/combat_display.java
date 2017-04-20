package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Arrays;

public class combat_display extends AppCompatActivity {
    private TextView monster_display_view;
    private TextView loot_display_view;
    String cr;
    String monUrl;
    String lootUrl;
    String formattedProperty = "";
    String valuesLength = "null";
    SpannableStringBuilder lootDisplay;

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
        String monNum = intent.getStringExtra("enemyNumChoice");



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
            monUrl = "http://cgi.soic.indiana.edu/~team39/this.php?cr="+cr+"&type="+ monType+"&num="+monNum;
        }
        else {
            monUrl = "http://cgi.soic.indiana.edu/~team39/this.php?cr=" + cr + "&type=" + monType.toLowerCase() + "," + spaceVar + "monster" + spaceVar + "manual" + "&num="+ monNum;
        }

        Log.i("monUrl",monUrl);

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

                                int nameLength = name.length();
                                int alignmentLength = alignment.length();
                                int immuneLength = immune.length();
                                int typeLength = type.length();
                                int hpLength = hp.length();
                                int sensesLength = senses.length();
                                int languagesLength = languages.length();
                                int resistLength = resist.length();
                                int traitsLength = traits.length();
                                int speedLength = speed.length();
                                int acLength = ac.length();
                                int strLength = str.length();
                                int dexLength = dex.length();
                                int conLength = con.length();
                                int intelLength = intel.length();
                                int wisLength = wis.length();
                                int chaLength = cha.length();
                                int saveLength = save.length();
                                int skillLength = skill.length();
                                int actionsLength = actions.length();
                                int spellsLength = spells.length();


                                final SpannableStringBuilder monsterString = new SpannableStringBuilder(
                                        name + ": \n" +
                                        "Alignment: " + alignment + "\n" +
                                        "Immune: " + immune + "\n" +
                                        "Type: " + type + "\n" +
                                        "Hit points: " + hp + "\n" +
                                        "Senses: " + senses + "\n" +
                                        "Languages: " + languages + "\n" +
                                        "Resists: " + resist + "\n" +
                                        "Traits: " + traits + "\n" +
                                        "Speed: " + speed + "\n"  +
                                        "Armor Class: " + ac + "\n\n" +
                                        "STR: " + str + "\t\t\t\t\t" + "DEX: " + dex + "\n" +
                                        "CON: " + con + "\t\t\t\t\t" + "INT: " + intel + "\n" +
                                        "WIS: " + wis + "\t\t\t\t\t" + "CHA: " + cha + "\n\n" +
                                        "Saves: " + save + "\n" +
                                        "Skills: " + skill + "\n" +
                                        "Actions: " + actions + "\n" +
                                        "Spells: " + spells + "\n" +
                                        "_______________________________" + "\n\n");


                                //indexes for "Alignment: "
                                int beginAlignment = nameLength + ": \n".length();
                                int endAlignment = beginAlignment + "Alignment: ".length();

                                    //set "Alignment:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginAlignment, endAlignment,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Immune: "
                                int beginImmune = endAlignment + alignmentLength + "\n".length();
                                int endImmune = beginImmune + "Immune: ".length();

                                    //set "Immune:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginImmune, endImmune,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Type: "
                                int beginType = endImmune + immuneLength + "\n".length();
                                int endType = beginType + "Type: ".length();

                                    //set "Type:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginType, endType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Hit points: "
                                int beginHP = endType + typeLength + "\n".length();
                                int endHP = beginHP + "Hit points: ".length();

                                    //set "Hit points:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginHP, endHP,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Senses: "
                                int beginSenses = endHP + hpLength + "\n".length();
                                int endSenses = beginSenses + "Senses: ".length();

                                    //set "Senses:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginSenses, endSenses,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Languages: "
                                int beginLanguages = endSenses + sensesLength + "\n".length();
                                int endLanguages = beginLanguages + "Languages: ".length();

                                    //set "Languages:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginLanguages, endLanguages,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Resists: "
                                int beginResist = endLanguages + languagesLength + "\n".length();
                                int endResist = beginResist + "Resists: ".length();

                                    //set "Resists:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginResist, endResist,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Traits: "
                                int beginTraits = endResist + resistLength + "\n".length();
                                int endTraits = beginTraits + "Traits: ".length();

                                    //set "Traits:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginTraits, endTraits,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Speed: "
                                int beginSpeed = endTraits + traitsLength + "\n".length();
                                int endSpeed = beginSpeed + "Speed: ".length();

                                    //set "Speed:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginSpeed, endSpeed,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Armor Class: "
                                int beginAc = endSpeed + speedLength + "\n".length();
                                int endAc = beginAc + "Armor Class: ".length();

                                    //set "Armor Class:" to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginAc, endAc,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "STR:"
                                int beginStr = endAc + acLength + "\n\n".length();
                                int endStr = beginStr + "STR: ".length();

                                    //set "STR: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginStr, endStr, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "DEX:"
                                int beginDex = endStr +strLength + "\t\t\t\t\t".length();
                                int endDex = beginDex + "DEX: ".length();

                                    //set "DEX: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginDex, endDex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "CON:"
                                int beginCon = endDex + dexLength + "\n".length();
                                int endCon = beginCon + "CON: ".length();

                                    //set "CON: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginCon, endCon, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "INT:"
                                int beginInt = endCon + conLength + "\t\t\t\t\t".length();
                                int endInt = beginInt + "INT: ".length();

                                    //set "INT: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginInt, endInt, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "WIS:"
                                int beginWis = endInt + intelLength + "\n".length();
                                int endWis = beginWis + "WIS: ".length();

                                    //set "WIS: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginWis, endWis, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "CHA:"
                                int beginCha = endWis + wisLength + "\t\t\t\t\t".length();
                                int endCha = beginCha + "CHA: ".length();

                                    //set "CHA: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginCha, endCha, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Saves:"
                                int beginSaves = endCha + chaLength + "\n\n".length();
                                int endSaves = beginSaves + "Saves: ".length();

                                    //set "Saves: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginSaves, endSaves, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Skills:"
                                int beginSkills = endSaves + saveLength + "\n".length();
                                int endSkills = beginSkills + "Skills: ".length();

                                    //set "Skills: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginSkills, endSkills, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Actions:"
                                int beginActions = endSkills + skillLength + "\n".length();
                                int endActions = beginActions + "Actions: ".length();

                                    //set "Actions: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginActions, endActions, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //indexes for "Spells:"
                                int beginSpells = endActions + actionsLength + "\n".length();
                                int endSpells = beginSpells + "Spells: ".length();

                                    //set "Spells: " to bold
                                    monsterString.setSpan(new StyleSpan(Typeface.BOLD), beginSpells, endSpells, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                                //set Monster name to bold and size 80
                                monsterString.setSpan(new StyleSpan(Typeface.BOLD), 0, nameLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                monsterString.setSpan(new AbsoluteSizeSpan(80),0, nameLength+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //sets text to size 50
                                monsterString.setSpan(new AbsoluteSizeSpan(50),nameLength + 2,monsterString.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                //appends the string to the output field
                                monster_display_view.append(monsterString);

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
                                //monster_display_view.append(error.toString());
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
                                String weight = loot.getString("weight");
                                String ac = loot.getString("ac");
                                String stealth = loot.getString("stealth");
                                String rarity = loot.getString("rarity");
                                String strength = loot.getString("strength");
                                String dmg1 = loot.getString("dmg1");
                                String dmg2 = loot.getString("dmg2");
                                String dmgType = loot.getString("dmgType");
                                String property = loot.getString("property");
                                //String dmgRange = loot.getString("dmgRange");
                                Log.i("Property", property);


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



                                //changing loot property to the full property
                                if (property != null) {

                                    String[] values = property.split(",");
                                    Log.d("ArrayList:", Arrays.toString(values));

                                    if (values[0] != null) {
                                        valuesLength = Integer.toString(values.length);
                                    }

                                    Log.d("The length of values is" , valuesLength);

                                    for (int x = 0; x < values.length; x++) {

                                        Log.d("x is currently:", Integer.toString(x));

                                        if (values[x].equals("2H")) {
                                            values[x] = "Two-Handed";
                                            formattedProperty += values[x];
                                            Log.d("'Two-Handed' added.", values[x].toString());
                                        } else if (values[x].equals("A")) {
                                            values[x] = "Ammunition";
                                            formattedProperty += values[x];
                                            Log.d("'Ammunition' added.", values[x].toString());
                                        } else if (values[x].equals("H")) {
                                            values[x] = "Heavy";
                                            formattedProperty += values[x];
                                            Log.d("'Heavy' added.", values[x].toString());
                                        } else if (values[x].equals("LD")) {
                                            values[x] = "Loading";
                                            formattedProperty += values[x];
                                            Log.d("'Loading' added.", values[x].toString());
                                        } else if (values[x].equals("F")) {
                                            values[x] = "Finesse";
                                            formattedProperty += values[x];
                                            Log.d("'Finesse' added.", values[x].toString());
                                        } else if (values[x].equals("L")) {
                                            values[x] = "Light";
                                            formattedProperty += values[x];
                                            Log.d("'Light' added.", values[x].toString());
                                        } else if (values[x].equals("T")) {
                                            values[x] = "Thrown";
                                            formattedProperty += values[x];
                                            Log.d("'Thrown' added.", values[x].toString());
                                        } else if (values[x].equals("R")) {
                                            values[x] = "Reach";
                                            formattedProperty += values[x];
                                            Log.d("'Reach' added.", values[x].toString());
                                        } else if (values[x].equals("V")) {
                                            values[x] = "Versatile";
                                            formattedProperty += values[x];
                                            Log.d("'Versatile' added.", values[x].toString());
                                        } else if (values[x].equals("S")) {
                                            values[x] = "Special";
                                            formattedProperty += values[x];
                                            Log.d("'Special' added.", values[x].toString());
                                        }

                                        if (x + 1 != values.length) {
                                            if (values[x] != null){
                                                formattedProperty += ", ";
                                            }
                                        }
                                    }
                                }


                                int lootTypeLength = type.length();
                                int lootNameLength = name.length();
                                int lootWeightLength = weight.length();
                                int lootAcLength = ac.length();
                                int lootStealthLength = stealth.length();
                                int lootRarityLength = rarity.length();
                                int lootStrengthLength = strength.length();
                                int lootDmg1Length = dmg1.length();
                                int lootDmg2Length = dmg2.length();
                                int lootDmgTypeLength = dmgType.length();
                                int lootPropertyLength = property.length();

                                //appending the correct info to the text for each type of item
                                if (type.equals("Ammunition")){
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                else if (type.equals("General")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + " Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Heavy Armor")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                            "Armor Class: " + ac + "\t\t\t\t" + "Strength: " + strength);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();
                                    //indexes for "Armor Class"
                                    int beginLootAC = endLootRarity + ": ".length() + lootRarityLength + "\n".length();
                                    int endLootAC = beginLootAC + "Armor Class".length();
                                    //indexes for "Strength"
                                    int beginLootStrength = endLootAC + ": ".length() + lootAcLength + "\t\t\t\t".length();
                                    int endLootStrength = beginLootStrength + "Strength".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Armor class" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootAC, endLootAC,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Strength" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStrength, endLootStrength,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Light Armor")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                            "Armor Class: " + ac);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();
                                    //indexes for "Armor Class"
                                    int beginLootAC = endLootRarity + ": ".length() + lootRarityLength + "\n".length();
                                    int endLootAC = beginLootAC + "Armor Class".length();


                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Armor class" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootAC, endLootAC,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Melee Weapon")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(
                                            name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                            "Damage 1: " + dmg1 + " (" + dmgType + ")"+ "\t\t\t\t" + "Damage 2: " + dmg2 + "(" + dmgType + ")\n" +
                                            "Property: " + formattedProperty);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();
                                    //indexes for "Damage 1"
                                    int beginLootDmg1 = endLootRarity + ": ".length() + lootRarityLength + "\n".length();
                                    int endLootDmg1 = beginLootDmg1 + "Damage 1".length();
                                    //indexes for "Damage 2"
                                    int beginLootDmg2 = endLootDmg1 + ": ".length() + lootDmg1Length + " (".length() + lootDmgTypeLength + ")".length()+ "\t\t\t\t".length();
                                    int endLootDmg2 = beginLootDmg2 + "Damage 2".length();
                                    //indexes for "Property"
                                    int beginLootProperty = endLootDmg2 + ": ".length() + lootDmg2Length + "(".length() + lootDmgTypeLength + ")\n".length();
                                    int endLootProperty = beginLootProperty + "Property".length();


                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Damage 1" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootDmg1, endLootDmg1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Damage 2" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootDmg2, endLootDmg2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Property" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootProperty, endLootProperty,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Medium Armor")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                            "Armor Class: " + ac);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();
                                    //indexes for "Armor Class"
                                    int beginLootAC = endLootRarity + ": ".length() + lootRarityLength + "\n".length();
                                    int endLootAC = beginLootAC + "Armor Class".length();


                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Armor class" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootAC, endLootAC,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Potion")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Ranged Weapon")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder(name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                            "Damage type: " + dmgType + "\n" +
                                            "Property: " + formattedProperty + "\t\t\t\t");

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();
                                    //indexes for "Damage Type"
                                    int beginLootDmgType = endLootRarity + ": ".length() + lootRarityLength + "\n".length();
                                    int endLootDmgType = beginLootDmgType + "Damage type".length();
                                    //indexes for "Property"
                                    int beginLootProperty = endLootDmgType + ": ".length() + lootDmgTypeLength + "\n".length();
                                    int endLootProperty = beginLootProperty + "Property".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Damage type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootDmgType, endLootDmgType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Property" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootProperty, endLootProperty,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Rod")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Ring")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Shield")) {
                                    loot_display_view.append(
                                            name + "\n" + "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                                    "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity + "\n" +
                                                    "Armor Class: " + ac);
                                }
                                if (type.equals("Scroll")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Staff")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Wondrous Item")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
                                }
                                if (type.equals("Wand")) {
                                    SpannableStringBuilder lootDisplay = new SpannableStringBuilder( name + "\n" +
                                            "Type: " + type + "\t\t\t\t" + "Weight: " + weight + "\n" +
                                            "Stealth: " + stealth + "\t\t\t\t" + "Rarity: " + rarity);

                                    //indexes for "name"
                                    int beginLootName = 0;
                                    int endLootName = beginLootName + lootNameLength;
                                    //indexes for "Type"
                                    int beginLootType = endLootName + "\n".length();
                                    int endLootType = beginLootType + "Type".length();
                                    //indexes for "Weight"
                                    int beginLootWeight = endLootType + ": ".length() + lootTypeLength + "\t\t\t\t".length();
                                    int endLootWeight = beginLootWeight + "Weight".length();
                                    //indexes for "Stealth"
                                    int beginLootStealth = endLootWeight + ": ".length() + lootWeightLength + "\n".length();
                                    int endLootStealth = beginLootStealth + "Stealth".length();
                                    //indexes for "Rarity"
                                    int beginLootRarity = endLootStealth + ": ".length() + lootStealthLength + "\t\t\t\t".length();
                                    int endLootRarity = beginLootRarity + "Rarity".length();

                                    //set "name" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootName, endLootName,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "type" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootType, endLootType,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "weight" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootWeight, endLootWeight,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Stealth" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootStealth, endLootStealth,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    //set "Rarity" to bold
                                    lootDisplay.setSpan(new StyleSpan(Typeface.BOLD), beginLootRarity, endLootRarity,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    loot_display_view.append(lootDisplay);
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