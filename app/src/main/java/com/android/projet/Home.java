package com.android.projet;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {
    ImageView Hiking ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Hiking = findViewById(R.id.hiking);

        // Ajouter un écouteur de clic sur le bouton
        Hiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un intent pour passer à SecondActivity
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);  // Lancer la nouvelle activité
            }
        });

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        String userName = getIntent().getStringExtra("userName");
        if (userName != null && !userName.isEmpty()) {
            welcomeMessage.setText("Hello, " + userName + "!");
        } else {
            welcomeMessage.setText("Hello!");
        }

    }
}