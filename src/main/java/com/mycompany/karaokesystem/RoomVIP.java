package com.mycompany.karaokesystem;

public class RoomVIP extends RoomKaraoke
        implements DapatDipesan, DapatDiberiRating {

    private String fasilitas;

    public RoomVIP(String namaRoom, int kapasitas,
            double hargaPerJam, String fasilitas) {
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
    public double hitungBiaya(int durasiJam) {
        return getHargaPerJam() * durasiJam;
    }

    @Override
    public void pesanRoom(int durasiJam) {
        System.out.println("Room VIP dipesan selama "
                + durasiJam + " jam.");
        System.out.println("Total biaya: Rp"
                + hitungBiaya(durasiJam));
    }

    @Override
    public void beriRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            System.out.println("Rating Room VIP: " + rating + "/5");
        } else {
            System.out.println("Rating harus antara 1 sampai 5!");
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("%-15s %-15s %-10d Rp%-10.0f %-20s%n",
                getNamaRoom(), "VIP", getKapasitas(),
                getHargaPerJam(), fasilitas);
    }
}