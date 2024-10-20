import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class marketplace_akungame {
    private static List<Pengguna> daftarPengguna = new ArrayList<>();
    private static List<AkunGame> daftarAkunGame = new ArrayList<>();
    private static List<String> notifikasi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        // Menambahkan beberapa akun game ke dalam daftar dengan harga
        daftarAkunGame.add(new AkunGame("12345", "ProGamer", 80, "60+ hero unlocked, termasuk Esmeralda, Lancelot", "5 skin epic, 10 skin rare", "Tersedia untuk dijual", 150000));
        daftarAkunGame.add(new AkunGame("23456", "CasualPlayer", 50, "30 hero unlocked, termasuk Layla dan Johnson", "2 skin rare", "Tersedia untuk dijual", 250000));
        daftarAkunGame.add(new AkunGame("34567", "StrategyKing", 70, "40 hero unlocked, termasuk Kagura dan Franco", "3 skin epic, 5 skin rare", "Tersedia untuk dijual", 350000));
        daftarAkunGame.add(new AkunGame("45678", "NoobMaster", 30, "10 hero unlocked, termasuk Miya dan Akai", "1 skin rare", "Tersedia untuk dijual", 450000));
        daftarAkunGame.add(new AkunGame("56789", "RushHour", 90, "70+ hero unlocked, termasuk Harith dan Aldous", "7 skin epic, 12 skin rare", "Tersedia untuk dijual", 650000));
        daftarAkunGame.add(new AkunGame("67890", "TeamPlayer", 60, "50 hero unlocked, termasuk Pharsa dan Leomord", "4 skin epic, 8 skin rare", "Tersedia untuk dijual", 550000));
        daftarAkunGame.add(new AkunGame("78901", "EliteHunter", 75, "55 hero unlocked, termasuk Gusion dan Angela", "6 skin epic, 9 skin rare", "Tersedia untuk dijual", 750000));
        daftarAkunGame.add(new AkunGame("89012", "SoloQMaster", 40, "25 hero unlocked, termasuk Karrie dan Chou", "2 skin rare", "Tersedia untuk dijual", 300000));
        daftarAkunGame.add(new AkunGame("90123", "SupportQueen", 65, "45 hero unlocked, termasuk Nana dan Estes", "3 skin epic, 6 skin rare", "Tersedia untuk dijual", 400000));
        daftarAkunGame.add(new AkunGame("01234", "FastLearner", 55, "35 hero unlocked, termasuk Fanny dan Saber", "1 skin rare", "Tersedia untuk dijual", 350000));

        while (true) {
            tampilkanMenu();
        }
    }

    // Menu utama
    public static void tampilkanMenu() {
        System.out.println("\n=== Menu Utama ===");
        System.out.println("1. Registrasi");
        System.out.println("2. Login");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // membersihkan newline

        switch (pilihan) {
            case 1:
                tampilkanMenuRegistrasi();
                break;
            case 2:
                tampilkanMenuLogin();
                break;
            case 3:
                System.exit(0); // Keluar dari program
                break;
            default:
                System.out.println("Opsi tidak valid. Silakan coba lagi.");
        }
    }

    // Menu Registrasi
    public static void tampilkanMenuRegistrasi() {
        System.out.println("\n=== Registrasi Pengguna ===");
        String nama = input("Nama");
        String email = input("Email");
        String kataSandi = inputKataSandi("Password");

        Pengguna penggunaBaru = new Pengguna(nama, email, kataSandi);
        if (tambahPengguna(penggunaBaru)) {
            System.out.println("Registrasi berhasil! Silakan verifikasi email Anda.");
        } else {
            System.out.println("Registrasi gagal. Email sudah digunakan.");
        }
    }

    // Menu Login
    public static void tampilkanMenuLogin() {
        System.out.println("\n=== Login Pengguna ===");
        String email = input("Email");
        String kataSandi = inputKataSandi("Password");

        if (autentikasiPengguna(email, kataSandi)) {
            System.out.println("Login berhasil! Selamat datang, " + getNamaPengguna(email) + ".");
            tampilkanMenuProfil(getNamaPengguna(email)); // Menampilkan menu profil setelah login
        } else {
            System.out.println("Login gagal. Email atau password salah.");
        }
    }

    // Menu Profil Pengguna
    public static void tampilkanMenuProfil(String namaPengguna) {
        while (true) {
            System.out.println("\n=== Menu Profil ===");
            System.out.println("1. Update Profil");
            System.out.println("2. Top Up Akun");
            System.out.println("3. Daftar Akun Game");
            System.out.println("4. Cari Akun Game");
            System.out.println("5. Beli Akun Game");
            System.out.println("6. Lihat Notifikasi");
            System.out.println("7. Logout");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            switch (pilihan) {
                case 1:
                    updateProfil(namaPengguna);
                    break;
                case 2:
                    topUpAkun(namaPengguna);
                    break;
                case 3:
                    tampilkanDaftarAkunGame();
                    break;
                case 4:
                    cariAkunGame();
                    break;
                case 5:
                    beliAkunGame(namaPengguna);
                    break;
                case 6:
                    tampilkanNotifikasi();
                    break;
                case 7:
                    System.out.println("Anda telah logout.");
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Update Profil
    public static void updateProfil(String namaPengguna) {
        System.out.println("\n=== Update Profil ===");
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getNama().equals(namaPengguna)) {
                String namaBaru = input("Nama Baru (tekan Enter untuk tidak mengubah)");
                String emailBaru = input("Email Baru (tekan Enter untuk tidak mengubah)");

                if (!namaBaru.isEmpty()) {
                    pengguna.setNama(namaBaru);
                }
                if (!emailBaru.isEmpty()) {
                    pengguna.setEmail(emailBaru);
                }
                System.out.println("Profil berhasil diperbarui.");
                return;
            }
        }
    }

    // Top Up Akun
    public static void topUpAkun(String namaPengguna) {
        System.out.println("\n=== Top Up Akun ===");
        System.out.print("Masukkan jumlah yang ingin ditambahkan ke saldo: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine(); // membersihkan newline

        System.out.println("=== Metode Pembayaran ===");
        System.out.println("1. rekening bca : 9898776557");
        System.out.println("2. 0812609878");
        System.out.println("3. 0812609878");
        System.out.print("Pilih metode pembayaran: ");
        int metodePembayaran = scanner.nextInt();
        scanner.nextLine(); // membersihkan newline

        switch (metodePembayaran) {
            case 1: // Transfer Bank
                String nomorRekening = input("Masukkan nomor rekening:");
                System.out.println("Anda memilih Transfer Bank dengan nomor rekening: " + nomorRekening);
                System.out.println("Silakan tunggu, sedang memproses transfer...");
                // Simulasi waktu tunggu untuk memproses transfer
                delay();
                tambahSaldoPengguna(namaPengguna, jumlah);
                return;
            case 2: // Pulsa
                String nomorHandphone = input("Masukkan nomor handphone penjual:");
                System.out.println("Anda memilih Pulsa dengan nomor handphone: " + nomorHandphone);
                System.out.println("Silakan tunggu, sedang memproses pulsa...");
                // Simulasi waktu tunggu untuk memproses pulsa
                delay();
                tambahSaldoPengguna(namaPengguna, jumlah);
                return;
            case 3: // E-Wallet
                String nomorDana = input("Masukkan nomor Dana (sesuai dengan nomor handphone):");
                System.out.println("Anda memilih E-Wallet dengan nomor Dana: " + nomorDana);
                System.out.println("Silakan tunggu, sedang memproses E-Wallet...");
                // Simulasi waktu tunggu untuk memproses E-Wallet
                delay();
                tambahSaldoPengguna(namaPengguna, jumlah);
                return;
            default:
                System.out.println("Metode pembayaran tidak valid.");
                return; // Kembali ke menu
        }
    }

    // Menambahkan saldo ke pengguna
    public static void tambahSaldoPengguna(String namaPengguna, double jumlah) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getNama().equals(namaPengguna)) {
                pengguna.topUp(jumlah);
                System.out.println("Top Up berhasil! Saldo Anda sekarang: " + pengguna.getSaldo());
                return;
            }
        }
    }

    // Menampilkan daftar akun game
    public static void tampilkanDaftarAkunGame() {
        System.out.println("\n=== Daftar Akun Game ===");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10s %-50s %-30s %-25s %-10s%n", "ID Akun", "Nama Pengguna", "Level", "Hero", "Skin", "Status", "Harga");
        System.out.println("--------------------------------------------------------------------------------");
        for (AkunGame akun : daftarAkunGame) {
            System.out.printf("%-10s %-15s %-10d %-50s %-30s %-25s %-10.2f%n",
                    akun.getIdAkun(),
                    akun.getNamaPengguna(),
                    akun.getLevel(),
                    akun.getHero(),
                    akun.getSkin(),
                    akun.getStatus(),
                    akun.getHarga()); // Tampilkan harga
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Mencari akun game
    public static void cariAkunGame() {
        System.out.print("Masukkan nama akun game yang ingin dicari: ");
        String namaAkun = scanner.nextLine();
        boolean ditemukan = false;

        for (AkunGame akun : daftarAkunGame) {
            if (akun.getNamaPengguna().equalsIgnoreCase(namaAkun)) {
                System.out.println("Akun game ditemukan!");
                System.out.printf("ID Akun: %s, Level: %d, Hero: %s, Skin: %s, Status: %s, Harga: %.2f%n",
                        akun.getIdAkun(), akun.getLevel(), akun.getHero(), akun.getSkin(), akun.getStatus(), akun.getHarga());
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Akun game tidak ditemukan.");
        }
    }

    // Membeli akun game
    public static void beliAkunGame(String namaPengguna) {
        System.out.print("Masukkan nama akun game yang ingin dibeli: ");
        String namaAkun = scanner.nextLine();
        boolean ditemukan = false;

        for (AkunGame akun : daftarAkunGame) {
            if (akun.getNamaPengguna().equalsIgnoreCase(namaAkun) && akun.getStatus().equals("Tersedia untuk dijual")) {
                System.out.println("Anda telah memilih akun: " + akun.getNamaPengguna());
                System.out.printf("ID Akun: %s, Level: %d, Hero: %s, Skin: %s, Harga: %.2f%n", akun.getIdAkun(), akun.getLevel(), akun.getHero(), akun.getSkin(), akun.getHarga());

                // Metode Pembayaran
                System.out.println("=== Metode Pembayaran ===");
                System.out.println("Silakan transfer ke nomor rekening BCA: 12786278690");

                // Delay 4 detik
                delay(4000);

                // Pembayaran
                System.out.println("Pembayaran sedang diproses...");
                delay(); // Simulasi waktu tunggu

                System.out.println("Pembayaran berhasil. Informasi akun akan segera dikirim.");
                // Pengiriman Informasi Akun
                System.out.println("Mengirim informasi akun...");
                delay(); // Simulasi waktu tunggu

                System.out.println("Informasi akun yang berhasil dibeli:");
                System.out.println("Username: " + akun.getNamaPengguna());
                System.out.println("Password: [DIKIRIM KE EMAIL ANDA]"); // Simulasi pengiriman password
                System.out.println("Email terkait: [DIKIRIM KE EMAIL ANDA]"); // Simulasi pengiriman email
                System.out.println("Kode verifikasi atau informasi keamanan tambahan: [DIKIRIM KE EMAIL ANDA]"); // Simulasi pengiriman kode verifikasi

                // Konfirmasi Transaksi
                System.out.println("Konfirmasi transaksi berhasil! Anda telah membeli akun: " + akun.getNamaPengguna());
                notifikasi.add("Anda telah membeli akun: " + akun.getNamaPengguna());

                // Mengubah status akun game setelah pembelian
                akun.setStatus("Tidak Tersedia");
                return; // Pembelian berhasil
            }
        }
        System.out.println("Akun game tidak ditemukan atau sudah tidak tersedia.");
    }

    // Delay dengan parameter waktu
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Tunggu selama waktu yang ditentukan
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Menampilkan notifikasi
    public static void tampilkanNotifikasi() {
        System.out.println("\n=== Notifikasi ===");
        if (notifikasi.isEmpty()) {
            System.out.println("Tidak ada notifikasi.");
        } else {
            for (String notif : notifikasi) {
                System.out.println(notif);
            }
        }
    }

    // Menunda program selama beberapa detik
    public static void delay() {
        try {
            Thread.sleep(2000); // 2 detik
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Input string dengan prompt
    public static String input(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    // Input kata sandi dengan prompt
    public static String inputKataSandi(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    // Menambahkan pengguna baru
    public static boolean tambahPengguna(Pengguna pengguna) {
        for (Pengguna p : daftarPengguna) {
            if (p.getEmail().equals(pengguna.getEmail())) {
                return false; // Email sudah digunakan
            }
        }
        daftarPengguna.add(pengguna);
        return true; // Registrasi berhasil
    }

    // Autentikasi pengguna
    public static boolean autentikasiPengguna(String email, String kataSandi) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email) && pengguna.getKataSandi().equals(kataSandi)) {
                return true; // Autentikasi berhasil
            }
        }
        return false; // Autentikasi gagal
    }

    // Mengambil nama pengguna berdasarkan email
    public static String getNamaPengguna(String email) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email)) {
                return pengguna.getNama();
            }
        }
        return null; // Tidak ditemukan
    }
}

// Kelas Pengguna
class Pengguna {
    private String nama;
    private String email;
    private String kataSandi;
    private double saldo;

    public Pengguna(String nama, String email, String kataSandi) {
        this.nama = nama;
        this.email = email;
        this.kataSandi = kataSandi;
        this.saldo = 0; // Saldo awal adalah 0
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public double getSaldo() {
        return saldo;
    }

    public void topUp(double jumlah) {
        saldo += jumlah;
    }
}

// Kelas AkunGame
class AkunGame {
    private String idAkun;
    private String namaPengguna;
    private int level;
    private String hero;
    private String skin;
    private String status;
    private double harga; // Tambahkan harga

    public AkunGame(String idAkun, String namaPengguna, int level, String hero, String skin, String status, double harga) {
        this.idAkun = idAkun;
        this.namaPengguna = namaPengguna;
        this.level = level;
        this.hero = hero;
        this.skin = skin;
        this.status = status;
        this.harga = harga; // Inisialisasi harga
    }

    public String getIdAkun() {
        return idAkun;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public int getLevel() {
        return level;
    }

    public String getHero() {
        return hero;
    }

    public String getSkin() {
        return skin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHarga() {
        return harga; // Getter untuk harga
    }
}

// tess andre
