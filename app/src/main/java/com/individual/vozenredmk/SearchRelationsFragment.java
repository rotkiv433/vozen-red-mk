package com.individual.vozenredmk;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SearchRelationsFragment extends Fragment {
    Spinner relationFrom, relationTo;
    Button btnBaraj;
    String selectedRelationFrom, selectedRelationTo;
    List<String> gradovi;
    ArrayAdapter<String> adapter;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        gradovi = loadList();

        //ADAPTER FOR FIRST SPINNER
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gradovi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btnBaraj = (Button)view.findViewById(R.id.btnBaraj);
        btnBaraj.setOnClickListener(btnBarajListener);

        //SPINNERS
        relationFrom = (Spinner)view.findViewById(R.id.relationFrom);
        relationTo = (Spinner)view.findViewById(R.id.relationTo);

        relationFrom.setAdapter(adapter);
        relationFrom.setOnItemSelectedListener(relationFromListener);




        return view;
    }

    private Spinner.OnItemSelectedListener relationFromListener = new Spinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            List<String> gradovi2 = loadList();
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(parent.getContext(), android.R.layout.simple_spinner_item, gradovi2);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selectedRelationFrom = relationFrom.getSelectedItem().toString();

            adapter2.remove(selectedRelationFrom);
            relationTo.setAdapter(adapter2);
            selectedRelationTo = relationTo.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    };

    private Button.OnClickListener btnBarajListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent goToListActivity = new Intent(getContext(), ListActivity.class);
            selectedRelationFrom = relationFrom.getSelectedItem().toString();
            selectedRelationTo = relationTo.getSelectedItem().toString();
            //transferring selected cities
            goToListActivity.putExtra("relationFrom", selectedRelationFrom);
            goToListActivity.putExtra("relationTo", selectedRelationTo);
            startActivity(goToListActivity);
        }
    };
}
