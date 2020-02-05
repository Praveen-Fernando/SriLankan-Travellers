package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    Button btn1,btn2,btn3;
    String plc;
    TextView t1,t2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        img=findViewById(R.id.imageView);

        t1= findViewById(R.id.h1);
        t2=findViewById(R.id.t1);
        btn1=(Button)findViewById(R.id.reviewB);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity2();

            }
        });

        btn2=(Button)findViewById(R.id.addReview);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToAdd();

            }
        });

        Intent i = getIntent();
        plc = i.getStringExtra("laka");
        t1.setText(plc);

        if(plc.equals("Sigiriya")){
            t2.setText(Discription.sigiriya);
            img.setImageDrawable(getResources().getDrawable(R.drawable.sigiriya));
        }else if(plc.equals("Galle Fort")){
            t2.setText(Discription.galle);
            img.setImageDrawable(getResources().getDrawable(R.drawable.galle));
        }else if(plc.equals("Gangaramaya Temple")){
            t2.setText(Discription.gangaramaya);
            img.setImageDrawable(getResources().getDrawable(R.drawable.ganga));
        }else if(plc.equals("Independent Square")){
            t2.setText(Discription.inde);
            img.setImageDrawable(getResources().getDrawable(R.drawable.inde));
        }else if(plc.equals("Nine Arch Bridge")){
            t2.setText(Discription.nine);
            img.setImageDrawable(getResources().getDrawable(R.drawable.nine));
        }

    }



    public void moveToActivity2(){
        Intent intent=new Intent(Activity2.this ,Showtour_review.class);
        intent.putExtra("Kala",plc);
        startActivity(intent);
    }
    public void moveToAdd(){
        Intent intent=new Intent(Activity2.this, form.class);
        intent.putExtra("Kala",plc);
        startActivity(intent);
    }
}
