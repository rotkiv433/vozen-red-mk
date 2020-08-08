package com.individual.vozenredmk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import jrizani.jrspinner.JRSpinner;

public class ChooseRelationActivity extends AppCompatActivity {
    DatabaseReference mReference;
    List<String> gradovi;
    ArrayAdapter<String> adapter;
    Spinner relationFrom, relationTo;
    Button btnBaraj;
    String selectedRelationFrom, selectedRelationTo;
    JRSpinner spinner2;

    private List<String> loadList() {
        List<String> gradovi = new ArrayList<>();
        gradovi.add("Скопје");
        gradovi.add("Куманово");
        gradovi.add("Битола");
        gradovi.add("Прилеп");
        gradovi.add("Велес");
        gradovi.add("Штип");
        gradovi.add("Тетово");
        gradovi.add("Охрид");
        gradovi.add("Гостивар");
        gradovi.add("Струмица");
        gradovi.add("Кичево");
        gradovi.add("Кавадарци");
        gradovi.add("Кочани");

        gradovi.add("test");

        return gradovi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_relation);


        gradovi = loadList();

        //ADAPTER FOR FIRST SPINNER
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gradovi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //SPINNERS
        relationFrom = (Spinner)findViewById(R.id.relationFrom);
        relationTo = (Spinner)findViewById(R.id.relationTo);
        
        relationFrom.setAdapter(adapter);
        relationFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> gradovi2 = loadList();
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(ChooseRelationActivity.this, android.R.layout.simple_spinner_item, gradovi2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selectedRelationFrom = relationFrom.getSelectedItem().toString();

                adapter2.remove(selectedRelationFrom);
                relationTo.setAdapter(adapter2);
                selectedRelationTo = relationTo.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnBaraj = (Button)findViewById(R.id.btnBaraj);
        btnBaraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToListActivity = new Intent(getApplicationContext(), ListActivity.class);
                selectedRelationFrom = relationFrom.getSelectedItem().toString();
                selectedRelationTo = relationTo.getSelectedItem().toString();
                //transferring selected cities
                goToListActivity.putExtra("relationFrom", selectedRelationFrom);
                goToListActivity.putExtra("relationTo", selectedRelationTo);

                startActivity(goToListActivity);

            }
        });
    }

}