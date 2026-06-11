package matcha.Main;

import matcha.controller.AuthController;
import matcha.view.MainMenuView;

public class MatchaApp {
    public static void main(String[] args) {
        // Inisialisasi komponen utama (Dependency Injection sederhana)
        AuthController authController = new AuthController();
        MainMenuView mainMenuView = new MainMenuView(authController);

        // Mulai aplikasi
        mainMenuView.displayMenu();
    }
}