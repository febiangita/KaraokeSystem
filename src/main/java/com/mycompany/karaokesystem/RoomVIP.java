package com.mycompany.karaokesystem;

public class RoomVIP extends RoomKaraoke {

    private String fasilitas;

    public RoomVIP(String namaRoom, int kapasitas, double hargaPerJam, String fasilitas) {
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
            this.fasilitas = "Sound System + TV";
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("%-15s %-15s %-10d Rp%-10.0f %-20s%n",
                getNamaRoom(), "VIP", getKapasitas(),
                getHargaPerJam(), fasilitas);
    }
}