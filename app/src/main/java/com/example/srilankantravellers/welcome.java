package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

public class welcome extends AppCompatActivity {

  private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_welcome );
        //getSupportActionBar().hide();

        videoView =findViewById( R.id.videoView );
        Uri video = Uri.parse("android.resource://" +getPackageName() +"/" + R.raw.welcome);

        videoView.setVideoURI( video );

        videoView.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity( new Intent(welcome.this,MainActivity.class ) );
                finish();
            }
        } );
        videoView.start();
    }
}
