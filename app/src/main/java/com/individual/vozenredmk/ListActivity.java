package com.individual.vozenredmk;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ListActivity extends AppCompatActivity {
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
        initData();
    }

    private void initData() {
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

    @Override
    protected void onStart() {
        super.onStart();

    }

    public static class RelacijaViewHolder extends RecyclerView.ViewHolder {
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
}