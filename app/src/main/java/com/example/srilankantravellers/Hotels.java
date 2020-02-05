package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Hotels extends AppCompatActivity {
    ImageButton hilton;
    ImageButton shangrila;
    ImageButton galadari;
    ImageButton taj;
    ImageButton cinnoman;
    ImageButton ramada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        hilton = (ImageButton) findViewById(R.id.hilton);
        hilton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Hilton = "https://www.booking.com/hotel/lk/hilton-colombo.html";
                Uri web1 = Uri.parse(Hilton);

                Intent goToHilton = new Intent(Intent.ACTION_VIEW,web1);
                if(goToHilton.resolveActivity(getPackageManager())!=null){
                    startActivity(goToHilton);
                }


            }
        });
        shangrila=(ImageButton) findViewById(R.id.shangrila);
        shangrila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Shangrila = "http://www.shangri-la.com/colombo/shangrila/";
                Uri web2 = Uri.parse(Shangrila);

                Intent goToShangrila = new Intent(Intent.ACTION_VIEW,web2);
                if(goToShangrila.resolveActivity(getPackageManager())!=null){
                    startActivity(goToShangrila);
                }


            }
        });
        galadari = (ImageButton) findViewById(R.id.galadari);
        galadari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Galadari ="http://www.galadarihotel.lk/";
                Uri web3 = Uri.parse(Galadari);

                Intent goToGaladari = new Intent(Intent.ACTION_VIEW,web3);
                if(goToGaladari.resolveActivity(getPackageManager())!=null){
                    startActivity(goToGaladari);

                }
            }
        });
        taj = (ImageButton)findViewById(R.id.taj);
        taj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Taj = "https://www.booking.com/hotel/lk/taj-samudra.html";
                Uri web4 = Uri.parse(Taj);

                Intent goToTaj = new Intent(Intent.ACTION_VIEW,web4);
                if(goToTaj.resolveActivity(getPackageManager())!=null){
                    startActivity(goToTaj);
                }
            }
        });

        cinnoman = (ImageButton)findViewById(R.id.cinnoman);
        cinnoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Cinnoman="https://www.booking.com/hotel/lk/cinnamon-grand-colombo.html";
                Uri web5 = Uri.parse(Cinnoman);

                Intent goToCinnoman = new Intent(Intent.ACTION_VIEW,web5);
                if(goToCinnoman.resolveActivity(getPackageManager())!=null){
                    startActivity(goToCinnoman);
                }

            }
        });

        ramada = (ImageButton)findViewById(R.id.ramada);
        ramada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ramada="https://www.booking.com/hotel/lk/ramada-colombo.html";
                Uri web6 = Uri.parse(Ramada);

                Intent goToRamada = new Intent(Intent.ACTION_VIEW,web6);
                if(goToRamada.resolveActivity(getPackageManager())!=null){
                    startActivity(goToRamada);
                }

            }
        });



    }
}
