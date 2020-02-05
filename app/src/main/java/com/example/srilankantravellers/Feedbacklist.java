package com.example.srilankantravellers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

public class Feedbacklist extends ArrayAdapter<FeedbackModel> {


    private Activity context;
    private List<FeedbackModel> feedbackModels;
    DatabaseReference databaseReference;
    EditText Name;
    EditText Email;
    EditText Feedback;
//    String loginusername = "Lakshan";


    public Feedbacklist(Activity context, List<FeedbackModel> feedbackModels, DatabaseReference databaseReference, EditText Feedback, EditText Name, EditText Email) {
        super(context, R.layout.activity_feedbacklist, feedbackModels);

        this.context = context;
        this.feedbackModels = feedbackModels;
        this.databaseReference = databaseReference;
        this.Name = Name;
        this.Email = Email;
        this.Feedback = Feedback;

    }

    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        final View listViewItem = layoutInflater.inflate(R.layout.activity_feedbacklist, null, true);
        TextView txtname = (TextView) listViewItem.findViewById(R.id.txtname);
        TextView txtemail = (TextView) listViewItem.findViewById(R.id.txtemail);
        TextView txtfeed = (TextView) listViewItem.findViewById(R.id.txtfeed);
        Button btndelete = (Button) listViewItem.findViewById(R.id.btndelete);



        final FeedbackModel feedbackModel = feedbackModels.get(pos);

         if(!ShareUname.uname.equals( feedbackModel.getName() ))
            btndelete.setVisibility( View.INVISIBLE );

         txtname.setText(feedbackModel.getName());
        txtemail.setText(feedbackModel.getEmail());
        txtfeed.setText(feedbackModel.getFeedback());
    txtname.setEnabled( false );


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(feedbackModel.getId()).removeValue();
            }
        });


        return listViewItem;
    }

}