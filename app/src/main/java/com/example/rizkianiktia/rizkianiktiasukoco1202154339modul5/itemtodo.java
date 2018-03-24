package com.example.rizkianiktia.rizkianiktiasukoco1202154339modul5;

/**
 * Created by ACER on 24/03/2018.
 */


public class itemtodo {
    String nama, deskripsi, prioritas;

    //method setter getter

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public itemtodo(String nama, String deskripsi, String prioritas) {

        this.nama = nama;
        this.deskripsi = deskripsi;
        this.prioritas = prioritas;
    }
}
