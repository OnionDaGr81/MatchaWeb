package matcha.Main;

import io.javalin.Javalin;
import matcha.controller.AuthController;
import matcha.controller.CatalogController;

public class MatchaApp {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> it.anyHost());
            });
        }).start(7070);

        // Tambahkan baris ini agar root URL ada isinya
        app.get("/", ctx -> ctx.result("Selamat datang di Matcha API Server!"));

        //  INISIALISASI CONTROLLER 
        CatalogController catalogController = new CatalogController();
        AuthController authController = new AuthController(); // Pindah ke sini

        // ENDPOINT CATALOG 
        app.get("/api/talents", ctx -> {
            ctx.json(catalogController.getAllAvailableTalents());
        });

        app.get("/api/talents/search", ctx -> {
            String serviceName = ctx.queryParam("service");
            if (serviceName != null) {
                ctx.json(catalogController.searchTalentByService(serviceName));
            } else {
                ctx.status(400).json("{\"error\": \"Parameter service tidak boleh kosong\"}");
            }
        });

        app.get("/api/talents/{id}", ctx -> {
            String talentId = ctx.pathParam("id");
            matcha.model.Talent foundTalent = catalogController.getTalentById(talentId);
            
            if (foundTalent != null) {
                ctx.json(foundTalent);
            } else {
                ctx.status(404).json("{\"error\": \"Talent tidak ditemukan\"}");
            }
        });

        //  ENDPOINT AUTH (LOGIN) 
        app.post("/api/auth/login", ctx -> {
            LoginRequest req = ctx.bodyAsClass(LoginRequest.class);
        
            matcha.model.User loggedInUser = authController.login(req.email, req.password);
            
            if (loggedInUser != null) {
                ctx.status(200).json(loggedInUser);
            } else {
                ctx.status(401).json("{\"error\": \"Email atau password salah.\"}");
            }
        });

    } 

}

class LoginRequest {
    public String email;
    public String password;
}