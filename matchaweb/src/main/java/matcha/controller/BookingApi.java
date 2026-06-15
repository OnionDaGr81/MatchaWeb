package matcha.controller;

import io.javalin.http.Context;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookingApi {

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/matcha_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    // POST /bookings — Client buat booking baru
    public static void createBooking(Context ctx) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(ctx.body());

            String clientId     = body.get("clientId").asText();
            String talentId     = body.get("talentId").asText();
            String serviceId    = body.get("serviceId").asText();
            String waktuMulai   = body.get("waktuMulai").asText();
            String waktuSelesai = body.get("waktuSelesai").asText();
            String catatan      = body.has("catatan") ? body.get("catatan").asText() : "";

            String newId = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO bookings (id, client_id, talent_id, service_id, waktu_mulai, waktu_selesai, status, catatan) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'Pending', ?)"
            );
            stmt.setString(1, newId);
            stmt.setString(2, clientId);
            stmt.setString(3, talentId);
            stmt.setString(4, serviceId);
            stmt.setString(5, waktuMulai);
            stmt.setString(6, waktuSelesai);
            stmt.setString(7, catatan);
            stmt.executeUpdate();

            stmt.close(); conn.close();
            ctx.status(201).json(Map.of("status", "success", "bookingId", newId));

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    // GET /bookings/client/{clientId} — Client lihat booking miliknya
    public static void getBookingsByClient(Context ctx) {
        try {
            String clientId = ctx.pathParam("clientId");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT b.id, u.nama AS talentNama, s.nama_layanan AS layanan, " +
                "b.waktu_mulai, b.waktu_selesai, b.status, b.catatan, s.tarif_dasar " +
                "FROM bookings b " +
                "JOIN users u ON b.talent_id = u.id " +
                "JOIN services s ON b.service_id = s.id " +
                "WHERE b.client_id = ? ORDER BY b.waktu_mulai DESC"
            );
            stmt.setString(1, clientId);
            ResultSet rs = stmt.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                long mulai   = rs.getTimestamp("waktu_mulai").getTime();
                long selesai = rs.getTimestamp("waktu_selesai").getTime();
                long durasi  = (selesai - mulai) / 3600000;
                double tarif = rs.getDouble("tarif_dasar");
                double total = durasi * tarif;

                list.add(Map.of(
                    "id",           rs.getString("id"),
                    "talentNama",   rs.getString("talentNama"),
                    "layanan",      rs.getString("layanan"),
                    "waktuMulai",   rs.getString("waktu_mulai"),
                    "waktuSelesai", rs.getString("waktu_selesai"),
                    "status",       rs.getString("status"),
                    "catatan",      rs.getString("catatan") != null ? rs.getString("catatan") : "",
                    "total",        total
                ));
            }
            rs.close(); stmt.close(); conn.close();
            ctx.json(list);

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    // GET /bookings/talent/{talentId} — Talent lihat booking masuk
    public static void getBookingsByTalent(Context ctx) {
        try {
            String talentId = ctx.pathParam("talentId");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT b.id, u.nama AS clientNama, s.nama_layanan AS layanan, " +
                "b.waktu_mulai, b.waktu_selesai, b.status, b.catatan, s.tarif_dasar " +
                "FROM bookings b " +
                "JOIN users u ON b.client_id = u.id " +
                "JOIN services s ON b.service_id = s.id " +
                "WHERE b.talent_id = ? ORDER BY b.waktu_mulai DESC"
            );
            stmt.setString(1, talentId);
            ResultSet rs = stmt.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                long mulai   = rs.getTimestamp("waktu_mulai").getTime();
                long selesai = rs.getTimestamp("waktu_selesai").getTime();
                long durasi  = (selesai - mulai) / 3600000;
                double tarif = rs.getDouble("tarif_dasar");
                double total = durasi * tarif;

                list.add(Map.of(
                    "id",           rs.getString("id"),
                    "clientNama",   rs.getString("clientNama"),
                    "layanan",      rs.getString("layanan"),
                    "waktuMulai",   rs.getString("waktu_mulai"),
                    "waktuSelesai", rs.getString("waktu_selesai"),
                    "status",       rs.getString("status"),
                    "catatan",      rs.getString("catatan") != null ? rs.getString("catatan") : "",
                    "total",        total
                ));
            }
            rs.close(); stmt.close(); conn.close();
            ctx.json(list);

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }

    // PATCH /bookings/{id}/status — Talent konfirmasi atau tolak
    public static void updateStatus(Context ctx) {
        try {
            String bookingId = ctx.pathParam("id");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = mapper.readTree(ctx.body());
            String newStatus = body.get("status").asText();

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE bookings SET status = ? WHERE id = ?"
            );
            stmt.setString(1, newStatus);
            stmt.setString(2, bookingId);
            int rows = stmt.executeUpdate();

            stmt.close(); conn.close();

            if (rows == 0) {
                ctx.status(404).json(Map.of("error", "Booking tidak ditemukan."));
            } else {
                ctx.json(Map.of("status", "success"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", e.getMessage()));
        }
    }
}