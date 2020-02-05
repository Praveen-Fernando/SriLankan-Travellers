package com.example.srilankantravellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Display extends AppCompatActivity {


    EditText Name;
    EditText Email;
    EditText Feedback;
    DatabaseReference databaseReference;
    ListView listViewFeedback;
    List<FeedbackModel> feedbackModels;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);




        feedbackModels=new ArrayList<FeedbackModel>();
        databaseReference= FirebaseDatabase.getInstance().getReference("feedbackModels");



        Name=(EditText)findViewById(R.id.Name);
        Email=(EditText)findViewById(R.id.Email);
        Feedback=(EditText)findViewById(R.id.Feedback);
        listViewFeedback=(ListView)findViewById(R.id.listViewFeedback);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feedbackModels.clear();

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    FeedbackModel feedbackModel=postSnapshot.getValue(FeedbackModel.class);
                    feedbackModels.add(feedbackModel);
                }
                Feedbacklist listadapter=new Feedbacklist(Display.this,feedbackModels,databaseReference,Name,Email,Feedback);
                listViewFeedback.setAdapter((listadapter));


                listViewFeedback.setOnItemClickListener( new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                          Toast.makeText(getApplicationContext(),feedbackModels.get( i ).getId(),Toast.LENGTH_LONG ).show();
                        update(feedbackModels.get( i ).getId());

                    }
                } );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public  void  update(String id){
        Intent i = new Intent( this, com.example.srilankantravellers.Feedback.class );
        i.putExtra( "id",id );
        startActivity( i );

    }


}
