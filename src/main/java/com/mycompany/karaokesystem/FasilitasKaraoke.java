package com.mycompany.karaokesystem;

public class FasilitasKaraoke {

    private String namaFasilitas;
    private String deskripsi;

    public FasilitasKaraoke(String namaFasilitas, String deskripsi) {
        this.namaFasilitas = namaFasilitas;
        this.deskripsi = deskripsi;
    }

    public void tampilkanFasilitas() {
        System.out.println("Nama Fasilitas : " + namaFasilitas);
        System.out.println("Deskripsi      : " + deskripsi);
    }
}