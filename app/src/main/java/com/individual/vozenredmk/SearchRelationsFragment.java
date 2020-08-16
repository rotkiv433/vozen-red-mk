package com.individual.vozenredmk;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class SearchRelationsFragment extends Fragment {
    Button btnBaraj, relationFrom, relationTo;
    String selectedRelationFrom, selectedRelationTo;
    List<String> gradovi;
    ArrayAdapter<String> adapter;
    SpinnerDialog spinnerDialog, spinnerDialog2;
    private int pozicija = -1;


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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        gradovi = loadList();
        spinnerDialog = new SpinnerDialog(getActivity(), (ArrayList<String>) gradovi, "Од град:",R.style.DialogAnimations_SmileWindow, "Затвори");
        spinnerDialog.setCancellable(true);
        spinnerDialog.setShowKeyboard(true);
//        spinnerDialog2.setSearchIconColor(getResources().getColor(R.color.purple));
//        spinnerDialog2 = new SpinnerDialog(getActivity(),(ArrayList<String>) gradovi, "До град:", R.style.DialogAnimations_SmileWindow, "Затвори");
//        spinnerDialog2.setCancellable(true);
//        spinnerDialog2.setShowKeyboard(false);
        //ADAPTER FOR FIRST SPINNER
//        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gradovi);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btnBaraj = (Button)view.findViewById(R.id.btnBaraj);
        relationFrom = (Button)view.findViewById(R.id.relationFrom);
        relationTo = (Button)view.findViewById(R.id.relationTo);
        relationTo.setEnabled(false);



        //SPINNERS
//        relationFrom = (Spinner)view.findViewById(R.id.relationFrom);
//        relationTo = (Spinner)view.findViewById(R.id.relationTo);
//
//        relationFrom.setAdapter(adapter);
//        relationFrom.setOnItemSelectedListener(relationFromListener);

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                relationFrom.setText(item);
                relationFrom.setBackgroundResource(R.drawable.button_bg_clicked);
                relationFrom.setTextColor(Color.WHITE);
                relationTo.setEnabled(true);
                relationTo.setText("");
                relationTo.setBackgroundResource(R.drawable.button_bg);
                relationTo.setTextColor(Color.BLACK);
                btnBaraj.setVisibility(View.GONE);
                List<String> gradovi2 = loadList();
                gradovi2.remove(position);
                spinnerDialog2 = new SpinnerDialog(getActivity(),(ArrayList<String>) gradovi2, "До град:", R.style.DialogAnimations_SmileWindow, "Затвори");
                spinnerDialog2.setCancellable(true);
                spinnerDialog2.setShowKeyboard(true);
                spinnerDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item, int position) {
                        relationTo.setText(item);
                        relationTo.setBackgroundResource(R.drawable.button_bg_clicked);
                        relationTo.setTextColor(Color.WHITE);
                        Transition transition = new Fade();
                        transition.setDuration(1000);
                        transition.addTarget(R.id.btnBaraj);
                        TransitionManager.beginDelayedTransition(container, transition);
                        btnBaraj.setVisibility(View.VISIBLE);
                    }
                });
            }
        });







        relationFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
        relationTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog2.showSpinerDialog();
            }
        });

        btnBaraj.setOnClickListener(btnBarajListener);

        return view;
    }

//    private Spinner.OnItemSelectedListener relationFromListener = new Spinner.OnItemSelectedListener() {
//
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            List<String> gradovi2 = loadList();
//            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(parent.getContext(), android.R.layout.simple_spinner_item, gradovi2);
//            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            selectedRelationFrom = relationFrom.getSelectedItem().toString();
//
//            adapter2.remove(selectedRelationFrom);
//            relationTo.setAdapter(adapter2);
//            selectedRelationTo = relationTo.getSelectedItem().toString();
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//
//    };

    private Button.OnClickListener btnBarajListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent goToListActivity = new Intent(getContext(), ListActivity.class);
            selectedRelationFrom = relationFrom.getText().toString();
            selectedRelationTo = relationTo.getText().toString();
            //transferring selected cities
            goToListActivity.putExtra("relationFrom", selectedRelationFrom);
            goToListActivity.putExtra("relationTo", selectedRelationTo);
            startActivity(goToListActivity);
        }
    };
}
