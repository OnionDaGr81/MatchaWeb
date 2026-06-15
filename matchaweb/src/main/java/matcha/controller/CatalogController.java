/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/matcha_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static void getAllTalents(Context ctx) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            
            // 1. Ambil semua user yang memiliki role TALENT
            PreparedStatement stmtTalent = conn.prepareStatement("SELECT * FROM users WHERE role = 'TALENT'");
            ResultSet rsTalent = stmtTalent.executeQuery();
            
            List<Map<String, Object>> talents = new ArrayList<>();
            
            while (rsTalent.next()) {
                Map<String, Object> talent = new HashMap<>();
                String talentId = rsTalent.getString("id");
                
                talent.put("id", talentId);
                talent.put("nama", rsTalent.getString("nama"));
                talent.put("email", rsTalent.getString("email"));
                talent.put("hariTersedia", "Setiap Hari"); // DB kita belum ada kolom hari, set default dulu
                
                // 2. Cari layanan (services) milik talent ini
                PreparedStatement stmtService = conn.prepareStatement("SELECT * FROM services WHERE talent_id = ?");
                stmtService.setString(1, talentId);
                ResultSet rsService = stmtService.executeQuery();
                
                List<Map<String, Object>> services = new ArrayList<>();
                double tarifTermurah = 0;
                boolean isFirst = true;
                
                while (rsService.next()) {
                    Map<String, Object> service = new HashMap<>();
                    service.put("id", rsService.getString("id"));
                    service.put("nama", rsService.getString("nama_layanan"));
                    
                    // Cari tarif paling murah untuk ditampilkan di card depan
                    double tarif = rsService.getDouble("tarif_dasar");
                    if (isFirst || tarif < tarifTermurah) {
                        tarifTermurah = tarif;
                        isFirst = false;
                    }
                    services.add(service);
                }
                
                talent.put("services", services);
                talent.put("tarifPerJam", tarifTermurah);
                
                talents.add(talent);
                
                rsService.close();
                stmtService.close();
            }
            
            // 3. Kirim data JSON ke UI HTML
            ctx.status(200).json(talents);
            
            rsTalent.close();
            stmtTalent.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", "Terjadi kesalahan server: " + e.getMessage()));
        }
    }
    public static void getTalentById(io.javalin.http.Context ctx) {
        String talentId = ctx.pathParam("talentId");
        
        java.util.Map<String, Object> talent = new java.util.HashMap<>();
        talent.put("id", talentId);
        talent.put("nama", "Kaizone");
        talent.put("email", "Kaizone@gmail.com");
        talent.put("tarifPerJam", 150000);
        talent.put("hariTersedia", "Senin, Rabu, Jumat, Sabtu");
        
        ctx.json(talent);
    }
}