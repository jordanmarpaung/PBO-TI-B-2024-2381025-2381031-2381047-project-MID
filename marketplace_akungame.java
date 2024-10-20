import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class marketplace_akungame {
    private static List<Pengguna> daftarPengguna = new ArrayList<>();
    private static List<AkunGame> daftarAkunGame = new ArrayList<>();
    private static List<String> notifikasi = new ArrayList<>();
    private static List<Transaksi> riwayatTransaksi = new ArrayList<>();
    private static List<RatingUlasan> daftarRatingUlasan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static LoyaltyPoints loyaltyPoints = new LoyaltyPoints();
    private static double saldoPengguna = 100000;

    public static void main(String[] args) {
        // Menambahkan beberapa akun game ke dalam daftar
        daftarAkunGame.add(new AkunGame("12345", "ProGamer", 80, "60+ hero unlocked", "5 skin epic, 10 skin rare", "Tersedia untuk dijual", 150000));
        daftarAkunGame.add(new AkunGame("23456", "CasualPlayer", 50, "30 hero unlocked", "2 skin rare", "Tersedia untuk dijual", 250000));
        daftarAkunGame.add(new AkunGame("34567", "StrategyKing", 70, "40 hero unlocked", "3 skin epic, 5 skin rare", "Tersedia untuk dijual", 350000));
        daftarAkunGame.add(new AkunGame("45678", "NoobMaster", 30, "10 hero unlocked", "1 skin rare", "Tersedia untuk dijual", 450000));
        daftarAkunGame.add(new AkunGame("56789", "RushHour", 90, "70+ hero unlocked", "7 skin epic, 12 skin rare", "Tersedia untuk dijual", 650000));
        daftarAkunGame.add(new AkunGame("67890", "TeamPlayer", 60, "50 hero unlocked", "4 skin epic, 8 skin rare", "Tersedia untuk dijual", 550000));
        daftarAkunGame.add(new AkunGame("78901", "EliteHunter", 75, "55 hero unlocked", "6 skin epic, 9 skin rare", "Tersedia untuk dijual", 750000));
        daftarAkunGame.add(new AkunGame("89012", "SoloQMaster", 40, "25 hero unlocked", "2 skin rare", "Tersedia untuk dijual", 300000));
        daftarAkunGame.add(new AkunGame("90123", "SupportQueen", 65, "45 hero unlocked", "3 skin epic, 6 skin rare", "Tersedia untuk dijual", 400000));
        daftarAkunGame.add(new AkunGame("01234", "FastLearner", 55, "35 hero unlocked", "1 skin rare", "Tersedia untuk dijual", 350000));

        while (true) {
            tampilkanMenu();
        }
    }

    public static void tampilkanMenu() {
        System.out.println("\n=== Menu Utama ===");
        System.out.println("1. Registrasi");
        System.out.println("2. Login");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1:
                tampilkanMenuRegistrasi();
                break;
            case 2:
                tampilkanMenuLogin();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opsi tidak valid. Silakan coba lagi.");
        }
    }

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

    public static void tampilkanMenuLogin() {
        System.out.println("\n=== Login Pengguna ===");
        String email = input("Email");
        String kataSandi = inputKataSandi("Password");

        if (autentikasiPengguna(email, kataSandi)) {
            System.out.println("Login berhasil! Selamat datang, " + getNamaPengguna(email) + ".");
            tampilkanMenuProfil(getNamaPengguna(email));
        } else {
            System.out.println("Login gagal. Email atau password salah.");
        }
    }

    public static void tampilkanMenuProfil(String namaPengguna) {
        while (true) {
            System.out.println("\n=== Menu Profil ===");
            System.out.println("1. Update Profil");
            System.out.println("2. Top Up Akun");
            System.out.println("3. Daftar Akun Game");
            System.out.println("4. Cari Akun Game");
            System.out.println("5. Beli Akun Game");
            System.out.println("6. Lihat Notifikasi");
            System.out.println("7. Tampilkan Loyalty Points");
            System.out.println("8. Verifikasi Akun Game");
            System.out.println("9. Riwayat Transaksi");
            System.out.println("10. Rating dan Ulasan");
            System.out.println("11. Logout");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

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
                    tampilkanLoyaltyPoints();
                    break;
                case 8:
                    verifikasiAkunGame(namaPengguna);
                    break;
                case 9:
                    tampilkanRiwayatTransaksi();
                    break;
                case 10:
                    tampilkanRatingDanUlasan(namaPengguna);
                    break;
                case 11:
                    System.out.println("Anda telah logout.");
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }

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

    public static void topUpAkun(String namaPengguna) {
        System.out.println("\n=== Top Up Akun ===");
        System.out.print("Masukkan jumlah yang ingin ditambahkan ke saldo: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("=== Metode Pembayaran ===");
        System.out.println("1. Rekening BCA : 9898776557");
        System.out.println("2. 0812609878 (E-Wallet)");
        System.out.println("3. Tunai");
        System.out.print("Pilih metode pembayaran: ");
        int metodePembayaran = scanner.nextInt();
        scanner.nextLine();

        if (metodePembayaran == 1 || metodePembayaran == 2 || metodePembayaran == 3) {
            System.out.println("Top up berhasil. Saldo Anda kini bertambah sebesar: " + jumlah);
            notifikasi.add("Top up berhasil sebesar: " + jumlah + " (Metode Pembayaran: " + metodePembayaran + ")");
            loyaltyPoints.tambahPoints(10); // Tambah 10 poin tetap
            saldoPengguna += jumlah;
            riwayatTransaksi.add(new Transaksi(namaPengguna, "Top Up", jumlah));
        } else {
            System.out.println("Metode pembayaran tidak valid.");
        }
    }

    public static void tampilkanDaftarAkunGame() {
        System.out.println("\n=== Daftar Akun Game ===");
        for (AkunGame akun : daftarAkunGame) {
            System.out.printf("ID: %s, Nama: %s, Level: %d, Harga: %.2f, Status: %s%n",
                    akun.getIdAkun(), akun.getNamaPengguna(), akun.getLevel(), akun.getHarga(), akun.getStatus());
        }
    }

    public static void cariAkunGame() {
        System.out.print("Masukkan ID Akun Game yang ingin dicari: ");
        String idAkun = scanner.nextLine();
        AkunGame akun = cariAkun(idAkun);
        if (akun != null) {
            System.out.printf("ID: %s, Nama: %s, Level: %d, Harga: %.2f, Status: %s%n",
                    akun.getIdAkun(), akun.getNamaPengguna(), akun.getLevel(), akun.getHarga(), akun.getStatus());
        } else {
            System.out.println("Akun Game tidak ditemukan.");
        }
    }

    public static void beliAkunGame(String namaPengguna) {
        System.out.print("Masukkan ID Akun Game yang ingin dibeli: ");
        String idAkun = scanner.nextLine();
        AkunGame akun = cariAkun(idAkun);
        if (akun != null) {
            if (saldoPengguna >= akun.getHarga()) {
                System.out.println("=== Metode Pembayaran ===");
                System.out.println("1. Rekening BCA : 9898776557");
                System.out.println("2. 0812609878 (E-Wallet)");
                System.out.println("3. Tunai");
                System.out.print("Pilih metode pembayaran: ");
                int metodePembayaran = scanner.nextInt();
                scanner.nextLine();

                // Simulate payment processing
                System.out.println("Memproses pembayaran...");
                try {
                    Thread.sleep(4000); // Delay of 4 seconds
                } catch (InterruptedException e) {
                    System.out.println("Terjadi kesalahan saat memproses pembayaran.");
                    return;
                }

                // Confirm payment after delay
                saldoPengguna -= akun.getHarga();
                notifikasi.add("Anda telah membeli Akun Game: " + akun.getNamaPengguna());
                riwayatTransaksi.add(new Transaksi(namaPengguna, "Beli Akun", akun.getHarga()));
                loyaltyPoints.tambahPoints(10); // Tambah 10 poin tetap

                // New Messages
                System.out.println("Pembayaran berhasil. Informasi akun akan segera dikirim.");
                System.out.println("Mengirim informasi akun...");
                System.out.printf("Username: %s%n", akun.getNamaPengguna());
                System.out.println("Password: [DIKIRIM KE EMAIL ANDA]");
                System.out.println("Email terkait: [DIKIRIM KE EMAIL ANDA]");
                System.out.println("Kode verifikasi atau informasi keamanan tambahan: [DIKIRIM KE EMAIL ANDA]");
                System.out.println("Konfirmasi transaksi berhasil! Anda telah membeli akun: " + akun.getNamaPengguna());
            } else {
                System.out.println("Saldo Anda tidak mencukupi.");
            }
        } else {
            System.out.println("Akun Game tidak ditemukan.");
        }
    }

    public static void tampilkanNotifikasi() {
        System.out.println("\n=== Notifikasi ===");
        for (String notifikasiItem : notifikasi) {
            System.out.println("- " + notifikasiItem);
        }
    }

    public static void tampilkanLoyaltyPoints() {
        System.out.println("Loyalty Points Anda: " + loyaltyPoints.getPoints());
    }

    public static void verifikasiAkunGame(String namaPengguna) {
        System.out.println("=== Verifikasi Akun Game ===");
        System.out.print("Masukkan ID Akun Game yang ingin diverifikasi: ");
        String idAkun = scanner.nextLine();

        AkunGame akun = cariAkun(idAkun);
        if (akun != null && akun.getStatus().equals("Tersedia untuk dijual")) {
            System.out.print("Masukkan nama baru: ");
            String namaBaru = scanner.nextLine();
            System.out.print("Masukkan email baru: ");
            String emailBaru = scanner.nextLine();
            System.out.print("Masukkan password baru: ");
            String passwordBaru = scanner.nextLine();

            // Set the account status to "Terjual"
            akun.setStatus("Terjual");
            notifikasi.add("Akun Game " + akun.getNamaPengguna() + " telah terverifikasi.");
            System.out.println("Verifikasi berhasil untuk Akun Game: " + akun.getNamaPengguna());

            // Update user information
            for (Pengguna pengguna : daftarPengguna) {
                if (pengguna.getNama().equals(namaPengguna)) {
                    pengguna.setNama(namaBaru);
                    pengguna.setEmail(emailBaru);
                    // Update password if necessary
                    // (Not implemented, as password handling requires secure practices)
                    break;
                }
            }
        } else {
            System.out.println("Akun Game tidak ditemukan atau sudah terverifikasi.");
        }
    }


    public static void tampilkanRiwayatTransaksi() {
        System.out.println("\n=== Riwayat Transaksi ===");
        for (Transaksi transaksi : riwayatTransaksi) {
            System.out.printf("Jenis: %s, Jumlah: %.2f%n", transaksi.getJenisTransaksi(), transaksi.getJumlah());
        }
    }

    public static void tampilkanRatingDanUlasan(String namaPengguna) {
        System.out.println("\n=== Rating dan Ulasan ===");

        // Allow user to add a rating and review
        String rating = input("Masukkan rating (1-5): ");
        String ulasan = input("Masukkan ulasan: ");
        daftarRatingUlasan.add(new RatingUlasan(namaPengguna, rating, ulasan));
        System.out.println("Ulasan berhasil ditambahkan.");

        // Display all ratings and reviews
        System.out.println("\n=== Semua Rating dan Ulasan ===");
        for (RatingUlasan ratingUlasan : daftarRatingUlasan) {
            System.out.printf("Nama: %s, Rating: %s, Ulasan: %s%n",
                    ratingUlasan.getNamaPengguna(), ratingUlasan.getRating(), ratingUlasan.getUlasan());
        }
    }

    // Helper methods
    public static boolean tambahPengguna(Pengguna pengguna) {
        for (Pengguna p : daftarPengguna) {
            if (p.getEmail().equals(pengguna.getEmail())) {
                return false; // Email sudah digunakan
            }
        }
        daftarPengguna.add(pengguna);
        return true;
    }

    public static boolean autentikasiPengguna(String email, String kataSandi) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email) && pengguna.getKataSandi().equals(kataSandi)) {
                return true;
            }
        }
        return false;
    }

    public static String getNamaPengguna(String email) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email)) {
                return pengguna.getNama();
            }
        }
        return null;
    }

    public static AkunGame cariAkun(String idAkun) {
        for (AkunGame akun : daftarAkunGame) {
            if (akun.getIdAkun().equals(idAkun)) {
                return akun;
            }
        }
        return null;
    }

    public static String input(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine();
    }

    public static String inputKataSandi(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine();
    }
}

