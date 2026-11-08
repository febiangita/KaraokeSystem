package com.mycompany.karaokesystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KaraokeSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ManajemenKaraoke manajemen =
            new ManajemenKaraoke();

    public static void validasiNama(String nama)
            throws KaraokeException {
        if (nama == null || nama.trim().isEmpty()) {
            throw new KaraokeException("Nama tidak boleh kosong.");
        }
    }

    public static RoomKaraoke cariRoom(String nama)
            throws KaraokeException {
        validasiNama(nama);
        return manajemen.cariRoom(nama);
    }

    public static Pelanggan cariPelanggan(String id)
            throws KaraokeException {
        validasiNama(id);
        return manajemen.cariPelanggan(id);
    }

    private static void isiDataAwal() throws KaraokeException {
        manajemen.isiDataAwal();
    }

    private static void tampilkanSemuaRoom() {
        manajemen.lihatSemuaRoom();
    }

    private static void tambahRoom() throws KaraokeException {
        System.out.println("\n1. Room Regular");
        System.out.println("2. Room VIP");
        System.out.println("3. Room Family");
        System.out.print("Pilih tipe room: ");

        int tipe = scanner.nextInt();
        scanner.nextLine();

        if (tipe < 1 || tipe > 3) {
            throw new KaraokeException("Tipe room tidak tersedia.");
        }

        System.out.print("Nama room: ");
        String nama = scanner.nextLine().trim();
        validasiNama(nama);

        System.out.print("Kapasitas: ");
        int kapasitas = scanner.nextInt();

        System.out.print("Harga per jam: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Fasilitas: ");
        String fasilitas = scanner.nextLine().trim();
        validasiNama(fasilitas);

        if (kapasitas <= 0 || harga <= 0) {
            throw new KaraokeException(
                    "Kapasitas dan harga harus lebih dari 0."
            );
        }

        RoomKaraoke roomBaru;

        switch (tipe) {
            case 1:
                roomBaru = new RoomRegular(nama, kapasitas, harga, fasilitas);
                break;
            case 2:
                roomBaru = new RoomVIP(nama, kapasitas, harga, fasilitas);
                break;
            default:
                roomBaru = new RoomFamily(nama, kapasitas, harga, fasilitas);
                break;
        }

        manajemen.tambahRoom(roomBaru);
    }

    private static void cariRoomMenu() throws KaraokeException {
        System.out.print("Masukkan nama room: ");
        String nama = scanner.nextLine();

        RoomKaraoke room = cariRoom(nama);
        System.out.println("Room ditemukan:");
        room.tampilkanInfo();
    }

    private static void simulasiDynamicBinding()
            throws KaraokeException {

        if (manajemen.getJumlahRoom() == 0) {
            throw new KaraokeException("Belum ada data room.");
        }

        for (int i = 0; i < manajemen.getJumlahRoom(); i++) {
            RoomKaraoke room = manajemen.getRoom(i);
            room.tampilkanInfo();
            System.out.println("Biaya 1 jam: Rp" + room.hitungBiaya(1));
        }
    }

    private static void pesanRoom() throws KaraokeException {
        System.out.print("Masukkan nama room: ");
        String nama = scanner.nextLine();

        RoomKaraoke room = cariRoom(nama);

        System.out.print("Durasi pemakaian (jam): ");
        int durasi = scanner.nextInt();
        scanner.nextLine();

        if (durasi <= 0) {
            throw new KaraokeException(
                    "Durasi harus lebih dari 0 jam."
            );
        }

        if (room instanceof DapatDipesan) {
            ((DapatDipesan) room).pesanRoom(durasi);
        } else {
            throw new KaraokeException(
                    "Room tidak mendukung pemesanan."
            );
        }
    }

    private static void transaksiPemesanan()
            throws KaraokeException {

        System.out.println("\n=== ASSOCIATION ===");
        manajemen.lihatSemuaPelanggan();

        System.out.print("Masukkan ID pelanggan: ");
        String id = scanner.nextLine().trim();

        Pelanggan pelanggan = cariPelanggan(id);

        manajemen.lihatSemuaRoom();

        System.out.print("Masukkan nama room: ");
        String namaRoom = scanner.nextLine();

        RoomKaraoke room = cariRoom(namaRoom);

        System.out.print("Durasi pemakaian (jam): ");
        int durasi = scanner.nextInt();
        scanner.nextLine();

        if (durasi <= 0) {
            throw new KaraokeException(
                    "Durasi harus lebih dari 0 jam."
            );
        }

        TransaksiPemesanan transaksi =
                new TransaksiPemesanan(pelanggan, room, durasi);

        transaksi.prosesTransaksi();
    }

    private static void beriRating() throws KaraokeException {
        System.out.print("Masukkan nama room: ");
        String nama = scanner.nextLine();

        RoomKaraoke room = cariRoom(nama);

        System.out.print("Masukkan rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        if (rating < 1 || rating > 5) {
            throw new KaraokeException(
                    "Rating harus antara 1 sampai 5."
            );
        }

        if (room instanceof DapatDiberiRating) {
            ((DapatDiberiRating) room).beriRating(rating);
        } else {
            throw new KaraokeException(
                    "Room tidak mendukung rating."
            );
        }
    }

    private static void tambahPelanggan()
            throws KaraokeException {

        System.out.print("ID pelanggan: ");
        String id = scanner.nextLine().trim();
        validasiNama(id);

        System.out.print("Nama pelanggan: ");
        String nama = scanner.nextLine().trim();
        validasiNama(nama);

        manajemen.tambahPelanggan(new Pelanggan(id, nama));
    }

    private static void cariPelangganMenu()
            throws KaraokeException {

        System.out.print("Masukkan ID pelanggan: ");
        String id = scanner.nextLine().trim();

        Pelanggan pelanggan = cariPelanggan(id);
        System.out.println("Nama pelanggan: " + pelanggan.getNama());
    }

    private static void tampilkanPelanggan() {
        manajemen.lihatSemuaPelanggan();
    }

    private static void tampilkanFasilitas() {
        manajemen.tampilkanFasilitas();
    }

    public static void main(String[] args) {
        try {
            isiDataAwal();
        } catch (KaraokeException e) {
            System.out.println("Gagal mengisi data awal: " + e.getMessage());
            return;
        }

        boolean berjalan = true;

        while (berjalan) {
            try {
                System.out.println("\n================================");
                System.out.println("         KARAOKE SYSTEM");
                System.out.println("================================");
                System.out.println("1. Tambah Room");
                System.out.println("2. Tampilkan Semua Room");
                System.out.println("3. Cari Room");
                System.out.println("4. Simulasi Dynamic Binding");
                System.out.println("5. Pesan Room");
                System.out.println("6. Beri Rating");
                System.out.println("7. Tambah Pelanggan");
                System.out.println("8. Cari Pelanggan");
                System.out.println("9. Tampilkan Pelanggan");
                System.out.println("10. Transaksi Pemesanan");
                System.out.println("11. Tampilkan Fasilitas");
                System.out.println("12. Keluar");
                System.out.println("================================");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahRoom();
                        break;
                    case 2:
                        tampilkanSemuaRoom();
                        break;
                    case 3:
                        cariRoomMenu();
                        break;
                    case 4:
                        simulasiDynamicBinding();
                        break;
                    case 5:
                        pesanRoom();
                        break;
                    case 6:
                        beriRating();
                        break;
                    case 7:
                        tambahPelanggan();
                        break;
                    case 8:
                        cariPelangganMenu();
                        break;
                    case 9:
                        tampilkanPelanggan();
                        break;
                    case 10:
                        transaksiPemesanan();
                        break;
                    case 11:
                        tampilkanFasilitas();
                        break;
                    case 12:
                        berjalan = false;
                        System.out.println("Program selesai.");
                        break;
                    default:
                        throw new KaraokeException(
                                "Pilihan menu tidak tersedia."
                        );
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Masukkan angka yang valid.");
                scanner.nextLine();

            } catch (KaraokeException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Proses menu selesai.");
            }
        }

        scanner.close();
    }
}
