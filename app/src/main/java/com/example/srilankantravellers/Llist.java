package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Llist extends AppCompatActivity {

    Button ur_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        ur_view=(Button)findViewById(R.id.ur_view);
        ur_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToAddReview();
            }
        });




    }

    public void moveToAddReview(){
        Intent intent=new Intent(Llist.this,form.class);
        startActivity(intent);
    }
}
