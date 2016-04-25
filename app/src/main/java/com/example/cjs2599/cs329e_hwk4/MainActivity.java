package com.example.cjs2599.cs329e_hwk4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity implements OnItemSelectedListener {

    private static Button introBTN;
    private static Button mapsBTN;
    private Spinner dropdownSpinner;

    MediaPlayer fightSong;
    int paused;

    private HashMap<String,String> urlBinds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introBTN = (Button) findViewById(R.id.button);
        mapsBTN = (Button) findViewById(R.id.button2);
        dropdownSpinner = (Spinner) findViewById(R.id.spinner);
        dropdownSpinner.setSelection(0);

        dropdownSpinner.setOnItemSelectedListener(this);
        introBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == introBTN.getId()) {
                    Intent i = new Intent(MainActivity.this, BuildingInfoActivity.class);
                    i.setData(Uri.parse("http://www.youtube.com/watch?v=kf30sHwKudI"));
                    Log.i("intent", "Intent Processed");
                    startActivity(i);
                }
            }
        });

        mapsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == mapsBTN.getId()) {
                    Intent i = new Intent(MainActivity.this, FoodActivity.class);
//                    i.setData(Uri.parse("http://www.youtube.com/watch?v=kf30sHwKudI"));
                    startActivity(i);
                }
        }});

        //mapsBTN.setOnClickListener(this);

        List<String> categories = new ArrayList<>();
        categories.add("Select a building...");
        categories.add("UT Tower");
        categories.add("Gates Dell Complex");
        categories.add("College of Liberal Arts");
        categories.add("Student Activity Center");
        categories.add("Student Union");
        categories.add("TEXAS FIGHT!");

        urlBinds = new HashMap<>();
        urlBinds.put("UT Tower", "https://www.utexas.edu/maps/main/buildings/mai.html");
        urlBinds.put("Gates Dell Complex", "https://www.utexas.edu/maps/main/buildings/gdc.html");
        urlBinds.put("College of Liberal Arts", "https://www.utexas.edu/maps/main/buildings/cla.html");
        urlBinds.put("Student Activity Center", "https://www.utexas.edu/maps/main/buildings/sac.html");
        urlBinds.put("Student Union", "https://www.utexas.edu/maps/main/buildings/unb.html");



        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdownSpinner.setAdapter(listAdapter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(fightSong!=null) {
            stop(dropdownSpinner);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        dropdownSpinner.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String selection = parent.getItemAtPosition(position).toString();
        if(!selection.equals("Select a building...")){
            if(selection.equals("TEXAS FIGHT!")) {

                play(parent);

            } else {

                String item = urlBinds.get(selection);
                Log.v("SPINNER", item);

                Intent building = new Intent(MainActivity.this, BuildingInfoActivity.class);
                building.setData(Uri.parse(item));
                startActivity(building);

            }
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {}

    public void pause(View view) {
        fightSong.pause();
        paused=fightSong.getCurrentPosition();
    }

    public void stop(View view) {
        fightSong.release();
        fightSong=null;

    }

    public void play(View view) {
        if(fightSong==null) {
            fightSong = MediaPlayer.create(this, R.raw.texas_fight);
            fightSong.start();
        } else if (!fightSong.isPlaying()){
            fightSong.seekTo(paused);
            fightSong.start();
        }
    }

}
