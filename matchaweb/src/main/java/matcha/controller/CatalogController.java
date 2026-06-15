/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 public static void getTalentById(Context ctx) {
        String talentId = ctx.pathParam("talentId");
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            
            // Mengambil nama dan email ASLI dari database berdasarkan talentId
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ? AND role = 'TALENT'");
            stmt.setString(1, talentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Map<String, Object> talent = new HashMap<>();
                talent.put("id", rs.getString("id"));
                // Menggunakan data dari database
                talent.put("nama", rs.getString("nama")); 
                talent.put("email", rs.getString("email"));
                
                // ... (sisanya ambil layanan dari tabel services) ...
                
                ctx.status(200).json(talent);
            }
            // ... tutup koneksi ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}