package com.example.tugasbesarrpl;

import static com.example.tugasbesarrpl.Db_Contract.urlRegister;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText nama_lengkap, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        btnDaftarListener();
        btnBackDaftarListener();
        txtMasukListener();
        nama_lengkap = findViewById(R.id.nama_lengkap);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private void btnDaftarListener() {
        Button btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RegisterActivity", "Button Daftar Clicked");
                String fullname = nama_lengkap.getText().toString();
                String userName = username.getText().toString();
                String emailUser = email.getText().toString();
                String pass = password.getText().toString();

                Log.d("RegisterActivity", "Fullname: " + fullname);
                Log.d("RegisterActivity", "Username: " + userName);
                Log.d("RegisterActivity", "Email: " + emailUser);
                Log.d("RegisterActivity", "Password: " + pass);

                if (!fullname.isEmpty() && !userName.isEmpty() && !emailUser.isEmpty() && !pass.isEmpty()) {
                    // Eksekusi request ke server di sini
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlRegister, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            Log.d("RegisterActivity", "Response: " + response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("RegisterActivity", "Error: " + error.getMessage(), error);

                            // Tambahan: Tampilkan pesan kesalahan yang lebih rinci di logcat
                            if (error.networkResponse != null && error.networkResponse.data != null) {
                                String errorResponse = new String(error.networkResponse.data);
                                Log.e("RegisterActivity", "Error Response: " + errorResponse);
                            }
                        }
                    }) {
                        @Override
                        protected HashMap<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<>();

                            params.put("nama_lengkap", fullname);
                            params.put("username", userName);
                            params.put("email", emailUser);
                            params.put("password", pass);

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                } else {
                    Log.d("RegisterActivity", "One or more fields are empty. Cannot proceed.");
                }
            }
        });
    }

    private void btnBackDaftarListener() {
        ImageView btnBack = findViewById(R.id.btn_back2);
        // Pastikan btnBack tidak null sebelum menetapkan OnClickListener
        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void txtMasukListener() {
        TextView txtMasuk = findViewById(R.id.txtMasuk);
        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}