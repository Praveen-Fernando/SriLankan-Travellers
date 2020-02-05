package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


        public class Review_update extends AppCompatActivity {


            EditText n1_update, e1_update;
            Button btnUpdate;
            String key,name;
            Review review;
            DatabaseReference dbRef;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_review_update);

                n1_update = findViewById(R.id.n1_update);
                e1_update = findViewById(R.id.e1_update);
                btnUpdate = findViewById(R.id.update);
               n1_update.setEnabled( false );
                review = new Review();

                Intent intent = getIntent();
                key = intent.getStringExtra("key");
                name=ShareUname.uname;

                n1_update.setText(ShareUname.uname);
                System.out.println("ttttttttttttttttttttttttt11111"+name);
                System.out.println("ttttttttttttttttttttttttt11111"+key);
                //   txtName.setText( intent.getStringExtra( "txtName" ) );
                //   dateView.setText( intent.getStringExtra( "dateView" ) );
                //   placeView.setText( intent.getStringExtra( "placeView" ) );
                //  txtdays.setText( intent.getStringExtra( "txtdays" ) );
                //  txtBudget.setText( intent.getStringExtra( "txtBudget" ) );

                DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Review").child(key);
                rref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(name)) {
                            review = dataSnapshot.child(name).getValue(Review.class);
                            System.out.println("ttttttttttttttttttttttttt333"+review.getName());
                            //n1_update.setText(name);
                            e1_update.setText(review.getReview());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Review").child(key);
                        //final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child( "Tour" );
                        //final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Tour").child(key);
                        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(name)) {
                                    Review review = new Review();
                                    review.setName(n1_update.getText().toString().trim());
                                    review.setReview(e1_update.getText().toString().trim());


                                    dbRef.child(name).setValue(review);

                                    Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        }