import java.util.Scanner;

public class Main {
    private static final int BIAYA_PELAYANAN = 20000;
    private static final double PAJAK_PERSEN = 0.10;
    private static final double DISKON_PERSEN = 0.10;
    private static final int BATAS_DISKON = 100000;
    private static final int BATAS_GRATIS_MINUMAN = 50000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Data menu disimpan dalam Array berisi object Menu.
        // Minimal 4 menu makanan dan 4 menu minuman (sesuai ketentuan soal).
        Menu[] menu = new Menu[8];
        menu[0] = new Menu("Nasi Padang", 28000, "MAKANAN");
        menu[1] = new Menu("Mie Ayam", 18000, "MAKANAN");
        menu[2] = new Menu("Ayam Geprek", 22000, "MAKANAN");
        menu[3] = new Menu("Sate Ayam", 30000, "MAKANAN");
        menu[4] = new Menu("Es Teh", 8000, "MINUMAN");
        menu[5] = new Menu("Jus Jeruk", 12000, "MINUMAN");
        menu[6] = new Menu("Kopi", 15000, "MINUMAN");
        menu[7] = new Menu("Air Mineral", 6000, "MINUMAN");

        tampilkanMenu(menu);

        // Maksimal 4 menu yang bisa dipesan (sesuai soal).
        Menu pesanan1 = null;
        Menu pesanan2 = null;
        Menu pesanan3 = null;
        Menu pesanan4 = null;
        int qty1 = 0;
        int qty2 = 0;
        int qty3 = 0;
        int qty4 = 0;

        // Tidak menggunakan struktur perulangan: proses input dibuat 4 kali (slot 1-4).
        System.out.println();
        System.out.println("Input pesanan (maksimal 4 menu). Masukkan 0 untuk selesai.");

        int nomor;
        System.out.print("Pilih nomor menu untuk Pesanan-1: ");
        nomor = bacaInt(scanner);
        if (nomor != 0) {
            pesanan1 = ambilMenuBerdasarkanNomor(menu, nomor);
            if (pesanan1 != null) {
                System.out.print("Jumlah Pesanan-1: ");
                qty1 = bacaInt(scanner);
                if (qty1 < 1) {
                    qty1 = 1;
                }
            } else {
                System.out.println("Nomor menu tidak valid. Pesanan-1 diabaikan.");
            }
        }

        System.out.print("Pilih nomor menu untuk Pesanan-2: ");
        nomor = bacaInt(scanner);
        if (nomor != 0) {
            pesanan2 = ambilMenuBerdasarkanNomor(menu, nomor);
            if (pesanan2 != null) {
                System.out.print("Jumlah Pesanan-2: ");
                qty2 = bacaInt(scanner);
                if (qty2 < 1) {
                    qty2 = 1;
                }
            } else {
                System.out.println("Nomor menu tidak valid. Pesanan-2 diabaikan.");
            }
        }

        System.out.print("Pilih nomor menu untuk Pesanan-3: ");
        nomor = bacaInt(scanner);
        if (nomor != 0) {
            pesanan3 = ambilMenuBerdasarkanNomor(menu, nomor);
            if (pesanan3 != null) {
                System.out.print("Jumlah Pesanan-3: ");
                qty3 = bacaInt(scanner);
                if (qty3 < 1) {
                    qty3 = 1;
                }
            } else {
                System.out.println("Nomor menu tidak valid. Pesanan-3 diabaikan.");
            }
        }

        System.out.print("Pilih nomor menu untuk Pesanan-4: ");
        nomor = bacaInt(scanner);
        if (nomor != 0) {
            pesanan4 = ambilMenuBerdasarkanNomor(menu, nomor);
            if (pesanan4 != null) {
                System.out.print("Jumlah Pesanan-4: ");
                qty4 = bacaInt(scanner);
                if (qty4 < 1) {
                    qty4 = 1;
                }
            } else {
                System.out.println("Nomor menu tidak valid. Pesanan-4 diabaikan.");
            }
        }

        // Hitung subtotal dari item yang dipilih.
        int subtotal = 0;
        if (pesanan1 != null)
            subtotal += pesanan1.getHarga() * qty1;
        if (pesanan2 != null)
            subtotal += pesanan2.getHarga() * qty2;
        if (pesanan3 != null)
            subtotal += pesanan3.getHarga() * qty3;
        if (pesanan4 != null)
            subtotal += pesanan4.getHarga() * qty4;

        double pajak = subtotal * PAJAK_PERSEN;
        int biayaPelayanan = BIAYA_PELAYANAN;

        // Total awal = subtotal + pajak + biaya pelayanan.
        // Ambang diskon / gratis minuman dihitung dari total awal ini (sesuai
        // interpretasi "total biaya keseluruhan").
        double totalAwal = subtotal + pajak + biayaPelayanan;

        // Struktur keputusan: nested if untuk promo.
        double diskon = 0;
        if (totalAwal > BATAS_DISKON) {
            diskon = totalAwal * DISKON_PERSEN;
        }

        // Penawaran: gratis 1 item minuman (harga 1 porsi) jika totalAwal > 50.000.
        // Dipilih minuman termurah dari pesanan pelanggan, supaya penawaran selalu
        // masuk akal.
        Menu minumanGratis = null;
        int nilaiGratis = 0;
        if (totalAwal > BATAS_GRATIS_MINUMAN) {
            minumanGratis = cariMinumanTermurah(pesanan1, qty1, pesanan2, qty2, pesanan3, qty3, pesanan4, qty4);
            if (minumanGratis != null) {
                nilaiGratis = minumanGratis.getHarga(); // gratis hanya 1 porsi
            }
        }

