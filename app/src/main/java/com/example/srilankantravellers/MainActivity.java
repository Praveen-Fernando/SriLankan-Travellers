package com.example.srilankantravellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private EditText nname;
    private EditText ppassword;
    private Button btn;
    private TextView info;
    Button reglink;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reglink = (Button) findViewById(R.id.lr);
        nname = (EditText) findViewById(R.id.name);
        ppassword = (EditText) findViewById(R.id.cpassword);
        btn = (Button) findViewById(R.id.btnn);
        info = (TextView) findViewById(R.id.tvInfo);
        dbref = FirebaseDatabase.getInstance().getReference("tourism");

    }

     public void login(View view){
        // Toast.makeText(getApplicationContext(), "Enter valid username", Toast.LENGTH_SHORT).show();
                final String name = nname.getText().toString();
                final String pw = ppassword.getText().toString();
                ShareUname.uname = name;
                dbref = FirebaseDatabase.getInstance().getReference().child("tourism");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(name)) {
                            if (pw.equals(dataSnapshot.child(name).child("password").getValue().toString())) {
                                Intent intent4 = new Intent(MainActivity.this, mainDashboard.class);
                                intent4.putExtra("name", name);
                                startActivity(intent4);
                               // Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Enter valid Password", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Enter valid username", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            public void gotoreg(View view){
                Intent intent = new Intent(MainActivity.this, reg.class);
                startActivity(intent);
               }


}






