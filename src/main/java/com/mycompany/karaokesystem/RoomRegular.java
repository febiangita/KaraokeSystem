package com.mycompany.karaokesystem;

public class RoomRegular extends RoomKaraoke {

    private String fasilitas;

    public RoomRegular(String namaRoom, int kapasitas, double hargaPerJam, String fasilitas) {
        super(namaRoom, kapasitas, hargaPerJam);
        this.setFasilitas(fasilitas);
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        if (fasilitas != null && !fasilitas.isEmpty()) {
            this.fasilitas = fasilitas;
        } else {
            this.fasilitas = "Sound System";
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("%-15s %-15s %-10d Rp%-10.0f %-20s%n",
                getNamaRoom(), "Regular", getKapasitas(),
                getHargaPerJam(), fasilitas);
    }
}