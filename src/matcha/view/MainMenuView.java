package matcha.view;

import matcha.controller.AuthController;
import matcha.model.User;
import matcha.model.Client;
import matcha.model.Talent;
import java.util.Scanner;

public class MainMenuView {
    private AuthController authController;
    private Scanner scanner;

    public MainMenuView(AuthController authController) {
        this.authController = authController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== SELAMAT DATANG DI MATCHA ===");
            System.out.println("Agensi Talent Pendamping Profesional");
            System.out.println("1. Login");
            System.out.println("2. Register sebagai Client");
            System.out.println("3. Register sebagai Talent");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer (enter)

            switch (pilihan) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    // TODO: Panggil fungsi registerClient dari AuthController
                    System.out.println("Menu Register Client belum diimplementasi.");
                    break;
                case 3:
                    // TODO: Panggil fungsi registerTalent dari AuthController
                    System.out.println("Menu Register Talent belum diimplementasi.");
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan Matcha!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    private void handleLogin() {
        System.out.print("\nMasukkan Email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        boolean isSuccess = authController.login(email, password);

        if (isSuccess) {
            User loggedInUser = authController.getCurrentUser();
            System.out.println("Login berhasil! Selamat datang, " + loggedInUser.getNama());

            // Redirect ke View yang sesuai dengan tipe objek (Polimorfisme)
            if (loggedInUser instanceof Client) {
                ClientView clientView = new ClientView((Client) loggedInUser);
                clientView.displayDashboard();
            } else if (loggedInUser instanceof Talent) {
                TalentView talentView = new TalentView((Talent) loggedInUser);
                talentView.displayDashboard();
            }
        } else {
            System.out.println("Login gagal! Email atau password salah.");
        }
    }
}
