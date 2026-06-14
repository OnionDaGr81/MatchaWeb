package matcha.view;

import matcha.model.Client;
import java.util.Scanner;

public class ClientView {
    private Client currentClient;
    private Scanner scanner;

    public ClientView(Client currentClient) {
        this.currentClient = currentClient;
        this.scanner = new Scanner(System.in);
    }

    public void displayDashboard() {
        boolean isClientSessionActive = true;

        while (isClientSessionActive) {
            System.out.println("\n=== DASHBOARD CLIENT ===");
            System.out.println("1. Lihat Katalog Talent & Layanan");
            System.out.println("2. Buat Pesanan (Booking)");
            System.out.println("3. Lihat Riwayat Pesanan & Tagihan");
            System.out.println("4. Berikan Ulasan (Review)");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    // TODO: Panggil CatalogController untuk menampilkan data
                    System.out.println("Menampilkan katalog...");
                    break;
                case 2:
                    // TODO: Ambil input detail pesanan, panggil BookingController
                    break;
                case 3:
                    // TODO: Tampilkan list invoice, tawarkan menu bayar via PaymentController
                    break;
                case 4:
                    // TODO: Ambil input string komentar, panggil ReviewController
                    break;
                case 0:
                    System.out.println("Logging out...");
                    // TODO: Panggil fungsi logout dari AuthController jika diperlukan
                    isClientSessionActive = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
