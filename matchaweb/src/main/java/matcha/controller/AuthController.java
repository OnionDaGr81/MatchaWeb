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
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthController {

    // Konfigurasi Database (Sesuaikan dengan Laragon)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/matcha_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    // Method untuk LOGIN
    public static void login(Context ctx) {
        try {
            // 1. Tangkap email & password dari UI HTML
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(ctx.body());
            String email = body.get("email").asText();
            String password = body.get("password").asText();

            // 2. Cek ke Database MariaDB
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            // 3. Jika ketemu, kirim JSON sukses ke UI
            if (rs.next()) {
                Map<String, Object> res = new HashMap<>();
                res.put("token", "token-rahasia-" + rs.getString("id"));
                res.put("id", rs.getString("id"));     // PENTING: Untuk UI menyimpan sesi
                res.put("nama", rs.getString("nama")); // PENTING: Untuk ditampilkan di sidebar
                res.put("role", rs.getString("role")); // PENTING: Untuk menentukan Dashboard
                
                ctx.status(200).json(res);
            } else {
                ctx.status(401).json(Map.of("error", "Email atau password salah."));
            }

            rs.close(); stmt.close(); conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", "Terjadi kesalahan server: " + e.getMessage()));
        }
    }

    // Method untuk REGISTER
    public static void register(Context ctx) {
        try {
            // 1. Tangkap data dari form registrasi UI
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(ctx.body());
            String email = body.get("email").asText();
            String password = body.get("password").asText();
            String nama = body.get("nama").asText();
            String noTelp = body.get("no_telp").asText();
            String role = body.get("role").asText();

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            
            // 2. Cek apakah email sudah dipakai
            PreparedStatement checkStmt = conn.prepareStatement("SELECT email FROM users WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                ctx.status(400).json(Map.of("error", "Email sudah terdaftar."));
                return;
            }

            // 3. Buat ID Baru (CL... untuk Client, TL... untuk Talent)
            String prefix = role.equals("CLIENT") ? "CL" : "TL";
            String newId = prefix + System.currentTimeMillis();

            // 4. Simpan ke Database
            PreparedStatement insertStmt = conn.prepareStatement(
                "INSERT INTO users (id, nama, email, password, no_telp, role) VALUES (?, ?, ?, ?, ?, ?)"
            );
            insertStmt.setString(1, newId);
            insertStmt.setString(2, nama);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password);
            insertStmt.setString(5, noTelp);
            insertStmt.setString(6, role);
            insertStmt.executeUpdate();

            // 5. Beri tahu UI bahwa pendaftaran berhasil
            ctx.status(201).json(Map.of("status", "success"));

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", "Terjadi kesalahan server."));
        }
    }
}