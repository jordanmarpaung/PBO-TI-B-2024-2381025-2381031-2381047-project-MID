import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class marketplace_akungame {
    private static List<Pengguna> daftarPengguna = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
            System.out.println("3. Logout");
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
                    System.out.println("Anda telah logout.");
                    return; // Kembali ke menu utama
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
        System.out.println("1. Transfer Bank");
        System.out.println("2. Pulsa");
        System.out.println("3. E-Wallet");
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
                return; // Kembali tanpa menambah saldo
        }
    }

    // Menambah saldo pengguna
    private static void tambahSaldoPengguna(String namaPengguna, double jumlah) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getNama().equals(namaPengguna)) {
                pengguna.tambahSaldo(jumlah);
                System.out.println("Top Up berhasil! Saldo baru Anda: " + pengguna.getSaldo());
                return;
            }
        }
    }

    // Simulasi delay untuk memproses pembayaran
    public static void delay() {
        try {
            Thread.sleep(2000); // Simulasi waktu tunggu 2 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Menambahkan pengguna baru ke daftar
    public static boolean tambahPengguna(Pengguna pengguna) {
        for (Pengguna p : daftarPengguna) {
            if (p.getEmail().equals(pengguna.getEmail())) {
                return false; // Email sudah digunakan
            }
        }
        daftarPengguna.add(pengguna);
        return true;
    }

    // Autentikasi pengguna berdasarkan email dan kata sandi
    public static boolean autentikasiPengguna(String email, String kataSandi) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email) && pengguna.getKataSandi().equals(kataSandi)) {
                return true; // Login berhasil
            }
        }
        return false; // Login gagal
    }

    // Mendapatkan nama pengguna berdasarkan email
    public static String getNamaPengguna(String email) {
        for (Pengguna pengguna : daftarPengguna) {
            if (pengguna.getEmail().equals(email)) {
                return pengguna.getNama(); // Mengembalikan nama
            }
        }
        return null;
    }

    // Input helper
    public static String input(String info) {
        System.out.print(info + ": ");
        return scanner.nextLine();
    }

    // Input kata sandi dengan menampilkan bintang
    public static String inputKataSandi(String info) {
        System.out.print(info + ": ");
        StringBuilder kataSandi = new StringBuilder();
        while (true) {
            char ch = ambilKarakter();
            if (ch == '\n') {
                break; // Jika tekan Enter, keluar dari loop
            } else {
                kataSandi.append(ch);
                System.out.print('*'); // Tampilkan bintang untuk setiap karakter
            }
        }
        System.out.println(); // Baris baru setelah selesai
        return kataSandi.toString();
    }

    // Mengambil karakter dari input (membaca dari System.in)
    private static char ambilKarakter() {
        try {
            return (char) System.in.read(); // Membaca karakter dari input
        } catch (Exception e) {
            return '\n'; // Kembali ke Enter jika ada kesalahan
        }
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
        this.saldo = 0.0; // Saldo awal
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

    public void tambahSaldo(double jumlah) {
        this.saldo += jumlah; // Menambahkan saldo
    }
}
