package com.matcha.repository;

import com.matcha.model.ServiceItem;
import com.matcha.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogRepository {

    // 1. Mengambil daftar Talent (menggabungkan tabel users dan profiles)
    public List<Map<String, Object>> getAllTalentsWithProfile(String keyword) {
        List<Map<String, Object>> talents = new ArrayList<>();
        
        // Gunakan LEFT JOIN agar talent yang belum punya profil tetap terbaca
        StringBuilder sql = new StringBuilder(
            "SELECT u.id, u.nama, p.bio " +
            "FROM users u " +
            "LEFT JOIN profiles p ON u.id = p.talent_id " +
            "WHERE u.role = 'talent'"
        );

        // Jika ada pencarian keyword (Filter)
        boolean hasKeyword = (keyword != null && !keyword.trim().isEmpty());
        if (hasKeyword) {
            sql.append(" AND (u.nama LIKE ? OR p.bio LIKE ?)");
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            if (hasKeyword) {
                String searchPattern = "%" + keyword + "%";
                stmt.setString(1, searchPattern);
                stmt.setString(2, searchPattern);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Menggunakan Map (Data Transfer Object dinamis) untuk dikirim jadi JSON
                Map<String, Object> talent = new HashMap<>();
                talent.put("id", rs.getString("id"));
                talent.put("nama", rs.getString("nama"));
                talent.put("bio", rs.getString("bio") != null ? rs.getString("bio") : "Belum ada bio.");
                talents.add(talent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return talents;
    }

    // 2. Mengambil Layanan (Services) berdasarkan ID Talent
    public List<ServiceItem> getServicesByTalentId(String talentId) {
        List<ServiceItem> services = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE talent_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, talentId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ServiceItem item = new ServiceItem();
                item.setId(rs.getString("id"));
                item.setTalentId(rs.getString("talent_id"));
                item.setNamaLayanan(rs.getString("nama_layanan"));
                item.setTarifDasar(rs.getDouble("tarif_dasar"));
                item.setDeskripsi(rs.getString("deskripsi"));
                services.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}