// Class definitions for Pengguna, AkunGame, Transaksi, RatingUlasan, and LoyaltyPoints
class Pengguna {
    private String nama;
    private String email;
    private String kataSandi;

    public Pengguna(String nama, String email, String kataSandi) {
        this.nama = nama;
        this.email = email;
        this.kataSandi = kataSandi;
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
}

class AkunGame {
    private String idAkun;
    private String namaPengguna;
    private int level;
    private String informasi;
    private String skin;
    private String status;
    private double harga;

    public AkunGame(String idAkun, String namaPengguna, int level, String informasi, String skin, String status, double harga) {
        this.idAkun = idAkun;
        this.namaPengguna = namaPengguna;
        this.level = level;
        this.informasi = informasi;
        this.skin = skin;
        this.status = status;
        this.harga = harga;
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

    public double getHarga() {
        return harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class Transaksi {
    private String namaPengguna;
    private String jenisTransaksi;
    private double jumlah;

    public Transaksi(String namaPengguna, String jenisTransaksi, double jumlah) {
        this.namaPengguna = namaPengguna;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlah = jumlah;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public double getJumlah() {
        return jumlah;
    }
}

class RatingUlasan {
    private String namaPengguna;
    private String rating;
    private String ulasan;

    public RatingUlasan(String namaPengguna, String rating, String ulasan) {
        this.namaPengguna = namaPengguna;
        this.rating = rating;
        this.ulasan = ulasan;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getRating() {
        return rating;
    }

    public String getUlasan() {
        return ulasan;
    }
}

class LoyaltyPoints {
    private int points;

    public void tambahPoints(int jumlah) {
        points += jumlah;
    }

    public int getPoints() {
        return points;
    }
}