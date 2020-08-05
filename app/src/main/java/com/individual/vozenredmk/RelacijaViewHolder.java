package com.individual.vozenredmk;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RelacijaViewHolder extends RecyclerView.ViewHolder {
    View mView;
    ConstraintLayout expandableLayout;
    TextView post_relacija, post_stanica, post_vremeikompanija, post_cena;

    public RelacijaViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
        expandableLayout.setVisibility(View.GONE);
    }

    public void setRelacija(String start) {
        post_relacija = (TextView)mView.findViewById(R.id.post_relacija);
        post_relacija.setText(start);
    }

    public void setStanica(String stanica) {
        post_stanica = (TextView)mView.findViewById(R.id.post_stanica);
        post_stanica.setText(stanica);
    }

    public void setVremeIKompanija(String vremeIKompanija) {
        post_vremeikompanija = (TextView)mView.findViewById(R.id.post_vremeikompanija);
        post_vremeikompanija.setText(vremeIKompanija);
        post_vremeikompanija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableLayout.getVisibility() == View.GONE)
                    expandableLayout.setVisibility(View.VISIBLE);
                else
                    expandableLayout.setVisibility(View.GONE);
            }
        });
    }
    public void setCena(String cena) {
        post_cena = (TextView)mView.findViewById(R.id.post_cena);
        post_cena.setText(cena);
    }
}