package matcha.Main;

import io.javalin.Javalin;
import matcha.controller.AuthController;
import matcha.controller.CatalogController;
import matcha.controller.BookingApi;

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
        app.post("/login", AuthController::login);
        app.post("/register", AuthController::register);
        
        app.get("/health", ctx -> ctx.status(200).result("OK"));
        
        // Endpoint Talent
        app.get("/talents", CatalogController::getAllTalents);
        app.get("/talents/{talentId}", CatalogController::getTalentById); // <-- INI BARIS YANG SUDAH DILENGKAPI
        
        // Endpoint Booking
        app.post("/bookings", BookingApi::createBooking);
        app.get("/bookings/client/{clientId}", BookingApi::getBookingsByClient);
        app.get("/bookings/talent/{talentId}", BookingApi::getBookingsByTalent);
        app.patch("/bookings/{id}/status", BookingApi::updateStatus);
    }
}