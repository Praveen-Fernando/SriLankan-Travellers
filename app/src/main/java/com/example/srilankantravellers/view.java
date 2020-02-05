package com.example.srilankantravellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view extends AppCompatActivity {

    EditText fnamee,lnamee,emaill;
    Button delete,update,passs;
    tourism std;
    DatabaseReference dbRef;
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent i = getIntent();
        n= i.getStringExtra("name");

        fnamee=(EditText) findViewById(R.id.uname);
        lnamee=(EditText) findViewById(R.id.laname);
        emaill=(EditText) findViewById(R.id.emaill);
        passs=(Button) findViewById(R.id.passs);
        delete=(Button) findViewById(R.id.del);
        update=(Button) findViewById(R.id.up);
        fnamee.setEnabled( false );
         std=new tourism();



        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("tourism").child(n);
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    fnamee.setText(dataSnapshot.child("uname").getValue().toString());
                    lnamee.setText(dataSnapshot.child("lname").getValue().toString());
                    emaill.setText(dataSnapshot.child("email").getValue().toString());
                   // passs.setText(dataSnapshot.child("password").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"no entries to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              DatabaseReference updRef=FirebaseDatabase.getInstance().getReference().child("tourism");
              updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if(dataSnapshot.hasChild(n)){
                          std = dataSnapshot.child(n).getValue(tourism.class);
                          try{
                              std.setUname(fnamee.getText().toString().trim());
                              std.setLname(lnamee.getText().toString().trim());
                              std.setEmail(emaill.getText().toString().trim());
                           //   std.setPassword(passs.getText().toString().trim());

                              dbRef=FirebaseDatabase.getInstance().getReference().child("tourism").child(n);
                              dbRef.setValue(std);

                              Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(view.this,mainDashboard.class);
                             // intent.putExtra("name",n);
                              startActivity(intent);

                          }
                          catch (NumberFormatException e){
                              Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_SHORT).show();
                          }
                      }
                      else
                          Toast.makeText(getApplicationContext(),"no source to update",Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });

            }
        });

        passs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.this,resetpw.class);
                intent.putExtra("name",n);
                startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("tourism");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(n)){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("tourism").child(n);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Account deleted successfully",Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(),"no source to delete",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent=new Intent(view.this,mainDashboard.class);
               // intent.putExtra("name",n);
                startActivity(intent);
            }
        });

    }
}
