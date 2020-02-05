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

public class resetpw extends AppCompatActivity {

    EditText txtp1,txtp2,txtp3;
    Button b2;
    String n;
    DatabaseReference dbref;
    tourism u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpw);


        txtp1=findViewById(R.id.editText4);
        txtp2=findViewById(R.id.editText6);
        txtp3=findViewById(R.id.editText7);
        b2=findViewById(R.id.button4);

        Intent i = getIntent();
        n =i.getStringExtra("name");


    }

    public void check(View view){
        final String pw1 = txtp1.getText().toString().trim();
        final String pw2 = txtp2.getText().toString().trim();
        final String pw3 = txtp3.getText().toString().trim();

        DatabaseReference  upref = FirebaseDatabase.getInstance().getReference().child("tourism");
        upref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(txtp2.getText().toString()).equals(txtp3.getText().toString()))
                    Toast.makeText(getApplicationContext(),"enter valid password",Toast.LENGTH_SHORT).show();
                else if (dataSnapshot.hasChild(n)) {
                    u = dataSnapshot.child(n).getValue(tourism.class);

                    if (pw1.equals(dataSnapshot.child(n).child("password").getValue().toString())){
                        DatabaseReference  upref1 = FirebaseDatabase.getInstance().getReference().child("tourism");
                        upref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(n)){
                                    u.setPassword(pw2);
                                    u.setCpass(pw3);
                                    dbref = FirebaseDatabase.getInstance().getReference().child("tourism").child(n);
                                    dbref.setValue(u);
                                    Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(resetpw.this,MainActivity.class);
                                    // intent.putExtra("name",n);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }else
                        Toast.makeText(getApplicationContext(),"current password is wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
