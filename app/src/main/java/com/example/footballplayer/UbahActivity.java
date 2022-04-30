package com.example.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    private String id, nama, nomor, klub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnUbah = findViewById(R.id.btn_ubah);

//        ambil data yang di  kirimkan dari adapter
        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNama");
        nomor = intent.getStringExtra("vNomor");
        klub = intent.getStringExtra("vKlub");

//        tampilkan di setiap inputan
        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);


        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempNama = etNama.getText().toString();
                String tempNomor = etNomor.getText().toString();
                String tempKlub = etKlub.getText().toString();

                if(tempNama.trim().equals("")) {
                    etNama.setError("Nama harus diisi.!!");
                }else if(tempNomor.trim().equals("")) {
                    etNomor.setError("Nomor harus diisi.!!");
                }else if(tempKlub.trim().equals("")) {
                    etKlub.setError("Klub harus diisi.!!");
                }else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
                    long eksekusi = myDB.ubahPlayer(id, tempNama, tempNomor, tempKlub);

//                    jika gagal
                    if(eksekusi == -1) {
                        Toast.makeText(UbahActivity.this, "Gagal mengubah data player", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }else{
                        Toast.makeText(UbahActivity.this, "Berhasil mengubah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}