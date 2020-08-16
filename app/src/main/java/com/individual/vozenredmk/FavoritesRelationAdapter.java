package com.individual.vozenredmk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class FavoritesRelationAdapter extends RecyclerView.Adapter<RelacijaViewHolder> {
    Context contextFavorites;
    ArrayList<Relation> relationFavorites;
    RecyclerView recyclerViewFavorites;
    ToggleButton heartButton;
    Fragment f;
    DBHelper dbHelper;
    private int lastPosition = -1;


    public FavoritesRelationAdapter(Context contextFavorites, ArrayList<Relation> relationFavorites) {

        this.contextFavorites = contextFavorites;
        this.relationFavorites = relationFavorites;

    }



    @NonNull
    @Override
    public RelacijaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHelper = new DBHelper(contextFavorites);
        View view = LayoutInflater.from(contextFavorites).inflate(R.layout.fragment_favorites, parent, false);
//        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
//        recyclerViewFavorites.setHasFixedSize(true);
//        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(contextFavorites));
//        relationFavorites = (ArrayList<Relation>) dbHelper.getEveryone();
//

        return new RelacijaViewHolder(LayoutInflater.from(contextFavorites).inflate(R.layout.relacii_cardview, parent, false));

    }



    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, final int position) {
        dbHelper = new DBHelper(contextFavorites);
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

            Relation foundIt = dbHelper.getSingleRow(relationFavorites.get(holder.getAdapterPosition()).getId());
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(contextFavorites.getApplicationContext(), R.drawable.ic_baseline_favorite_24));
                    dbHelper.addOne(foundIt);

                }
                else {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(contextFavorites.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
                    dbHelper.deleteOne(foundIt);
                }

            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return relationFavorites.size();
    }

    public void setAnimation(View view, int position) {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(contextFavorites, android.R.anim.slide_in_left);
            animation.setDuration(300);
            view.startAnimation(animation);

            lastPosition = position;
        }
    }
}
