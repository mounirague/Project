package com.android.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Started extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);

        Button buttonNavigate = findViewById(R.id.btn_started);

        // Ajouter un écouteur de clic sur le bouton
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un intent pour passer à SecondActivity
                Intent intent = new Intent(Started.this, Register.class);
                startActivity(intent);  // Lancer la nouvelle activité
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}