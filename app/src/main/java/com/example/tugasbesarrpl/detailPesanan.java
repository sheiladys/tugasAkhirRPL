package com.example.tugasbesarrpl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class detailPesanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        getSupportActionBar().hide();

        // Retrieve data from the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int berat = extras.getInt("Berat", 0);
            String kualitasCucian = extras.getString("KualitasCucian");
            String tanggalPengembalian = extras.getString("TanggalPengembalian");
            String alamat = extras.getString("key_alamat");
            int total = extras.getInt("Total", 0); // Retrieve the Total value

            // Update UI with the retrieved data
            TextView textViewBerat = findViewById(R.id.brtCucian);
            TextView textViewKualitas = findViewById(R.id.kwCucian);
            TextView textViewTanggal = findViewById(R.id.textViewTgl);
            TextView textViewAlamat = findViewById(R.id.textViewAlamat);
            TextView textViewTotal = findViewById(R.id.ttlCucian);

            textViewBerat.setText("Berat :" + berat + " KG");
            textViewKualitas.setText("Kualitas Cucian : " + kualitasCucian);
            textViewTanggal.setText("Tanggal : " + tanggalPengembalian);
            textViewAlamat.setText("Jl. " + alamat);
            textViewTotal.setText("Total : " + total);

            Button bayarButton = findViewById(R.id.bayar);
            bayarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(detailPesanan.this, "Pembayaran Diproses", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
