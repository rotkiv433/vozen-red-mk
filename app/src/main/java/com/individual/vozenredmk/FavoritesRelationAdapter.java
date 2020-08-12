package com.individual.vozenredmk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesRelationAdapter extends RecyclerView.Adapter<RelacijaViewHolder> {
    Context contextFavorites;
    ArrayList<Relation> relationFavorites;
    RecyclerView recyclerViewFavorites;
    Fragment f;

    public FavoritesRelationAdapter(Context contextFavorites, ArrayList<Relation> relationFavorites) {
        this.contextFavorites = contextFavorites;
        this.relationFavorites = relationFavorites;

    }



    @NonNull
    @Override
    public RelacijaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextFavorites).inflate(R.layout.fragment_favorites, parent, false);
        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
        return new RelacijaViewHolder(LayoutInflater.from(contextFavorites).inflate(R.layout.relacii_cardview, parent, false));

    }



    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, final int position) {
        final DBHelper dbHelper = new DBHelper(contextFavorites);
        holder.relacija.setText(relationFavorites.get(position).getRelacija());
        holder.vremeikompanija.setText(relationFavorites.get(position).getVremeIKompanija());
        holder.cena.setText(relationFavorites.get(position).getCena());
        holder.stanica.setText(relationFavorites.get(position).getStanica());


        holder.toggleButton.setTextOff(null);
        holder.toggleButton.setTextOn(null);
        holder.toggleButton.setText(null);
        holder.toggleButton.setChecked(true);
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(contextFavorites.getApplicationContext(), R.drawable.ic_baseline_favorite_24));
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Relation foundIt = dbHelper.getSingleRow(relationFavorites.get(position).getId());
                dbHelper.deleteOne(foundIt);
                relationFavorites = (ArrayList<Relation>) dbHelper.getEveryone();
                recyclerViewFavorites.setAdapter(new FavoritesRelationAdapter(contextFavorites, relationFavorites));
                recyclerViewFavorites.getAdapter();
            }
        });
    }

    @Override
    public int getItemCount() {
        return relationFavorites.size();
    }
}
