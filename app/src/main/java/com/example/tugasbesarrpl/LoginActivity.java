package com.example.tugasbesarrpl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnMasukListener();
        btnBackMasukListener();
        txtDaftarListener();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

    }

    private void btnMasukListener() {
        Button btnMsk = findViewById(R.id.btnMasuk);
        btnMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LoginActivity", "Button Masuk Clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                Log.d("LoginActivity", "Username: " + username);
                Log.d("LoginActivity", "Password: " + password);

                if (!username.isEmpty() && !password.isEmpty()) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, Db_Contract.urlLogin + "?username=" + username + "&password=" + password , new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Selamat Datang Kembali")){
                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), halamanUtama.class);
                                startActivity(intent);

                                Log.d("LoginActivity", "Response: " + response);
                            } else {
                                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                                Log.d("LoginActivity", "Response: " + response);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("LoginActivity", "Error: " + error.getMessage(), error);

                            // Tambahan: Tampilkan pesan kesalahan yang lebih rinci di logcat
                            if (error.networkResponse != null && error.networkResponse.data != null) {
                                String errorResponse = new String(error.networkResponse.data);
                                Log.e("LoginActivity", "Error Response: " + errorResponse);
                            }
                        }
                    });
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void btnBackMasukListener() {
        ImageView btnBack = findViewById(R.id.btn_back1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void txtDaftarListener() {
        TextView txtDaftar = findViewById(R.id.txtDaftar);
        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}