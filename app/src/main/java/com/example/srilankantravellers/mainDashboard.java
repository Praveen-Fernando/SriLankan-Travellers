package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainDashboard extends AppCompatActivity {
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_dashboard );

        Intent i = getIntent();
        n = i.getStringExtra("name");
        Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10;


              button1 = (Button) findViewById(R.id.btnhome);
              button2 = (Button) findViewById(R.id.btnprofile);
              button3 = (Button) findViewById(R.id.tripsbtn);
              button4 = (Button) findViewById(R.id.hotelbtn);
              button5 = (Button) findViewById(R.id.feedbackbtn);
              button6 = (Button) findViewById(R.id.reviewbtn);
              button7 = (Button) findViewById(R.id.aboutbtn);
              button8 = (Button) findViewById(R.id.contactbtn);
              button9 = (Button) findViewById(R.id.btnmap);
              button10 = (Button) findViewById(R.id.logoutbtn);


            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenmainDashboard();

                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenView();

                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenMyTours();

                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenMain3Activity();

                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenFeedback();

                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenMainActivity2();

                }
            });
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenAboutUs();
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openContactUs();
                }
            });
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenMapsActivity();

                }
            });

             button10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenMainActivity();

              }
            });
        }

        public  void OpenmainDashboard(){
            Intent intent = new Intent(this,mainDashboard.class);
            startActivity(intent);
        }

        public  void OpenView(){
            Intent intent = new Intent(this,view.class);
            intent.putExtra("name",n);
            startActivity(intent);
        }

        public  void OpenMyTours(){
            Intent intent = new Intent(this,MyTours.class);
            startActivity(intent);
        }

        public  void OpenMain3Activity(){
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
        }

        public  void OpenFeedback(){
        Intent intent = new Intent(this,Feedback.class);
        startActivity(intent);
        }

        public  void OpenMainActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
        }
        public  void OpenAboutUs(){
            Intent intent = new Intent(this,AboutUs.class);
            startActivity(intent);
        }

       public  void openContactUs(){
           Intent intent = new Intent(this,ContactUs.class);
           startActivity(intent);
}

        public  void OpenMapsActivity(){
          Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
        public  void OpenMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    }
