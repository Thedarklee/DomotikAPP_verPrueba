package com.example.domotikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Items extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }
    public void onClickAccederConectar (View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(Items.this, R.raw.sonido );
        //Inicio la reproducción
        mediaPlayer.start();
        Intent intent = new Intent(this, Connect.class);
        startActivity(intent);

    }
    public void onClickAccederUbicacionLando (View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(Items.this, R.raw.okok );
        //Inicio la reproducción
        mediaPlayer.start();
        Intent intent = new Intent(this, Maplando.class);
        startActivity(intent);

    }
}
