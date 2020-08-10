package com.individual.vozenredmk;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerViewList;
    DatabaseReference mReference;
    ArrayList<Relation> relacii;
    String relationFrom, relationTo;
    RelationAdapter adapter;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //THESE TWO STRINGS ARE TRANSFERED FROM PREVIOUS ACTIVITY
        relationFrom = getIntent().getStringExtra("relationFrom");
        relationTo = getIntent().getStringExtra("relationTo");

        recyclerViewList = (RecyclerView)findViewById(R.id.myRecycleView);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        relacii = new ArrayList<>();
        bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        mReference = FirebaseDatabase.getInstance().getReference().child("relacii");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Relation r = ds.getValue(Relation.class);
                    assert r != null;
                    if(relationFrom.equals(r.getStart()) && relationTo.equals(r.getEnd())) {
                        relacii.add(r);
                        System.out.println(relacii.toString());
                    }
                    else {
                        relacii.remove(r);
                    }
                }

                if(relacii.size() == 0) {
                    Toast.makeText(ListActivity.this, "Нема автобуски линии за оваа релација!", Toast.LENGTH_SHORT).show();
                }



                adapter = new RelationAdapter(ListActivity.this, relacii);
                recyclerViewList.setAdapter(adapter);

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
            switch (item.getItemId()) {

                case R.id.nav_search:
                    selectedFragment = new SearchRelationsFragment();
                    break;
                case R.id.nav_favorites:
                    selectedFragment = new FavoritesFragment();
                    break;
                case R.id.nav_allRelations:
                    selectedFragment = new AllRelationsFragment();
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        }
    };

}
