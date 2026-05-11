# Tugas 1 Pemrograman Berbasis Dekstop — Aplikasi Restoran (Java)

## Data Mahasiswa
- Nama: Yosada Dede Aressa
- NIM: 052984188
- UPBJJ: UT Surakarta

## Deskripsi Singkat
Program Java aplikasi restoran sederhana berbasis console yang:
- Menampilkan daftar menu makanan dan minuman beserta harga.
- Menerima input pesanan pelanggan (maksimal 4 item menu) dan jumlahnya.
- Menghitung total biaya (subtotal + pajak 10% + biaya pelayanan Rp 20.000).
- Menerapkan promo:
  - Diskon 10% jika total biaya keseluruhan > Rp 100.000
  - Gratis 1 item minuman (1 porsi) jika total biaya keseluruhan > Rp 50.000
- Mencetak struk pembayaran.

Catatan: Implementasi dibuat tanpa struktur perulangan (loop), sesuai arahan pada soal.

## Aturan Perhitungan
- Subtotal = jumlah dari (harga menu × jumlah)
- Pajak = 10% × subtotal
- Biaya pelayanan = Rp 20.000
- Total (sebelum promo) = subtotal + pajak + biaya pelayanan
- Diskon = 10% × total (sebelum promo), jika total (sebelum promo) > Rp 100.000
- Gratis minuman = potongan sebesar harga 1 porsi minuman termurah yang dipesan, jika total (sebelum promo) > Rp 50.000
- Total bayar = total (sebelum promo) − diskon − potongan gratis minuman

## Struktur File
- `Menu.java` — class `Menu` (atribut: nama, harga, kategori; helper: cek minuman)
- `Main.java` — class `Main` (input pesanan, perhitungan, dan cetak struk)

## Cara Menjalankan
Pastikan sudah terpasang JDK (Java Development Kit).

1. Compile:
   ```bash
   javac Main.java Menu.java
   ```
2. Jalankan:
   ```bash
   java Main
   ```

## Cara Pakai (Input)
- Program menampilkan daftar menu bernomor 1–8.
- Untuk setiap slot Pesanan-1 sampai Pesanan-4:
  - Masukkan nomor menu (1–8), atau `0` jika selesai.
  - Masukkan jumlah pesanan (minimal 1).

## Contoh Input Singkat
Contoh pesan:
- Pesanan-1: 1 (Nasi Padang), jumlah 2
- Pesanan-2: 5 (Es Teh), jumlah 1
- Pesanan-3: 0
- Pesanan-4: 0

## Informasi Tambahan (Opsional untuk Laporan)
- Mata kuliah:
- Kelas/Program Studi:
- Dosen/Tutor:
- Tanggal pengerjaan:
