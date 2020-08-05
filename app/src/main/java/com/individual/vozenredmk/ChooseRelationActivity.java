package com.individual.vozenredmk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ChooseRelationActivity extends AppCompatActivity {

    private List<String> loadList() {
        List<String> gradovi = new ArrayList<>();
        gradovi.add("Скопје");
        gradovi.add("Куманово");
        gradovi.add("Битола");
        gradovi.add("Прилеп");
        gradovi.add("Велес");

        return gradovi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_relation);


        final List<String> gradovi = loadList();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gradovi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner relationFrom = (Spinner)findViewById(R.id.relationFrom);
        final Spinner relationTo = (Spinner)findViewById(R.id.relationTo);
        relationFrom.setAdapter(adapter);

        relationFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> gradovi2 = loadList();
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(ChooseRelationActivity.this, android.R.layout.simple_spinner_item, gradovi2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                String selectedRelationFrom = relationFrom.getSelectedItem().toString();
                adapter2.remove(selectedRelationFrom);
                relationTo.setAdapter(adapter2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        relationFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


    }


}