package com.mycompany.karaokesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

public class ManajemenKaraoke implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<RoomKaraoke> daftarRoom;
    private HashMap<String, Pelanggan> daftarPelanggan;
    private FasilitasKaraoke fasilitas;

    private static final String FILE_DATA = "data_karaoke.dat";
    private static final String FILE_LOG = "log_karaoke.txt";

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

        catatAktivitas("Menambahkan room: " + room.getNamaRoom());
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

        throw new KaraokeException(
                "Room '" + nama + "' tidak ditemukan."
        );
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
            throw new KaraokeException(
                    "ID dan nama pelanggan harus diisi."
            );
        }

        if (daftarPelanggan.containsKey(id)) {
            throw new KaraokeException("ID pelanggan sudah terdaftar.");
        }

        daftarPelanggan.put(id, pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan.");

        catatAktivitas(
                "Menambahkan pelanggan: " + pelanggan.getNama()
                + " (ID: " + id + ")"
        );
    }

    public Pelanggan cariPelanggan(String id)
            throws KaraokeException {

        if (id == null || id.trim().isEmpty()) {
            throw new KaraokeException(
                    "ID pelanggan tidak boleh kosong."
            );
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

        System.out.println(
                "Total Pelanggan: " + daftarPelanggan.size()
        );
    }

    public void isiDataAwal() throws KaraokeException {
        if (!daftarRoom.isEmpty() || !daftarPelanggan.isEmpty()) {
            return;
        }

        tambahRoom(new RoomRegular(
                "Regular A", 3, 50000, "Sound System"));

        tambahRoom(new RoomRegular(
                "Regular B", 4, 60000, "Sound System"));

        tambahRoom(new RoomVIP(
                "VIP A", 6, 100000, "Sound System + TV"));

        tambahRoom(new RoomVIP(
                "VIP B", 8, 125000, "Sound System + TV"));

        tambahRoom(new RoomFamily(
                "Family A", 10, 150000, "TV + Sofa"));

        tambahPelanggan(new Pelanggan("P001", "Andi"));
        tambahPelanggan(new Pelanggan("P002", "Budi"));
        tambahPelanggan(new Pelanggan("P003", "Citra"));
    }

    public void catatAktivitas(String aktivitas) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_LOG, true);

            String waktu = new SimpleDateFormat(
                    "dd-MM-yyyy HH:mm:ss"
            ).format(new Date());

            writer.write(
                    "[" + waktu + "] " + aktivitas
                    + System.lineSeparator()
            );

        } catch (IOException e) {
            System.out.println(
                    "Gagal mencatat aktivitas: " + e.getMessage()
            );

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println(
                            "Gagal menutup file log: " + e.getMessage()
                    );
                }
            }
        }
    }

    public void bacaLogTransaksi() {
        File file = new File(FILE_LOG);

        if (!file.exists()) {
            System.out.println("File log belum tersedia.");
            return;
        }

        Scanner pembaca = null;

        try {
            pembaca = new Scanner(file);

            System.out.println("\n=== RIWAYAT AKTIVITAS KARAOKE ===");

            if (!pembaca.hasNextLine()) {
                System.out.println("Belum ada aktivitas.");
            }

            while (pembaca.hasNextLine()) {
                System.out.println(pembaca.nextLine());
            }

        } catch (IOException e) {
            System.out.println(
                    "Gagal membaca log: " + e.getMessage()
            );

        } finally {
            if (pembaca != null) {
                pembaca.close();
            }
        }
    }

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar =
                    new FileOutputStream(FILE_DATA);

            ObjectOutputStream objekKeluar =
                    new ObjectOutputStream(fileKeluar);

            objekKeluar.writeObject(this);

            objekKeluar.close();
            fileKeluar.close();

            System.out.println(
                    "Data Karaoke berhasil disimpan ke " + FILE_DATA
            );

        } catch (IOException e) {
            System.out.println(
                    "Gagal menyimpan data: " + e.getMessage()
            );
        }
    }

    public boolean muatDataDariFile() {
        File file = new File(FILE_DATA);

        if (!file.exists()) {
            return false;
        }

        try {
            FileInputStream fileMasuk =
                    new FileInputStream(file);

            ObjectInputStream objekMasuk =
                    new ObjectInputStream(fileMasuk);

            ManajemenKaraoke dataTersimpan =
                    (ManajemenKaraoke) objekMasuk.readObject();

            objekMasuk.close();
            fileMasuk.close();

            this.daftarRoom = dataTersimpan.daftarRoom;
            this.daftarPelanggan = dataTersimpan.daftarPelanggan;
            this.fasilitas = dataTersimpan.fasilitas;

            RoomKaraoke.setJumlahRoom(daftarRoom.size());

            System.out.println(
                    "Data Karaoke berhasil dimuat dari " + FILE_DATA
            );

            System.out.println(
                    "Jumlah room: " + daftarRoom.size()
            );

            System.out.println(
                    "Jumlah pelanggan: " + daftarPelanggan.size()
            );

            return true;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                    "Gagal memuat data: " + e.getMessage()
            );

            return false;
        }
    }
}
