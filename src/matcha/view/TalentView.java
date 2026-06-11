package matcha.view;

import matcha.model.Talent;
import java.util.Scanner;

public class TalentView {
    private Talent currentTalent;
    private Scanner scanner;

    public TalentView(Talent currentTalent) {
        this.currentTalent = currentTalent;
        this.scanner = new Scanner(System.in);
    }

    public void displayDashboard() {
        boolean isTalentSessionActive = true;

        while (isTalentSessionActive) {
            System.out.println("\n=== DASHBOARD TALENT ===");
            System.out.println("1. Atur Profil & Tambah Layanan");
            System.out.println("2. Lihat Jadwal Pemesanan");
            System.out.println("3. Ubah Status Ketersediaan (Available/Busy)");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    // TODO: Input bio baru atau layanan baru, update ke dalam objek Profile
                    System.out.println("Menu atur profil...");
                    break;
                case 2:
                    // TODO: Tampilkan isi dari scheduleList milik talent ini
                    break;
                case 3:
                    // TODO: Panggil method toggleAvailability() di objek Talent
                    break;
                case 0:
                    System.out.println("Logging out...");
                    isTalentSessionActive = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}