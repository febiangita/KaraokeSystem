package com.mycompany.karaokesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManajemenKaraoke {

    private ArrayList<RoomKaraoke> daftarRoom;
    private HashMap<String, Pelanggan> daftarPelanggan;
    private FasilitasKaraoke fasilitas;

    public ManajemenKaraoke() {
        daftarRoom = new ArrayList<>();
        daftarPelanggan = new HashMap<>();

        fasilitas = new FasilitasKaraoke(
                "Fasilitas Karaoke",
                "Sound System, Mikrofon, TV, Sofa, dan Ruangan Nyaman"
        );
    }

    public void tampilkanFasilitas() {
        fasilitas.tampilkanFasilitas();
    }

    public void tambahRoom(RoomKaraoke room)
            throws KaraokeException {

        if (room == null) {
            throw new KaraokeException("Room tidak valid.");
        }

        for (RoomKaraoke r : daftarRoom) {
            if (r.getNamaRoom().equalsIgnoreCase(room.getNamaRoom())) {
                throw new KaraokeException("Nama room sudah digunakan.");
            }
        }

        daftarRoom.add(room);
        System.out.println("Room berhasil ditambahkan.");
    }

    public void lihatSemuaRoom() {
        if (daftarRoom.isEmpty()) {
            System.out.println("Belum ada data room.");
            return;
        }

        System.out.println("\n=== DAFTAR ROOM KARAOKE ===");

        System.out.printf(
                "%-15s %-15s %-10s %-13s %-20s%n",
                "Nama Room", "Tipe", "Kapasitas",
                "Harga/Jam", "Fasilitas"
        );

        for (RoomKaraoke room : daftarRoom) {
            room.tampilkanInfo();
        }

        System.out.println("Total Room: " + daftarRoom.size());
    }

    public int getJumlahRoom() {
        return daftarRoom.size();
    }

    public RoomKaraoke getRoom(int index)
            throws KaraokeException {

        if (index < 0 || index >= daftarRoom.size()) {
            throw new KaraokeException("Nomor room tidak tersedia.");
        }

        return daftarRoom.get(index);
    }

    public RoomKaraoke cariRoom(String nama)
            throws KaraokeException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new KaraokeException("Nama room tidak boleh kosong.");
        }

        for (RoomKaraoke room : daftarRoom) {
            if (room.getNamaRoom().equalsIgnoreCase(nama)) {
                return room;
            }
        }

        throw new KaraokeException("Room '" + nama + "' tidak ditemukan.");
    }

    public void tambahPelanggan(Pelanggan pelanggan)
            throws KaraokeException {

        if (pelanggan == null) {
            throw new KaraokeException("Data pelanggan tidak valid.");
        }

        String id = pelanggan.getIdPelanggan();

        if (id == null || id.trim().isEmpty()
                || pelanggan.getNama() == null
                || pelanggan.getNama().trim().isEmpty()) {
            throw new KaraokeException("ID dan nama pelanggan harus diisi.");
        }

        if (daftarPelanggan.containsKey(id)) {
            throw new KaraokeException("ID pelanggan sudah terdaftar.");
        }

        daftarPelanggan.put(id, pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    public Pelanggan cariPelanggan(String id)
            throws KaraokeException {

        if (id == null || id.trim().isEmpty()) {
            throw new KaraokeException("ID pelanggan tidak boleh kosong.");
        }

        Pelanggan pelanggan = daftarPelanggan.get(id);

        if (pelanggan == null) {
            throw new KaraokeException(
                    "Pelanggan dengan ID " + id + " tidak ditemukan."
            );
        }

        return pelanggan;
    }

    public void lihatSemuaPelanggan() {
        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        System.out.println("\n=== DAFTAR PELANGGAN ===");

        for (Map.Entry<String, Pelanggan> data
                : daftarPelanggan.entrySet()) {
            System.out.println(
                    "ID: " + data.getKey()
                    + " | Nama: " + data.getValue().getNama()
            );
        }

        System.out.println("Total Pelanggan: " + daftarPelanggan.size());
    }

    public void isiDataAwal() throws KaraokeException {
        tambahRoom(new RoomRegular("Regular A", 3, 50000, "Sound System"));
        tambahRoom(new RoomRegular("Regular B", 4, 60000, "Sound System"));
        tambahRoom(new RoomVIP("VIP A", 6, 100000, "Sound System + TV"));
        tambahRoom(new RoomVIP("VIP B", 8, 125000, "Sound System + TV"));
        tambahRoom(new RoomFamily("Family A", 10, 150000, "TV + Sofa"));

        tambahPelanggan(new Pelanggan("P001", "Andi"));
        tambahPelanggan(new Pelanggan("P002", "Budi"));
        tambahPelanggan(new Pelanggan("P003", "Citra"));
    }
}
