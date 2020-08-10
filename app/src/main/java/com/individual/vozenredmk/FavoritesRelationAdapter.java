package com.individual.vozenredmk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesRelationAdapter extends RecyclerView.Adapter<RelacijaViewHolder> {
    Context contextFavorites;
    ArrayList<Relation> relationFavorites;

    public FavoritesRelationAdapter(Context contextFavorites, ArrayList<Relation> relationFavorites) {
        this.contextFavorites = contextFavorites;
        this.relationFavorites = relationFavorites;
    }



    @NonNull
    @Override
    public RelacijaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RelacijaViewHolder(LayoutInflater.from(contextFavorites).inflate(R.layout.relacii_cardview, parent, false));

    }



    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, int position) {
        final DBHelper dbHelper = new DBHelper(contextFavorites);
        holder.relacija.setText(relationFavorites.get(position).getRelacija());
        holder.vremeikompanija.setText(relationFavorites.get(position).getVremeIKompanija());
        holder.cena.setText(relationFavorites.get(position).getCena());
        holder.stanica.setText(relationFavorites.get(position).getStanica());
        holder.vremeikompanija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.expandableLayout.getVisibility() == View.GONE) {
                    holder.expandableLayout.setVisibility(View.VISIBLE);
                }
                else
                    holder.expandableLayout.setVisibility(View.GONE);
            }
        });

        holder.toggleButton.setTextOff(null);
        holder.toggleButton.setTextOn(null);
        holder.toggleButton.setText(null);
        holder.toggleButton.setChecked(false);
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(contextFavorites.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
    }

    @Override
    public int getItemCount() {
        return relationFavorites.size();
    }
}
