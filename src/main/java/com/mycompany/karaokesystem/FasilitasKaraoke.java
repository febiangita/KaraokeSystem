package com.mycompany.karaokesystem;

import java.io.Serializable;

public class FasilitasKaraoke implements Serializable {

    private static final long serialVersionUID = 1L;

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
