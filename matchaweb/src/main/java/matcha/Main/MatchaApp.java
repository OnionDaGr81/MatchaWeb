package matcha.Main;

import io.javalin.Javalin;
import matcha.controller.AuthController;

public class MatchaApp {
    public static void main(String[] args) {
        // 1. Nyalakan Server & Aktifkan CORS
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> it.anyHost());
            });
        }).start(7070);

        System.out.println("Server Matcha API Berjalan di http://localhost:7070");

        // 2. Daftarkan Endpoint sesuai permintaan UI HTML
        // Saat UI memanggil /login, Javalin akan menjalankan AuthController.login
        app.post("/login", AuthController::login);
        app.post("/register", AuthController::register);
        
        // (Nanti kita akan tambahkan /talents dan /bookings di sini)
        // Endpoint untuk mengecek status server (agar spanduk kuning hilang)
        app.get("/health", ctx -> ctx.status(200).result("OK"));
        
        // Endpoint untuk mengambil daftar talent dari database
        app.get("/talents", matcha.controller.CatalogController::getAllTalents);
    }
}