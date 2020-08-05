package com.individual.vozenredmk;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    DatabaseReference mReference;
    String relationFrom, relationTo;
    FirebaseRecyclerAdapter<Relacija, RelacijaViewHolder> firebaseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReference = FirebaseDatabase.getInstance().getReference().child("relacii");
        relationFrom = getIntent().getStringExtra("relationFrom");
        relationTo = getIntent().getStringExtra("relationTo");

        recyclerViewList = (RecyclerView)findViewById(R.id.myRecycleView);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        initData();

    }

    private void initData() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Relacija, RelacijaViewHolder>
                (Relacija.class, R.layout.relacii_cardview,RelacijaViewHolder.class,mReference) {
            @Override
            protected void populateViewHolder(RelacijaViewHolder relacijaViewHolder, Relacija relacija, int i) {
                relacija.getStart();
                relacija.getEnd();
                if(relacija.getStart().equals(relationFrom) && relacija.getEnd().equals(relationTo)) {
                    relacijaViewHolder.setRelacija(relacija.getStart() + " - " + relacija.getEnd());
                    relacijaViewHolder.setStanica(relacija.getStanica());
                    relacijaViewHolder.setVremeIKompanija(relacija.getVreme() + " - " + relacija.getKompanija());
                    relacijaViewHolder.setCena(relacija.getCena());
                }
                else {
                    relacijaViewHolder.itemView.setVisibility(View.GONE);
                    relacijaViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }
        };

        recyclerViewList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        firebaseRecyclerAdapter.cleanup();

    }

}
