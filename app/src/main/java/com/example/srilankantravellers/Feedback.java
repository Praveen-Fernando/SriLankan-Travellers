package com.example.srilankantravellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {

    Button send;
    Button list,Update;
    EditText Name;
    EditText Email;
    EditText Feedback;
    ListView listViewfeedback;
    DatabaseReference databaseReference;
    List<FeedbackModel> feedbackModels;


    String feedid;
    public static FeedbackModel feedbackModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackModels=new ArrayList<FeedbackModel>();
        databaseReference= FirebaseDatabase.getInstance().getReference("feedbackModels");


        send=(Button)findViewById(R.id.send);
        list=(Button)findViewById(R.id.list);
        Update=(Button)findViewById(R.id.Update);
        Name=(EditText)findViewById(R.id.Name);
        Email=(EditText)findViewById(R.id.Email);
        Feedback=(EditText)findViewById(R.id.Feedback);
        listViewfeedback=(ListView)findViewById(R.id.listViewfeedback);

       Intent laka = getIntent();
       feedid = laka.getStringExtra( "id" );


       try {
           if (!feedid.equals( null )) {


               DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child( "feedbackModels" );
               rref.addListenerForSingleValueEvent( new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.hasChild( feedid ));
                       FeedbackModel fb = dataSnapshot.child( feedid ).getValue(FeedbackModel.class);
                       Name.setText( fb.getName() );
                       Email.setText( fb.getEmail() );
                       Feedback.setText( fb.getFeedback() );

                       if (!ShareUname.uname.equals( fb.getName()))
                           Update.setEnabled( false );

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               } );
           }
       }catch (Exception e){

       }






        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name=Name.getText().toString();
                String email=Email.getText().toString();
                String feedback=Feedback.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(name) | TextUtils.isEmpty(email) | TextUtils.isEmpty(feedback) ) {
                    Toast.makeText(com.example.srilankantravellers.Feedback.this, "Fields Cannot Be Empty!", Toast.LENGTH_LONG).show();
                }

                else if (!email.matches (emailPattern)){
                    Toast.makeText(com.example.srilankantravellers.Feedback.this,"Please Enter a Valid Email ",Toast.LENGTH_SHORT).show();
                }

                else {
                    if (TextUtils.isEmpty(feedid)) {
                        String id = databaseReference.push().getKey();
                        FeedbackModel feedbackModel = new FeedbackModel(id, name, email, feedback);
                        databaseReference.child(id).setValue(feedbackModel);

                        Toast.makeText(Feedback.this, "Thank You For Your Feedback ☺", Toast.LENGTH_SHORT).show();
                    }

                }


                Name.setText(null);
                Email.setText(null);
                Feedback.setText(null);
            }
        });


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showlist();
            }
        });



        final FeedbackModel feedbackModel=new FeedbackModel();



            Update.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String name = Name.getText().toString();
                    String email = Email.getText().toString();
                    String feedback = Feedback.getText().toString();
                    Name.setEnabled( false );
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                    if (TextUtils.isEmpty( name ) | TextUtils.isEmpty( email ) | TextUtils.isEmpty( feedback )) {
                        Toast.makeText( com.example.srilankantravellers.Feedback.this, "Fields Cannot Be Empty!", Toast.LENGTH_LONG ).show();
                    } else if (!email.matches( emailPattern )) {
                        Toast.makeText( com.example.srilankantravellers.Feedback.this, "Please Enter a Valid Email ", Toast.LENGTH_SHORT ).show();
                    } else {
                        if (!TextUtils.isEmpty( feedid )) {

                            databaseReference.child( feedid ).child( "name" ).setValue( name );
                            databaseReference.child( feedid ).child( "email" ).setValue( email );
                            databaseReference.child( feedid ).child( "feedback" ).setValue( feedback );

                            Toast.makeText( Feedback.this, "Your Feedback Has Been Updated ☺", Toast.LENGTH_SHORT ).show();
                        }
                    }


                    Name.setText( null );
                    Email.setText( null );
                    Feedback.setText( null );


                }
            } );
        }



    public void showlist() {
        Intent intent=new Intent(this,Display.class);
        startActivity(intent);

    }




    }






