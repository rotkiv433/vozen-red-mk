package com.individual.vozenredmk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    FavoritesRelationAdapter adapterFavorites;
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
        recyclerViewFavorites.setHasFixedSize(true);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        relationFavorites = (ArrayList<Relation>) dbHelper.getEveryone();



        adapterFavorites = new FavoritesRelationAdapter(getContext(), relationFavorites);
        recyclerViewFavorites.setAdapter(adapterFavorites);


        return view;
    }
}
