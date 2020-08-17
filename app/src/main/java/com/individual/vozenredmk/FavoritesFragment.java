package com.individual.vozenredmk;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class FavoritesFragment extends Fragment {
    FavoritesRelationAdapter adapterFavorites;
    RecyclerView recyclerViewFavorites;
    ArrayList<Relation> relationFavorites;
    SwipeRefreshLayout refreshLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain = new Intent(getContext(), MainActivity.class);
                startActivity(goToMain);
            }
        });
        refreshLayout = view.findViewById(R.id.swipeRefresh);
        final DBHelper dbHelper = new DBHelper(getContext());
        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setHasFixedSize(true);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        theMagicFour(dbHelper);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                theMagicFour(dbHelper);
                refreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    public void theMagicFour(DBHelper dbHelper) {
        relationFavorites = (ArrayList<Relation>) dbHelper.getEveryone();
        Collections.sort(relationFavorites, Relation.sortByTime);
        adapterFavorites = new FavoritesRelationAdapter(getContext(), relationFavorites);
        recyclerViewFavorites.setAdapter(adapterFavorites);
    }
}
