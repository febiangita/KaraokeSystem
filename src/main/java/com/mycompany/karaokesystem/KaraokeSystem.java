package com.mycompany.karaokesystem;

import java.util.Scanner;

public class KaraokeSystem {

    public static void cariRoom(
            String namaRoom,
            RoomKaraoke[] daftarRoom,
            int jumlahData) {

        boolean ditemukan = false;

        System.out.println("\nMencari room dengan nama: " + namaRoom);

        for (int i = 0; i < jumlahData; i++) {
            if (daftarRoom[i].getNamaRoom()
                    .equalsIgnoreCase(namaRoom)) {

                System.out.println("Room ditemukan:");
                daftarRoom[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Room tidak ditemukan.");
        }
    }

    public static void cariRoom(
            String namaRoom,
            double hargaMaksimal,
            RoomKaraoke[] daftarRoom,
            int jumlahData) {

        boolean ditemukan = false;

        System.out.println("\nMencari room: " + namaRoom
                + " dengan harga maksimal Rp" + hargaMaksimal);

        for (int i = 0; i < jumlahData; i++) {
            if (daftarRoom[i].getNamaRoom()
                    .equalsIgnoreCase(namaRoom)
                    && daftarRoom[i].getHargaPerJam()
                    <= hargaMaksimal) {

                System.out.println("Room ditemukan:");
                daftarRoom[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Room tidak ditemukan.");
        }
    }

    public static void simulasiProses(RoomKaraoke room) {

        System.out.println("\n--------------------------------");
        System.out.println("     SIMULASI DYNAMIC BINDING");
        System.out.println("--------------------------------");

        room.tampilkanInfo();

        System.out.println("Proses berhasil.");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        RoomKaraoke[] daftarRoom = new RoomKaraoke[10];

        int jumlahData = 0;
        int pilihan;
        boolean isRunning = true;

        while (isRunning) {

            System.out.println("\n==================================================");
            System.out.println("                 KARAOKE SYSTEM");
            System.out.println("          Sistem Manajemen Room Karaoke");
            System.out.println("==================================================");
            System.out.println("1. Tambah Room");
            System.out.println("2. Tampilkan Semua Room");
            System.out.println("3. Cari Room");
            System.out.println("4. Simulasi Dynamic Binding");
            System.out.println("5. Pesan Room");
            System.out.println("6. Beri Rating");
            System.out.println("7. Keluar");
            System.out.println("==================================================");
            System.out.print("Pilih menu: ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {

                case 1:

                    if (jumlahData >= daftarRoom.length) {
                        System.out.println("Data room sudah penuh.");
                        break;
                    }

                    System.out.println("\n--- TAMBAH ROOM ---");
                    System.out.println("1. Room Regular");
                    System.out.println("2. Room VIP");
                    System.out.println("3. Room Family");
                    System.out.print("Pilih tipe room: ");

                    int tipeRoom = scanner.nextInt();
                    scanner.nextLine();

                    if (tipeRoom < 1 || tipeRoom > 3) {
                        System.out.println(
                                "Pilihan tipe room tidak tersedia.");
                        break;
                    }

                    System.out.print("Nama Room       : ");
                    String namaRoom = scanner.nextLine();

                    System.out.print("Kapasitas       : ");
                    int kapasitas = scanner.nextInt();

                    System.out.print("Harga Per Jam   : ");
                    double hargaPerJam = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Fasilitas       : ");
                    String fasilitas = scanner.nextLine();

                    if (tipeRoom == 1) {
                        daftarRoom[jumlahData] = new RoomRegular(
                                namaRoom, kapasitas,
                                hargaPerJam, fasilitas);

                        System.out.println(
                                "Room Regular berhasil ditambahkan.");

                    } else if (tipeRoom == 2) {
                        daftarRoom[jumlahData] = new RoomVIP(
                                namaRoom, kapasitas,
                                hargaPerJam, fasilitas);

                        System.out.println(
                                "Room VIP berhasil ditambahkan.");

                    } else {
                        daftarRoom[jumlahData] = new RoomFamily(
                                namaRoom, kapasitas,
                                hargaPerJam, fasilitas);

                        System.out.println(
                                "Room Family berhasil ditambahkan.");
                    }

                    jumlahData++;
                    break;

                case 2:

                    if (jumlahData == 0) {
                        System.out.println("\nBelum ada data room.");
                    } else {
                        System.out.println("\n--- DAFTAR ROOM KARAOKE ---");

                        System.out.printf(
                                "%-15s %-15s %-10s %-13s %-20s%n",
                                "Nama Room", "Tipe", "Kapasitas",
                                "Harga/Jam", "Fasilitas");

                        System.out.println(
                                "---------------------------------------------------------------");

                        for (int i = 0; i < jumlahData; i++) {
                            daftarRoom[i].tampilkanInfo();
                        }

                        System.out.println(
                                "Total Room Dibuat: "
                                + RoomKaraoke.getJumlahRoom());
                    }

                    break;

                case 3:

                    if (jumlahData == 0) {
                        System.out.println("\nBelum ada data room.");
                        break;
                    }

                    System.out.println("\n--- CARI ROOM ---");
                    System.out.println("1. Cari berdasarkan nama");
                    System.out.println(
                            "2. Cari berdasarkan nama dan harga");
                    System.out.print("Pilih pencarian: ");

                    int pilihanCari = scanner.nextInt();
                    scanner.nextLine();

                    if (pilihanCari == 1) {
                        System.out.print("Masukkan nama room: ");
                        String namaCari = scanner.nextLine();

                        cariRoom(namaCari, daftarRoom, jumlahData);

                    } else if (pilihanCari == 2) {
                        System.out.print("Masukkan nama room: ");
                        String namaCari = scanner.nextLine();

                        System.out.print("Masukkan harga maksimal: ");
                        double hargaMaksimal = scanner.nextDouble();
                        scanner.nextLine();

                        cariRoom(namaCari, hargaMaksimal,
                                daftarRoom, jumlahData);

                    } else {
                        System.out.println(
                                "Pilihan pencarian tidak tersedia.");
                    }

                    break;

                case 4:

                    if (jumlahData == 0) {
                        System.out.println("\nBelum ada data room.");
                    } else {
                        for (int i = 0; i < jumlahData; i++) {
                            simulasiProses(daftarRoom[i]);
                        }
                    }

                    break;

                case 5:

                    if (jumlahData == 0) {
                        System.out.println("\nBelum ada data room.");
                        break;
                    }

                    System.out.println("\n--- PESAN ROOM ---");

                    for (int i = 0; i < jumlahData; i++) {
                        System.out.print((i + 1) + ". ");
                        daftarRoom[i].tampilkanInfo();
                    }

                    System.out.print("Pilih nomor room: ");
                    int nomorRoom = scanner.nextInt();

                    if (nomorRoom < 1 || nomorRoom > jumlahData) {
                        System.out.println("Nomor room tidak valid.");
                        break;
                    }

                    System.out.print("Durasi pemakaian (jam): ");
                    int durasiJam = scanner.nextInt();

                    if (durasiJam <= 0) {
                        System.out.println(
                                "Durasi harus lebih dari 0 jam.");
                        break;
                    }

                    RoomKaraoke roomPesanan =
                            daftarRoom[nomorRoom - 1];

                    System.out.println("\n--- KONFIRMASI PESANAN ---");

                    if (roomPesanan instanceof DapatDipesan) {
                        ((DapatDipesan) roomPesanan)
                                .pesanRoom(durasiJam);
                    } else {
                        System.out.println(
                                "Room ini tidak mendukung pemesanan.");
                    }

                    break;

                case 6:

                    if (jumlahData == 0) {
                        System.out.println("\nBelum ada data room.");
                        break;
                    }

                    System.out.println("\n--- BERI RATING ---");

                    for (int i = 0; i < jumlahData; i++) {
                        System.out.print((i + 1) + ". ");
                        daftarRoom[i].tampilkanInfo();
                    }

                    System.out.print("Pilih nomor room: ");
                    int nomorRating = scanner.nextInt();

                    if (nomorRating < 1
                            || nomorRating > jumlahData) {
                        System.out.println("Nomor room tidak valid.");
                        break;
                    }

                    System.out.print("Masukkan rating (1-5): ");
                    int rating = scanner.nextInt();

                    RoomKaraoke roomRating =
                            daftarRoom[nomorRating - 1];

                    if (roomRating instanceof DapatDiberiRating) {
                        ((DapatDiberiRating) roomRating)
                                .beriRating(rating);
                    } else {
                        System.out.println(
                                "Room ini tidak mendukung rating.");
                    }

                    break;

                case 7:
                    isRunning = false;
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println(
                            "Pilihan menu tidak tersedia.");
            }
        }

        scanner.close();
    }
}