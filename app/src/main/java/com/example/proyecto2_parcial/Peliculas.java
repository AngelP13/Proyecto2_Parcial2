package com.example.proyecto2_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Peliculas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas2);

        VideoView videoView = findViewById(R.id.videoView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String selectedMovie = extras.getString("selectedMovie");
            int videoResource = getVideoResource(selectedMovie);
            if (videoResource != 0) {
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResource);

                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);

                videoView.setMediaController(mediaController);
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        }
    }

    private int getVideoResource(String selectedMovie) {
        switch (selectedMovie) {
            case "miraculous":
                return R.raw.miraculous;
            case "pinguinos":
                return R.raw.pinguinos;
            case "transformers":
                return R.raw.transformers;
            case "flash":
                return R.raw.flash;
            case "lalaland":
                return R.raw.lalaland;
            case "oppenheimer":
                return R.raw.oppenheimer;
            default:
                return 0;
        }
    }
}