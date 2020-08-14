package com.individual.vozenredmk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RelationAdapter extends RecyclerView.Adapter<RelacijaViewHolder> {

    Context context;
    ArrayList<Relation> relacii;

    public RelationAdapter(Context c, ArrayList<Relation> r) {
        context = c;
        relacii = r;
    }

    @NonNull
    @Override
    public RelacijaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RelacijaViewHolder(LayoutInflater.from(context).inflate(R.layout.relacii_cardview, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, final int position) {
        final DBHelper dbHelper = new DBHelper(context);
        holder.relacija.setText(relacii.get(position).getRelacija());
        holder.vremeikompanija.setText(relacii.get(position).getVremeIKompanija());
        holder.cena.setText(relacii.get(position).getCena());
        holder.stanica.setText(relacii.get(position).getStanica());




        //STAR BUTTON
        holder.toggleButton.setTextOff(null);
        holder.toggleButton.setTextOn(null);
        holder.toggleButton.setText(null);
        holder.toggleButton.setChecked(false);
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Relation savedRealtion = new Relation();
                savedRealtion = savedRealtion.createNewRelation(relacii, holder, position);
                if(isChecked) {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_24));
                    dbHelper.addOne(savedRealtion);

                }
                else {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
                    dbHelper.deleteOne(savedRealtion);
                }
            }

        });


    }


    @Override
    public int getItemCount() {
        return relacii.size();
    }

    public void clear() {
        int size = getItemCount();
        relacii.clear();
        notifyItemRangeRemoved(0, size);
    }


}
