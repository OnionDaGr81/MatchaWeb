/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Bastian
 */
import matcha.model.Talent;
import matcha.model.Service;
import matcha.util.DBUtil; // Pastikan import DBUtil-nya ada

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CatalogController {
    private ArrayList<Talent> masterTalentList;

    public CatalogController() {
        this.masterTalentList = getAllAvailableTalents();
        
        // Jaga-jaga jika database kosong agar tidak terjadi NullPointerException
        if (this.masterTalentList == null) {
            this.masterTalentList = new ArrayList<>();
        }
    }

    public ArrayList<Talent> getAllAvailableTalents() {
        ArrayList<Talent> availableTalents = new ArrayList<>();
        // Query untuk mengambil semua data user yang rolenya TALENT
        String query = "SELECT * FROM users WHERE role = 'TALENT'";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Talent t = new Talent(); 
                t.setId(rs.getString("id"));
                t.setNama(rs.getString("nama"));
                t.setEmail(rs.getString("email"));
                
                // Masukkan data dari MySQL ke dalam list
                availableTalents.add(t);
            }
        } catch (Exception e) {
            System.out.println("[CatalogController] Gagal mengambil data talent dari Database: " + e.getMessage());
        }
        return availableTalents;
    }

    public ArrayList<Talent> searchTalentByService(String serviceName) {
        ArrayList<Talent> filteredTalents = new ArrayList<>();
        
        // Logika pencariannya tetap sama seperti kodemu yang asli, 
        // melakukan iterasi ke variabel masterTalentList
        for (Talent t : masterTalentList) {
            if (t.getProfile() != null) {
                for (Service s : t.getProfile().getOfferedServices()) {
                    if (s.getServiceName().equalsIgnoreCase(serviceName)) {
                        filteredTalents.add(t);
                        break; 
                    }
                }
            }
        }
        return filteredTalents;
    }

    public void showTalentProfile(Talent talent) {
        if (talent != null && talent.getProfile() != null) {
            talent.getProfile().displayProfile();
        } else {
            System.out.println("Profil tidak ditemukan atau talent belum mengatur profil.");
        }
    }
}