package com.example.tugasbesarrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class cuci extends AppCompatActivity {
    private TextView beratTextView;
    private int currentBerat = 0;
    private EditText editTextTanggal;
    private RadioButton cbStandar, cbPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuci);
        getSupportActionBar().hide();
        editTextTanggal = findViewById(R.id.editTextTanggal);
        beratTextView = findViewById(R.id.berat);

        ImageView tambahButton = findViewById(R.id.tambah);
        ImageView kurangButton = findViewById(R.id.kurang);

        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahBerat();
            }
        });

        kurangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kurangBerat();
            }
        });

        cbStandar = findViewById(R.id.cbStandar);
        cbPremium = findViewById(R.id.cbPremium);
    }

    private void tambahBerat() {
        currentBerat++;
        updateBeratTextView();
    }

    private void kurangBerat() {
        if (currentBerat > 0) {
            currentBerat--;
            updateBeratTextView();
        }
    }

    private void updateBeratTextView() {
        beratTextView.setText(String.valueOf(currentBerat));
    }

    public void onPilihButtonClick(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Pilih kualitas cucian terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String kualitas = selectedRadioButton.getText().toString();
            Toast.makeText(this, "Kualitas cucian dipilih: " + kualitas, Toast.LENGTH_SHORT).show();
        }
    }

    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                editTextTanggal.setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    public void konfirmasiPesanan(View view) {
        // Retrieve order details and pass them to the next activity
        int berat = Integer.parseInt(beratTextView.getText().toString());
        String kualitasCucian = getSelectedQuality();
        String tanggalPengembalian = editTextTanggal.getText().toString();
        String alamat = getAlamat();
        int total = hitungTotal();

        if (tanggalPengembalian.isEmpty() || kualitasCucian.isEmpty()) {
            Toast.makeText(this, "Lengkapi tanggal dan kualitas cucian terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, detailPesanan.class);

        intent.putExtra("Berat", berat);
        intent.putExtra("KualitasCucian", kualitasCucian);
        intent.putExtra("TanggalPengembalian", tanggalPengembalian);
        intent.putExtra("key_alamat", alamat);
        intent.putExtra("Total", total);

        startActivity(intent);
    }

    private String getAlamat() {
        EditText editTextAlamat = findViewById(R.id.inputAlamat);
        return editTextAlamat.getText().toString();
    }

    private String getSelectedQuality() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.cbStandar) {
            return "Standar";
        } else if (selectedId == R.id.cbPremium) {
            return "Premium";
        }

        return "";
    }

    private int hitungTotal() {
        int hargaPerKg = 3000;
        int total;

        String kualitasCucian = getSelectedQuality();

        if ("Standar".equals(kualitasCucian)) {
            hargaPerKg = 5000;
        } else if ("Premium".equals(kualitasCucian)) {
            hargaPerKg = 7500;
        }

        // Menghitung total
        total = currentBerat * hargaPerKg;

        return total;
    }

}
