package com.individual.vozenredmk;

import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RelacijaViewHolder extends RecyclerView.ViewHolder {
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
        toggleButton = (ToggleButton) itemView.findViewById(R.id.relationFavorite);
    }
}
