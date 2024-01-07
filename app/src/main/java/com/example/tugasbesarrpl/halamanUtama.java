package com.example.tugasbesarrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class halamanUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);
        getSupportActionBar().hide();
        imgCuciListener();
    }

    private void imgCuciListener() {
        ImageView cuci = findViewById(R.id.washFitur);
        if (cuci != null) {
            cuci.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(halamanUtama.this, cuci.class);
                    startActivity(intent);
                }
            });
        }
    }
}