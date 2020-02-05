package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Showtour_review extends AppCompatActivity {

    DatabaseReference dbRef;
    ProgressDialog progressDialog;
    List <Review> rev = new ArrayList<>();
    String plc;
    RecyclerView recyclerView;
    recycle adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtour_review);

        Intent i = getIntent();
        plc = i.getStringExtra("Kala");


        recyclerView = (RecyclerView) findViewById(R.id.recycleView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Showtour_review.this));
        progressDialog = new ProgressDialog(Showtour_review.this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Review").child(plc);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                rev.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Review review = dataSnapshot.getValue(Review.class);
                    //review.setKey(dataSnapshot.getKey());
                    rev.add(review);
                }

                adapter = new recycle(Showtour_review.this,rev);
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
