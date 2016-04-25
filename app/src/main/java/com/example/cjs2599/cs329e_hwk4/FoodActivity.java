package com.example.cjs2599.cs329e_hwk4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FoodActivity extends AppCompatActivity {

    private Spinner spnFood;
    private Button btnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        spnFood = (Spinner) findViewById(R.id.spinnerFood);
        btnFood = (Button) findViewById(R.id.btnFood);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(this, R.array.food_places, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFood.setAdapter(priceAdapter);

        setButtonOnClickListener();
    }

    private void setButtonOnClickListener() {
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = 0;
                double lng = 0;
                int position = spnFood.getSelectedItemPosition();
                String name = spnFood.getItemAtPosition(position).toString();
                switch (position) {
                    case 0:
                        lat = 30.2824676;
                        lng = -97.7363361;
                        break;

                    case 1:
                        lat = 30.2903787;
                        lng = -97.7396316;
                        break;

                    case 2:
                        lat = 30.2848496;
                        lng = -97.7362363;
                        break;
                }
                Intent i = new Intent(FoodActivity.this, MapsActivity.class);
                i.putExtra("name", name);
                i.putExtra("lat", lat);
                i.putExtra("lng", lng);
                startActivity(i);
            }
        });
    }
}
