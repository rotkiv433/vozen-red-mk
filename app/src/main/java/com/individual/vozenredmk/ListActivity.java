package com.individual.vozenredmk;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerViewList;
    DatabaseReference mReference;
    ArrayList<Relation> relacii;
    ArrayList<Relation> relaciiOutOfTime;
    String relationFrom, relationTo;
    RelationAdapter adapter;
    BottomNavigationView bottomNav;
    ProgressBar loading;
    ConstraintLayout mainConstraint;
    Switch allRelations;
    FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.relacii_cardview, null);
        //THESE TWO STRINGS ARE TRANSFERED FROM PREVIOUS ACTIVITY
        relationFrom = getIntent().getStringExtra("relationFrom");
        relationTo = getIntent().getStringExtra("relationTo");
        loading = findViewById(R.id.loading);
        allRelations = findViewById(R.id.switchAllRelations);
        fragmentContainer = findViewById(R.id.fragment_container);

//        mainConstraint = view.findViewById(R.id.mainConstraint);
//        mainConstraint.setBackgroundResource(R.color.purple);
        recyclerViewList = (RecyclerView)findViewById(R.id.myRecycleView);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        relacii = new ArrayList<>();
        relaciiOutOfTime = new ArrayList<>();
        bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



        mReference = FirebaseDatabase.getInstance().getReference().child("relacii");
        final String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());


        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Relation r = ds.getValue(Relation.class);
                    assert r != null;
                    String relationTime = r.getVreme();
                    if(relationFrom.equals(r.getStart()) && relationTo.equals(r.getEnd())) {
                        if(relationTime.compareTo(currentTime) > 0) {
                            relacii.add(r);
                        }
                        else {
                            relaciiOutOfTime.add(r);
                        }
                    }
                    else {
                        relacii.remove(r);
                    }


                }

                if(relacii.size() == 0) {
                    Toast.makeText(ListActivity.this, "Нема автобуски линии за оваа релација!", Toast.LENGTH_SHORT).show();
                }

                Collections.sort(relacii, Relation.sortByTime);
                System.out.println(relacii);
                adapter = new RelationAdapter(ListActivity.this, relacii);
                recyclerViewList.setAdapter(adapter);
                loading.setVisibility(View.GONE);
                allRelations.setVisibility(View.VISIBLE);

                allRelations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            allRelations.setText("Сите релации ("+relationFrom+" - "+relationTo+")");
                            ArrayList<Relation> result = new ArrayList<>();
                            result.addAll(relacii);
                            System.out.println(relacii);
                            result.addAll(relaciiOutOfTime);
                            Collections.sort(result, Relation.sortByTime);
                            System.out.println(result);
                            adapter = new RelationAdapter(ListActivity.this, result);
                            recyclerViewList.setAdapter(adapter);
                        }
                        else {
                            allRelations.setText("Во живо релации ("+relationFrom+" - "+relationTo+")");
                            adapter = new RelationAdapter(ListActivity.this, relacii);
                            recyclerViewList.setAdapter(adapter);
                            System.out.println(relacii);
                        }
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListActivity.this, "Настана грешка!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        adapter.clear();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            adapter.clear();
            allRelations.setVisibility(View.GONE);

            switch (item.getItemId()) {

                case R.id.nav_search:
                    selectedFragment = new SearchRelationsFragment();
                    break;
                case R.id.nav_favorites:
                    selectedFragment = new FavoritesFragment();
                    break;

            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        }
    };

}
