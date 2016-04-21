package com.example.cjs2599.cs329e_hwk4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements OnItemSelectedListener {

    private static Button introBTN;
    private static Button mapsBTN;
    private Spinner dropdownSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        introBTN = (Button) findViewById(R.id.button);
        mapsBTN = (Button) findViewById(R.id.button2);
        dropdownSpinner = (Spinner) findViewById(R.id.spinner);

        dropdownSpinner.setOnItemSelectedListener(this);
        //introBTN.setOnClickListener(this);
        //mapsBTN.setOnClickListener(this);

        List<String> categories = new ArrayList<>();
        categories.add("UT Tower");
        categories.add("Gates Dell Complex");
        categories.add("College Of Liberal Arts");
        categories.add("Turtle Pond");
        categories.add("TEXAS FIGHT!");

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdownSpinner.setAdapter(listAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {}



}
