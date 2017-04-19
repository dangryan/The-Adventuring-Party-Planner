package theadventuringparty.theadventuringpartyplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Tab2Event extends Fragment{
    Spinner mEventTypeSpinner;
    private TextView mEventOutput;
    String eventUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.noncombat_encounter_generator, container, false);

        mEventTypeSpinner = (Spinner) rootView.findViewById(R.id.eventTypeSpinner);

        mEventOutput = (TextView) rootView.findViewById(R.id.campaignMotivationTextView);

        mEventOutput.setMovementMethod(new ScrollingMovementMethod());


        rootView.findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventTypeChoice = mEventTypeSpinner.getSelectedItem().toString();

                eventUrl = "http://cgi.soic.indiana.edu/~team39/event.php?type=" + eventTypeChoice;

                // Add if here to account for different Output functions for each Event Type
                if (eventTypeChoice.equals("Dungeon")) {
                    displayDungeonOutput(eventUrl);
                }
                else if (eventTypeChoice.equals("Rumor")) {
                    displayRumorOutput(eventUrl);
                }
                else if (eventTypeChoice.equals("Stranger")) {
                    displayStrangerOutput(eventUrl);
                }
                else if (eventTypeChoice.equals("Villain")) {
                    displayVillainOutput(eventUrl);
                }
                else if (eventTypeChoice.equals("Quest")) {
                    displayQuestOutput(eventUrl);
                }
                else {
                    displayVillageTaskOutput(eventUrl);
                }

            }

            //You'll have to make one of these for each Event Type (Dungeon, Rumor, Stranger, Villain, VillageTask, Quest)
            public void displayDungeonOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject dungeon = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String type = dungeon.getString("type");
                                        String origin = dungeon.getString("origin");
                                        String location = dungeon.getString("location");
                                        String occupants = dungeon.getString("occupants");
                                        String additional_occupants = dungeon.getString("additional_occupants");
                                        String challenge = dungeon.getString("challenge");
                                        String loot = dungeon.getString("loot");


                                        mEventOutput.append(
                                                "This place is (or was) a " + type
                                                        + " built by " + origin
                                                        + " and located " + location + ". "
                                                        + "The place is currently occupied by " + occupants
                                                        + " and some " + additional_occupants + ". "
                                                        + "If you survive the " + challenge
                                                        + ", you might recover the " + loot + ".");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }

            //Rumor Event Output
            public void displayRumorOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject rumor = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String time = rumor.getString("time");
                                        String subject1 = rumor.getString("subject1");
                                        String subject2 = rumor.getString("subject2");
                                        String location = rumor.getString("location");
                                        String subject3 = rumor.getString("subject3");
                                        String source = rumor.getString("source");
                                        String veracity = rumor.getString("veracity");


                                        mEventOutput.append(
                                                "I heard that, " + time + ", " + subject1
                                                        + " was seen with " + subject2
                                                        + " down near " + location
                                                        + " and nearby there was " + subject3 + ". "
                                                        + "I heard it from " + source
                                                        + ", so it " + veracity + ".");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }

            public void displayStrangerOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject stranger = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String age = stranger.getString("age");
                                        String sex = stranger.getString("sex");
                                        String hair_type = stranger.getString("hair_type");
                                        String hair_color = stranger.getString("hair_color");
                                        String hair_style = stranger.getString("hair_style");
                                        String mark = stranger.getString("mark");
                                        String home = stranger.getString("home");
                                        String home_location = stranger.getString("home_location");
                                        String occupation = stranger.getString("occupation");
                                        String renown = stranger.getString("renown");
                                        String build = stranger.getString("build");
                                        String descriptor = stranger.getString("descriptor");
                                        String speech = stranger.getString("speech");
                                        String motive = stranger.getString("motive");


                                        mEventOutput.append(
                                                "The stranger is " + age + " " + sex + " who is " + build
                                                        + " with " + hair_type + " " + hair_color + " " + hair_style
                                                        + " and " + mark + "."
                                                        + " (S)he talks " + speech + " and has " + descriptor + "."
                                                        + " (S)he is " + occupation + " who is living " + home + " " + home_location + "."
                                                        + " People in town " + renown + " him/her. "
                                                        + "All (s)he really wants is to " + motive + ".");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }

            public void displayVillainOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject villain = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String origin = villain.getString("origin");
                                        String time_turned = villain.getString("time_turned");
                                        String time_to_turn = villain.getString("time_to_turn");
                                        String reason_turned = villain.getString("reason_turned");
                                        String unique_trait = villain.getString("unique_trait");
                                        String goal = villain.getString("goal");
                                        String opposition = villain.getString("opposition");
                                        String race = villain.getString("race");
                                        String leadership_quality = villain.getString("leadership_quality");
                                        String minions = villain.getString("minions");
                                        String redeeming_quality = villain.getString("redeeming_quality");


                                        mEventOutput.append(
                                                "The villain started as " + origin + ". "
                                                        + "(S)he turned " + time_turned + ", " + time_to_turn + ". "
                                                        + "The villain turned because " + reason_turned + ".\n"
                                                        + "The villain is a " + race + " with " + unique_trait + ".\n"
                                                        + "The villain's current goal is " + goal + ". "
                                                        + "(S)he leads through " + leadership_quality + " and his/her minions are " + minions + ".\n"
                                                        + "Despite being a villain, his/her qualities include " + redeeming_quality + ".\n"
                                                        + "The villain will oppose the party " + opposition + ". ");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }

            public void displayVillageTaskOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject villageTask = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String subject = villageTask.getString("subject");
                                        String problem = villageTask.getString("problem");
                                        String location = villageTask.getString("location");
                                        String clue = villageTask.getString("clue");
                                        String suspect_it_involves = villageTask.getString("suspect_it_involves");
                                        String impeder = villageTask.getString("impeder");
                                        String attempt_to_help = villageTask.getString("attempt_to_help");
                                        String reward = villageTask.getString("reward");



                                        mEventOutput.append(
                                                "A peasant runs over to you, shouting:\nYou've got to help! There is a " + subject
                                                        + " who " + problem
                                                        + " at " + location + ". "
                                                        + "We found " + clue + ", "
                                                        + "which we suspect involves " + suspect_it_involves + ", "
                                                        + "but the " + impeder
                                                        + " is/are stopping us from " + attempt_to_help + ". "
                                                        + "Please! If you can help, I'll give you " + reward + ". ");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }


            public void displayQuestOutput(String url){
                Context mContext = getActivity().getApplicationContext();


                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        eventUrl,
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
                                        JSONObject quest = response.getJSONObject(i);

                                        // Get the current event (json object) data

                                        String location = quest.getString("location");
                                        String task = quest.getString("task");
                                        String task_focus = quest.getString("task_focus");
                                        String guarded_by = quest.getString("guarded_by");
                                        String alertness = quest.getString("alertness");
                                        String time_limit = quest.getString("time_limit");
                                        String twist = quest.getString("twist");
                                        String consequence = quest.getString("consequence");



                                        mEventOutput.append(
                                                "The party must infiltrate " + location
                                                        + " to " + task + " " + task_focus + ".\n"
                                                        + "The place is guarded by " + guarded_by
                                                        + " who are " + alertness + ".\n"
                                                        + "The party needs to do this before " + time_limit + ", "
                                                        + "but it's not that easy because they discover that what/whom they are seeking is " + twist + ".\n"
                                                        + "If they fail, they will be " + consequence + ". ");
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
                                mEventOutput.append("\n Something went wrong. \n");
                            }
                        }
                );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);

                mEventOutput.setText("");
            }



        });

        return rootView;
    }
}
