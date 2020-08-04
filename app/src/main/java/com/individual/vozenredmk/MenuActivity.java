package com.individual.vozenredmk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReference = FirebaseDatabase.getInstance().getReference().child("relacii");
        mReference.keepSynced(true);

        recyclerViewList = (RecyclerView)findViewById(R.id.myRecycleView);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Relacija, RelacijaViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Relacija, RelacijaViewHolder>
                (Relacija.class, R.layout.relacii_cardview,RelacijaViewHolder.class,mReference) {
            @Override
            protected void populateViewHolder(RelacijaViewHolder relacijaViewHolder, Relacija relacija, int i) {
                relacijaViewHolder.setRelacija(relacija.getStart() + " - " + relacija.getEnd());
                relacijaViewHolder.setStanica(relacija.getStanica());
                relacijaViewHolder.setVremeIKompanija(relacija.getVreme() + " - " + relacija.getKompanija());
                relacijaViewHolder.setCena(relacija.getCena());
            }
        };

        recyclerViewList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class RelacijaViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public RelacijaViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }



        public void setRelacija(String start) {
            TextView post_relacija = (TextView)mView.findViewById(R.id.post_relacija);
            post_relacija.setText(start);
        }

        public void setStanica(String stanica) {
            TextView post_stanica = (TextView)mView.findViewById(R.id.post_stanica);
            post_stanica.setText(stanica);
        }

        public void setVremeIKompanija(String vremeIKompanija) {
            TextView post_vremeikompanija = (TextView)mView.findViewById(R.id.post_vremeikompanija);
            post_vremeikompanija.setText(vremeIKompanija);
        }

        public void setCena(String cena) {
            TextView post_cena = (TextView)mView.findViewById(R.id.post_cena);
            post_cena.setText(cena);
        }


    }
}
