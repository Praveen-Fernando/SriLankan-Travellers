package com.example.srilankantravellers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TourView extends AppCompatActivity {


    EditText txtName, txtdays, txtBudget;
    TextView placeView,dateView;
    Button  button,btnUpdate,btnGoCalander;
    String key;
    Tour tour;
    Calendar calendar;
    DatePickerDialog dPd;
    DatabaseReference dbRef;

    //places
    CharSequence[] items = {"Colombo Museum - Rs-500,", " Gangaramaya Temple - Free,", " Independence Square - Free,", " Colombo Dutch Museum - Rs-450,", " Pettah Floating Market - Free,", " Colombo Lotus Tower - Rs1000,"};

    //if places are selected
    boolean[] selectedItems = {false, false, false, false, false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_view);

        txtName = findViewById(R.id.updatetxtName);
        dateView = findViewById(R.id.updatedateView );
        placeView = findViewById(R.id.updateplaceView);
        txtdays = findViewById(R.id.updatetxtdays );
        txtBudget = findViewById(R.id.updatetxtBudget);
        button = findViewById( R.id.placebtn );
        btnUpdate = findViewById(R.id.btnUpdate);
        btnGoCalander = findViewById( R.id.btnGoCalander);
        txtName.setEnabled( false );
        tour = new Tour();

        Intent intent = getIntent();
        key = intent.getStringExtra( "key" );

     //   txtName.setText( intent.getStringExtra( "txtName" ) );
     //   dateView.setText( intent.getStringExtra( "dateView" ) );
     //   placeView.setText( intent.getStringExtra( "placeView" ) );
      //  txtdays.setText( intent.getStringExtra( "txtdays" ) );
      //  txtBudget.setText( intent.getStringExtra( "txtBudget" ) );

        DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child( "Tour" );
        rref.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild( key )){
                      tour = dataSnapshot.child( key ).getValue(Tour.class);

                      txtName.setText( tour.gettName() );
                    dateView.setText( tour.getDate() );
                      placeView.setText( tour.getPlaces() );
                    txtdays.setText( tour.getDays() );
                    txtBudget.setText( tour.getBudget());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        btnGoCalander.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int date = calendar.get( Calendar.DAY_OF_MONTH );
                int month = calendar.get( Calendar.MONTH );
                int year = calendar.get(Calendar.YEAR);

                dPd = new DatePickerDialog( TourView.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        dateView.setText( mDay + "/" + (mMonth + 1) + "/" + mYear );
                    }
                }, date, month, year);
                dPd.show();
            }
        } );


        //show selected items
        placeView.setText( itemsToString() );

        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder aleartDialogBuilder = new AlertDialog.Builder(TourView.this);
                aleartDialogBuilder.setCancelable( true );
                aleartDialogBuilder.setTitle( "Select Places" );
                aleartDialogBuilder.setMultiChoiceItems( items, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selectedItems[which] = isChecked;
                    }
                } );
                aleartDialogBuilder.setPositiveButton( "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //show selected items
                        placeView.setText( itemsToString() );
                        dialogInterface.dismiss();
                    }
                } );
                AlertDialog alertDialog = aleartDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside( true );
                alertDialog.show();
            }
        } );


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child( "Tour" );
               //final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child( "Tour" );
               //final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Tour").child(key);
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild( key )) {
                            Tour tour = new Tour();
                            tour.settName( txtName.getText().toString().trim() );
                            tour.setDate( dateView.getText().toString().trim() );
                            tour.setPlaces( placeView.getText().toString().trim() );
                            tour.setDays( txtdays.getText().toString().trim() );
                            tour.setBudget( txtBudget.getText().toString().trim() );

                            dbRef.child( key ).setValue( tour );

                            Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(TourView.this,mainDashboard.class);
                            // intent.putExtra("name",n);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    private String itemsToString() {
        String text = "";
        for (int i = 0; i < selectedItems.length; i++) {
            if (selectedItems[i]) {
                text = text + items[i] + "";
            }
        }
        return text.trim();
    }

}

