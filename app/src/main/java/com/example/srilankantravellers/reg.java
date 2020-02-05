package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class reg extends AppCompatActivity  {
    Button loginlink,reg;
    EditText fname;
    EditText lname;
    EditText email;
    EditText password,cpassword;
    DatabaseReference dbRef;
    tourism std;


    private void clearControls(){
        fname.setText("");
        lname.setText("");
        email.setText("");
        password.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        loginlink = (Button) findViewById(R.id.hhh);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword) ;
        reg = (Button) findViewById(R.id.rbtnn);
        std=new tourism();

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(reg.this ,MainActivity.class);
                startActivity(intent);
            }
        });



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("tourism");
                String temail=email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                try{
                    if(TextUtils.isEmpty(fname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a user name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(lname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter the last name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(),"please enter a email",Toast.LENGTH_SHORT).show();
                    else if(!temail.matches(emailPattern))
                        Toast.makeText(getApplicationContext(),"enter a valid email",Toast.LENGTH_SHORT).show();
                    else if (!(password.getText().toString()).equals(cpassword.getText().toString()))
                    Toast.makeText(getApplicationContext(),"enter valid password",Toast.LENGTH_SHORT).show();

                    else{
                        std.setUname(fname.getText().toString().trim());
                        std.setLname(lname.getText().toString().trim());
                        std.setEmail(email.getText().toString().trim());
                        std.setPassword(password.getText().toString().trim());
                        std.setCpass(cpassword.getText().toString().trim());

                        //dbRef.push().setValue(std);
                        dbRef.child(std.getUname()).setValue(std);
                        Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(reg.this,mainDashboard.class);


                       ShareUname.uname = std.getUname();
                        intent.putExtra("name",std.getUname());
                      //  System.out.println(std.getUname()+"sdaasdgggggggggggg");
                        startActivity(intent);
                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_SHORT).show();
                }



            }
        });







}
    }