        // Urutan perhitungan promo yang dipakai:
        // 1) totalSetelahDiskon = totalAwal - diskon
        // 2) totalAkhir = totalSetelahDiskon - nilaiGratis
        double totalSetelahDiskon = totalAwal - diskon;
        double totalAkhir = totalSetelahDiskon - nilaiGratis;
        if (totalAkhir < 0) {
            totalAkhir = 0;
        }

        cetakStruk(
                pesanan1, qty1,
                pesanan2, qty2,
                pesanan3, qty3,
                pesanan4, qty4,
                subtotal, pajak, biayaPelayanan,
                totalAwal, diskon,
                minumanGratis, nilaiGratis,
                totalAkhir);
    }

    private static void tampilkanMenu(Menu[] menu) {
        System.out.println("=== DAFTAR MENU RESTORAN ===");
        System.out.println();
        System.out.println("MAKANAN:");
        // Tidak menggunakan perulangan: tampilkan 4 makanan secara manual.
        tampilkanMenuBaris(1, menu[0]);
        tampilkanMenuBaris(2, menu[1]);
        tampilkanMenuBaris(3, menu[2]);
        tampilkanMenuBaris(4, menu[3]);

        System.out.println();
        System.out.println("MINUMAN:");
        // Tidak menggunakan perulangan: tampilkan 4 minuman secara manual.
        tampilkanMenuBaris(5, menu[4]);
        tampilkanMenuBaris(6, menu[5]);
        tampilkanMenuBaris(7, menu[6]);
        tampilkanMenuBaris(8, menu[7]);
    }

    private static void tampilkanMenuBaris(int nomor, Menu item) {
        System.out.printf("%d. %s - Rp %,d%n", nomor, item.getNama(), item.getHarga());
    }

    private static Menu ambilMenuBerdasarkanNomor(Menu[] menu, int nomor) {
        // Struktur keputusan switch-case untuk memilih item berdasarkan nomor menu.
        switch (nomor) {
            case 1:
                return menu[0];
            case 2:
                return menu[1];
            case 3:
                return menu[2];
            case 4:
                return menu[3];
            case 5:
                return menu[4];
            case 6:
                return menu[5];
            case 7:
                return menu[6];
            case 8:
                return menu[7];
            default:
                return null;
        }
    }

    private static int bacaInt(Scanner scanner) {
        // Menangani input non-angka tanpa perulangan: jika salah, kembalikan 0.
        // (Di tugas ini fokusnya struktur keputusan; input handling dibuat sederhana.)
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        scanner.next(); // buang token yang bukan angka
        return 0;
    }

    private static Menu cariMinumanTermurah(
            Menu m1, int q1,
            Menu m2, int q2,
            Menu m3, int q3,
            Menu m4, int q4) {
        // Memilih minuman termurah dari pesanan.
        // Tidak memakai perulangan: evaluasi slot 1-4 dengan if-else.
        Menu termurah = null;

        if (m1 != null && q1 > 0 && m1.isMinuman()) {
            termurah = m1;
        }

        if (m2 != null && q2 > 0 && m2.isMinuman()) {
            if (termurah == null) {
                termurah = m2;
            } else if (m2.getHarga() < termurah.getHarga()) {
                termurah = m2;
            }
        }

        if (m3 != null && q3 > 0 && m3.isMinuman()) {
            if (termurah == null) {
                termurah = m3;
            } else if (m3.getHarga() < termurah.getHarga()) {
                termurah = m3;
            }
        }

        if (m4 != null && q4 > 0 && m4.isMinuman()) {
            if (termurah == null) {
                termurah = m4;
            } else if (m4.getHarga() < termurah.getHarga()) {
                termurah = m4;
            }
        }

        return termurah;
    }

    private static void cetakStruk(
            Menu p1, int q1,
            Menu p2, int q2,
            Menu p3, int q3,
            Menu p4, int q4,
            int subtotal, double pajak, int biayaPelayanan,
            double totalAwal, double diskon,
            Menu minumanGratis, int nilaiGratis,
            double totalAkhir) {
        System.out.println();
        System.out.println("=========== STRUK PEMBAYARAN ===========");

        if (p1 == null && p2 == null && p3 == null && p4 == null) {
            System.out.println("Tidak ada pesanan.");
        } else {
            System.out.println("Daftar Pesanan:");
            // Tidak menggunakan perulangan: cetak maksimal 4 baris pesanan secara manual.
            cetakBarisPesanan(p1, q1);
            cetakBarisPesanan(p2, q2);
            cetakBarisPesanan(p3, q3);
            cetakBarisPesanan(p4, q4);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Subtotal               : Rp %,d%n", subtotal);
        System.out.printf("Pajak (10%%)             : Rp %,.0f%n", pajak);
        System.out.printf("Biaya Pelayanan        : Rp %,d%n", biayaPelayanan);
        System.out.printf("Total (sebelum promo)  : Rp %,.0f%n", totalAwal);

        if (diskon > 0) {
            System.out.printf("Diskon (10%%)            : -Rp %,.0f%n", diskon);
        } else {
            System.out.println("Diskon (10%)           : Rp 0");
        }

        if (minumanGratis != null) {
            System.out.printf("Gratis Minuman         : %s (-%s)%n", minumanGratis.getNama(),
                    formatRupiah(nilaiGratis));
        } else {
            System.out.println("Gratis Minuman         : -");
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BAYAR            : Rp %,.0f%n", totalAkhir);
        System.out.println("========================================");
    }

    private static void cetakBarisPesanan(Menu menu, int qty) {
        if (menu == null) {
            return;
        }
        int totalItem = menu.getHarga() * qty;
        System.out.printf("- %s x%d = Rp %,d%n", menu.getNama(), qty, totalItem);
    }

    private static String formatRupiah(int nilai) {
        return String.format("Rp %,d", nilai);
    }
}
