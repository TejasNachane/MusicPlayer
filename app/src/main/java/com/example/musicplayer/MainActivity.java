package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

// mp means musicPlayer
public class MainActivity<mp, tactic, val> extends AppCompatActivity {
    static MediaPlayer mp;
    tactic MediaPlayer ;
    int position;
    SeekBar sb;

    Thread updateSeekBar;
    Button pause,next,previous;
    TextView songNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        //adding song
        mp = MediaPlayer.create(this,R.raw.music);
        mp.start();
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Now Playing");
        //adding buttons
        pause = (Button) findViewById(R.id.pause);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        //creating Seekbar
        sb = (SeekBar) findViewById(R.id.seekBar);
        updateSeekBar=new Thread(){
            @Override
            public void run(){
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
                while(currentPosition < totalDuration){
                    try{
                        sleep(500);
                        currentPosition=mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    }
                    catch (InterruptedException e){

                    }
                }
            }
        };
        //catching the song in progressbar
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }@Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }@Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });

        //changing the pause to play while song is playing
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setMax(mp.getDuration());
                if(mp.isPlaying()){
                    pause.setBackgroundResource(R.drawable.play);
                    mp.pause();

                }
                else {
                    pause.setBackgroundResource(R.drawable.pause);
                    mp.start();
                }
            }
        });

        //
    }
}

