package com.mycompany.karaokesystem;

public class RoomKaraoke {

    private String namaRoom;
    private int kapasitas;
    private double hargaPerJam;

    private static int jumlahRoom = 0;

    public RoomKaraoke(String namaRoom, int kapasitas, double hargaPerJam) {
        this.setNamaRoom(namaRoom);
        this.setKapasitas(kapasitas);
        this.setHargaPerJam(hargaPerJam);
        jumlahRoom++;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        if (namaRoom != null && !namaRoom.isEmpty()) {
            this.namaRoom = namaRoom;
        } else {
            this.namaRoom = "Tidak diketahui";
        }
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        if (kapasitas > 0) {
            this.kapasitas = kapasitas;
        } else {
            this.kapasitas = 1;
        }
    }

    public double getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(double hargaPerJam) {
        if (hargaPerJam > 0) {
            this.hargaPerJam = hargaPerJam;
        } else {
            this.hargaPerJam = 50000;
        }
    }

    public static int getJumlahRoom() {
        return jumlahRoom;
    }

    public void tampilkanInfo() {
        System.out.printf("%-15s %-15s %-10d Rp%-10.0f%n",
                namaRoom, "Umum", kapasitas, hargaPerJam);
    }
}
