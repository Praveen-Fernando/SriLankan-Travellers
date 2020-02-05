package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HotelsGalle extends AppCompatActivity {


    ImageButton gallefort;
    ImageButton le;
    ImageButton tamarid;
    ImageButton jet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_galle);



        gallefort=(ImageButton) findViewById(R.id.gallefort);
        gallefort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Shangrila = "https://www.booking.com/hotel/lk/the-galle-fort.html";
                Uri web7 = Uri.parse(Shangrila);

                Intent goToGalle = new Intent(Intent.ACTION_VIEW,web7);
                if(goToGalle.resolveActivity(getPackageManager())!=null){
                    startActivity(goToGalle);
                }


            }
        });


        le=(ImageButton) findViewById(R.id.le);
        le.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Le = "https://www.booking.com/hotel/lk/le-grand-galle.html";
                Uri web8 = Uri.parse(Le);

                Intent goToLe = new Intent(Intent.ACTION_VIEW,web8);
                if(goToLe.resolveActivity(getPackageManager())!=null){
                    startActivity(goToLe);
                }


            }
        });

        tamarid=(ImageButton) findViewById(R.id.tamarid);
        tamarid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tamarid = "https://www.booking.com/hotel/lk/tamarind-hill.html";
                Uri web9 = Uri.parse(Tamarid);

                Intent goToTamarid = new Intent(Intent.ACTION_VIEW,web9);
                if(goToTamarid.resolveActivity(getPackageManager())!=null){
                    startActivity(goToTamarid);
                }


            }
        });

        jet=(ImageButton) findViewById(R.id.jet);
        jet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Jet = "https://www.booking.com/hotel/lk/jetwing-lighthouse.html";
                Uri web10 = Uri.parse(Jet);

                Intent goToJetwing = new Intent(Intent.ACTION_VIEW,web10);
                if(goToJetwing.resolveActivity(getPackageManager())!=null){
                    startActivity(goToJetwing);
                }


            }
        });
























    }
}
