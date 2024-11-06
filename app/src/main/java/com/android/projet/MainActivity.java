package com.android.projet;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

     RecyclerView recyclerView;
     HikingAdapter hikingAdapter;
     List<HikingTrail> hikingTrailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialiser RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // Créer les données
        hikingTrailList = new ArrayList<>();
        hikingTrailList.add(new HikingTrail("Lac Noir - Akfadou", "Béjaïa", R.drawable.lac_noir));
        hikingTrailList.add(new HikingTrail("Lac Agoulmime", "Bouira", R.drawable.lacagoulmime));
        hikingTrailList.add(new HikingTrail("Theniet El Had", "Tissemsilet", R.drawable.thenietlhad));
        hikingTrailList.add(new HikingTrail("Chrea", "Blida", R.drawable.chrea));

        // Configurer l'adapter
        hikingAdapter = new HikingAdapter(hikingTrailList,this);
        recyclerView.setAdapter(hikingAdapter);


        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int space = 25;
                outRect.left = space;
                outRect.right = space;

            }
        });

    }
}

