package com.example.srilankantravellers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MyTours extends AppCompatActivity {

    EditText txtName, txtdays, txtBudget;
    Button button, btnSave,btnList,btnGoCalander;
    TextView placeView,dateView;
    DatabaseReference dbRef;
    Tour tour;
    Calendar calendar;
    DatePickerDialog dPd;
    //places
    CharSequence[] items = {"Colombo Museum - Rs-500,", " Gangaramaya Temple - Free,", " Independence Square - Free,", " Colombo Dutch Museum - Rs-450,", " Pettah Floating Market - Free,", " Colombo Lotus Tower - Rs1000,"};

    //if places are selected
    boolean[] selectedItems = {false, false, false, false, false, false};


    private void clearControls() {
        txtName.setText( "" );
        dateView.setText( "" );
        placeView.setText( "" );
        txtdays.setText( "" );
        txtBudget.setText( "" );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_tours );

        txtName = findViewById( R.id.txtName );
        dateView = findViewById( R.id.dateView);
        placeView = findViewById( R.id.placeView );
        txtdays = findViewById( R.id.txtdays );
        txtBudget = findViewById( R.id.txtBudget );

        //selecting places
        button = findViewById( R.id.placebtn );
        btnGoCalander = findViewById( R.id.btnGoCalander);
        placeView = findViewById( R.id.placeView );

        //insert Save btn and list
        btnSave = findViewById( R.id.btnSave);
        btnList = findViewById( R.id.btnList );
        tour = new Tour();

        //show selected items
        placeView.setText( itemsToString() );

        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder aleartDialogBuilder = new AlertDialog.Builder( MyTours.this );
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

        btnGoCalander.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int date = calendar.get( Calendar.DAY_OF_MONTH );
                int month = calendar.get( Calendar.MONTH );
                int year = calendar.get(Calendar.YEAR);

                dPd = new DatePickerDialog( MyTours.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        dateView.setText( mDay + "/" + (mMonth + 1) + "/" + mYear );
                    }
                }, date, month, year);
                dPd.show();
            }
        } );


 btnSave.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child( "Tour" );
        try {
            if (TextUtils.isEmpty( txtName.getText().toString() ))
                Toast.makeText( getApplicationContext(), "please enter a Tour name", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( dateView.getText().toString() ))
                Toast.makeText( getApplicationContext(), "please Select dates", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( placeView.getText().toString() ))
                Toast.makeText( getApplicationContext(), "please Select Places", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtdays.getText().toString() ))
                Toast.makeText( getApplicationContext(), "please Enter Days", Toast.LENGTH_SHORT ).show();
            else if (TextUtils.isEmpty( txtBudget.getText().toString() ))
                Toast.makeText( getApplicationContext(), "please Enter Budget", Toast.LENGTH_SHORT ).show();
            else {
                tour.settName( txtName.getText().toString().trim() );
                tour.setDate( dateView.getText().toString().trim() );
                tour.setPlaces( placeView.getText().toString().trim() );
                tour.setDays( txtdays.getText().toString().trim() );
                tour.setBudget( txtBudget.getText().toString().trim() );


                //dbRef.push().setValue(tour);
               // dbRef.child( "std1" ).setValue( tour );
                DatabaseReference newref = dbRef.push();
                String pushKey = newref.getKey();
                tour.setKey(pushKey);
                newref.setValue( tour );
                Toast.makeText( getApplicationContext(), "Inserted successfully", Toast.LENGTH_SHORT ).show();
                clearControls();
                Intent intent = new Intent( MyTours.this, Showtour.class );
                startActivity( intent );
            }
        } catch (NumberFormatException e) {
            Toast.makeText( getApplicationContext(), "invalid", Toast.LENGTH_SHORT ).show();
        }

    }
    });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyTours.this, Showtour.class);
                intent.putExtra( "user_name",  txtName.getText().toString());
                startActivity(intent);

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

