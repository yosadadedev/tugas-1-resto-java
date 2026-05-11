public class Menu {
    private final String nama;
    private final int harga;
    private final String kategori; // "MAKANAN" atau "MINUMAN"

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isMinuman() {
        return "MINUMAN".equalsIgnoreCase(kategori);
    }
}
