package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class form extends AppCompatActivity {
    EditText txtName, txtReview;
    Button btnSave;
    DatabaseReference db;
    Review rv;
    String plc;

    private void clearControls(){
        txtName.setText("");
        txtReview.setText("");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        txtName=findViewById(R.id.n1);
        txtReview=findViewById(R.id.e1);

        btnSave=findViewById(R.id.save);
        txtName.setText( ShareUname.uname );
        txtName.setEnabled( false );
        Intent i = getIntent();
        plc = i.getStringExtra("Kala");
        System.out.println("kllllllllllllll"+plc);
        rv=new Review();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db= FirebaseDatabase.getInstance().getReference().child("Review").child(plc);
                try{
                    if (TextUtils.isEmpty(txtReview.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Add your review", Toast.LENGTH_SHORT).show();
                    else{
                        rv.setName(txtName.getText().toString().trim());
                        rv.setReview(txtReview.getText().toString().trim());
                        rv.setKey(plc);


                        db.child(rv.getName()).setValue(rv);
                       // db.child(rv.getName()).setValue(rv);
                        Toast.makeText(getApplicationContext(), "data successfull", Toast.LENGTH_SHORT).show();
                      //  clearControls();
                        Intent intent1 = new Intent( form.this, Showtour_review.class );
                        //intent1.putExtra("name", (Parcelable) rv);
                        intent1.putExtra("Kala",plc);
                      startActivity(intent1);
                    }

                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "invalid con number", Toast.LENGTH_SHORT).show();
                }




            }
        });








    }
}
