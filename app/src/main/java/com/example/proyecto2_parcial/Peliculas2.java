package com.example.proyecto2_parcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Peliculas2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String profile = extras.getString("profile");


            setTitle(profile);


            int moviesResourceId = getMoviesResourceId(profile);
            String[] movies = getResources().getStringArray(moviesResourceId);


            ListView listViewMovies = findViewById(R.id.listViewMovies);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, movies);
            listViewMovies.setAdapter(adapter);

            listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedMovie = (String) parent.getItemAtPosition(position);
                    reproducirVIdeo(selectedMovie);
                }
            });
        }
    }

    private int getMoviesResourceId(String profile) {
        int resourceId;
        switch (profile) {
            case "Infantil":
                resourceId = R.array.movies_infantil;
                break;
            case "Adolescente":
                resourceId = R.array.movies_adolescente;
                break;
            case "Adulto":
                resourceId = R.array.movies_adulto;
                break;
            default:
                resourceId = -1;
                break;
        }
        return resourceId;
    }

    private void reproducirVIdeo(String selectedMovie) {
        Intent intent = new Intent(this, Peliculas.class);
        intent.putExtra("selectedMovie", selectedMovie);
        startActivity(intent);
    }
}