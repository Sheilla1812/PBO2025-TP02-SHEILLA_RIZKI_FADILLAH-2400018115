import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== SISTEM PENGELOLAAN ASISTEN =====");
        System.out.print("Login Sebagai (admin/asisten): ");
        String role = input.nextLine();

        if (role.equalsIgnoreCase("admin")) {
            System.out.print("Masukkan Password Admin: ");
            String pass = input.nextLine();
            if (pass.equals("admin123")) {
                menuAdmin(input);
            } else {
                System.out.println("Password Salah!");
            }
        } else if (role.equalsIgnoreCase("asisten")) {
            menuAsisten();
        } else {
            System.out.println("Role tidak dikenali.");
        }

        System.out.println("\n===== PROSES SELESAI =====");
        input.close();
    }

    public static void menuAdmin(Scanner input) {
        System.out.println("\n--- MODE INPUT DATA (ADMIN) ---");
        System.out.print("Nama Asisten    : ");
        String nama = input.nextLine();
        System.out.print("NIM             : ");
        String nim = input.nextLine();
        System.out.print("Mata Praktikum  : ");
        String praktikum = input.nextLine();

        // Input Tugas
        System.out.print("Jumlah Tugas yang diselesaikan: ");
        int jml = input.nextInt();
        input.nextLine();
        ArrayList<String> listTugas = new ArrayList<>();
        for (int i = 0; i < jml; i++) {
            System.out.print("Tugas ke-" + (i + 1) + ": ");
            listTugas.add(input.nextLine());
        }

        // Input Nilai
        System.out.println("\n--- INPUT NILAI KINERJA ---");
        System.out.print("Nilai Kedisiplinan   : ");
        int d = input.nextInt();
        System.out.print("Nilai Tanggung Jawab : ");
        int tj = input.nextInt();
        System.out.print("Nilai Komunikasi     : ");
        int k = input.nextInt();
        input.nextLine();

        System.out.print("Pesan/Catatan Admin  : ");
        String pesan = input.nextLine();

        // Hitung Logika
        EvaluasiKinerja ev = new EvaluasiKinerja(d, tj, k);
        int skor = ev.hitungNilai();
        String kat = ev.kategori();
        String status = ev.getStatusKelayakan();

        // Simpan ke File
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kinerja.txt"))) {
            writer.write("========== LAPORAN EVALUASI ASISTEN ==========\n");
            writer.write("Nama      : " + nama + "\n");
            writer.write("NIM       : " + nim + "\n");
            writer.write("Praktikum : " + praktikum + "\n");
            writer.write("----------------------------------------------\n");
            writer.write("DAFTAR TUGAS:\n");
            for (String t : listTugas) {
                writer.write("- " + t + "\n");
            }
            writer.write("----------------------------------------------\n");
            writer.write("HASIL PENILAIAN:\n");
            writer.write("Rata-rata Nilai : " + skor + "\n");
            writer.write("Kategori        : " + kat + "\n");
            writer.write("STATUS AKHIR    : " + status + "\n");
            writer.write("PESAN ADMIN     : " + pesan + "\n");
            writer.write("==============================================");
            System.out.println("\n[Berhasil] Data disimpan ke kinerja.txt");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    public static void menuAsisten() {
        System.out.println("\n--- MODE LIHAT NILAI (ASISTEN) ---");
        File file = new File("kinerja.txt");

        if (!file.exists()) {
            System.out.println("Maaf, Admin belum menginputkan evaluasi Anda.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}