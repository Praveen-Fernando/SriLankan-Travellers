package com.example.srilankantravellers;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Showtour extends AppCompatActivity {

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<Tour> list = new ArrayList<>();
    //List<String> Keys = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtour);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Showtour.this));
        progressDialog = new ProgressDialog(Showtour.this);
        progressDialog.setMessage("Loading Data from Database");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tour");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tour tour = dataSnapshot.getValue(Tour.class);
                    tour.setKey(dataSnapshot.getKey());
                    if(tour.gettName().equals( ShareUname.uname )) {
                    list.add(tour);
                    }
                }

                adapter = new RecyclerViewAdapter(Showtour.this, list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });


    }
}