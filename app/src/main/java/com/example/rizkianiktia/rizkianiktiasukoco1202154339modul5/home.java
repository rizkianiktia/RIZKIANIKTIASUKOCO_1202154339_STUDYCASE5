package com.example.rizkianiktia.rizkianiktiasukoco1202154339modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    database db; RecyclerView rc;
   adapter adapter;ArrayList<itemtodo> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Deklarasi objek yang akan digunakan
        rc = findViewById(R.id.todolist);
        listitem = new ArrayList<>();

        //Mengambil kembali data dari database
        db = new database(this);
        db.getAllItems(listitem);

        //Mengambil kembali SharedPreference
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.white);

        //Menentukan adapter untuk recyclerview_todolist
        adapter = new adapter(this, listitem, warna);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);

        //Menjalankan method
        initswipe();
    }

    //Method untuk menambahkan ItemTouchHelper pada RecyclerView
    public void initswipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //ketika dilakukan swiped data akan dihapus
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                itemtodo cur = adapter.getItem(pos);

                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if(db.deletedata(cur.getNama())){
                        adapter.remove(pos);
                        Snackbar.make(findViewById(R.id.root), "Item deleted", 1500).show();


                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rc);
    }

    //membuat options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //memilih item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            startActivity(new Intent(home.this, setting.class));
            finish();
        }
        return true;
    }

    //ketika menekan tombol add
    public void add(View view) {
        Intent intent = new Intent(this, addtodo.class );
        startActivity(intent);
        finish();
    }
}
