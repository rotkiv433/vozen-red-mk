package com.individual.vozenredmk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    RelationAdapter adapterFavorites;
    RecyclerView recyclerViewFavorites;
    ArrayList<Relation> relationFavorites;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        DBHelper dbHelper = new DBHelper(getContext());
        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setHasFixedSize(false);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        relationFavorites = (ArrayList<Relation>) dbHelper.getEveryone();

        adapterFavorites = new RelationAdapter(getContext(), relationFavorites);
        recyclerViewFavorites.setAdapter(adapterFavorites);
//        relationArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dbHelper.getEveryone());
//        listViewRelations = view.findViewById(R.id.listViewFavorites);
//        listViewRelations.setAdapter(relationArrayAdapter);

        return view;
    }
}
