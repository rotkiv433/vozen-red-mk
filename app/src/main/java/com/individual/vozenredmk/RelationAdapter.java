package com.individual.vozenredmk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RelationAdapter extends RecyclerView.Adapter<RelationAdapter.RelacijaViewHolder> {

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
    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, int position) {
        holder.relacija.setText(relacii.get(position).getRelacija());
        holder.vremeikompanija.setText(relacii.get(position).getVremeIKompanija());
        holder.cena.setText(relacii.get(position).getCena());
        holder.stanica.setText(relacii.get(position).getStanica());
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
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.star_off));
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.star_on));
                else
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.star_off));
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

    static class RelacijaViewHolder extends RecyclerView.ViewHolder {
        TextView relacija, stanica, cena, vremeikompanija;
        ConstraintLayout expandableLayout;
        ToggleButton toggleButton;

        public RelacijaViewHolder(@NonNull View itemView) {
            super(itemView);
            relacija = (TextView) itemView.findViewById(R.id.post_relacija);
            stanica = (TextView) itemView.findViewById(R.id.post_stanica);
            cena = (TextView) itemView.findViewById(R.id.post_cena);
            vremeikompanija = (TextView) itemView.findViewById(R.id.post_vremeikompanija);
            expandableLayout = (ConstraintLayout) itemView.findViewById(R.id.expandableLayout);
            expandableLayout.setVisibility(View.GONE);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.starFavorite);
        }

    }
}
