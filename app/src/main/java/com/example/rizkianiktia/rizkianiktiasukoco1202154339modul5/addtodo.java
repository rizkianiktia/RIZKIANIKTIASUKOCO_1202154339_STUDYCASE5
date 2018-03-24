package com.example.rizkianiktia.rizkianiktiasukoco1202154339modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addtodo extends AppCompatActivity {

    EditText nama, deskripsi, priority;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtodo);

        //deklarasi objek yang digunakan
        nama = findViewById(R.id.name);
        deskripsi = findViewById(R.id.desc);
        priority = findViewById(R.id.priority);
        db = new database(this);
    }

    //Method ketika tombol back ditekan
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
        this.finish();
    }

    //Method ketika tombol tambah ditekan
    public void tambah(View view) {
        if (db.insertdata(new itemtodo(nama.getText().toString(), deskripsi.getText().toString(), priority.getText().toString()))) {
            Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, home.class));
            this.finish();
        } else {
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
            nama.setText(null);
            deskripsi.setText(null);
            priority.setText(null);
        }
    }
}
