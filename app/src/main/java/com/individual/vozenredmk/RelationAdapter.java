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
    public void onBindViewHolder(@NonNull final RelacijaViewHolder holder, int position) {
        final DBHelper dbHelper = new DBHelper(context);
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



        //STAR BUTTON
        holder.toggleButton.setTextOff(null);
        holder.toggleButton.setTextOn(null);
        holder.toggleButton.setText(null);
        holder.toggleButton.setChecked(false);
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Relation savedRealtion = new Relation();
                    savedRealtion = createNewRelation();
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_24));
                    dbHelper.addOne(savedRealtion);

                }


                else
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
            }

            private Relation createNewRelation() {
                Relation temp = new Relation();
                String relacija = (String) holder.relacija.getText();
                String[] parts = relacija.split(" - ");
                String start = parts[0];
                String end = parts[1];
                temp.setStart(start);
                temp.setEnd(end);
                String vremeikompanija = (String) holder.vremeikompanija.getText();
                parts = vremeikompanija.split(" - ");
                String vreme = parts[0];
                String kompanija = parts[1];
                temp.setVreme(vreme);
                temp.setKompanija(kompanija);
                temp.setStanica((String) holder.stanica.getText());
                temp.setCena((String) holder.cena.getText());

                return temp;
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
