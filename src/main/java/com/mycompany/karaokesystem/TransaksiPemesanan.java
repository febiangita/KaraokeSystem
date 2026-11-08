
package com.mycompany.karaokesystem;

public class TransaksiPemesanan {

    private Pelanggan pelanggan;
    private RoomKaraoke room;
    private int durasiJam;

    public TransaksiPemesanan(
            Pelanggan pelanggan,
            RoomKaraoke room,
            int durasiJam) {

        this.pelanggan = pelanggan;
        this.room = room;
        this.durasiJam = durasiJam;
    }

    public void prosesTransaksi() {

        System.out.println("\n=== ASSOCIATION ===");
        System.out.println("TRANSAKSI PEMESANAN");

        System.out.println(
            "Nama Pelanggan: " + pelanggan.getNama()
        );

        System.out.println(
            "Nama Room: " + room.getNamaRoom()
        );

        System.out.println(
            "Durasi: " + durasiJam + " jam"
        );

        if (room instanceof DapatDipesan) {
            ((DapatDipesan) room).pesanRoom(durasiJam);
        } else {
            System.out.println("Room tidak dapat dipesan.");
            return;
        }

        System.out.println("Transaksi berhasil.");
    }
}
