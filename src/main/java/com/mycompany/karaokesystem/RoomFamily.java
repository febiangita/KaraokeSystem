package com.mycompany.karaokesystem;

public class RoomFamily extends RoomKaraoke {

    private String fasilitas;

    public RoomFamily(String namaRoom, int kapasitas, double hargaPerJam, String fasilitas) {
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
            this.fasilitas = "TV + Sofa";
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf(
                "%-15s %-10s %-10d Rp%-10.0f %-20s%n",
                getNamaRoom(),
                "Family",
                getKapasitas(),
                getHargaPerJam(),
                fasilitas
        );
    }
}
