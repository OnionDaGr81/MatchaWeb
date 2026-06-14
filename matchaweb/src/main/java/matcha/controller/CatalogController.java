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
import matcha.model.Profile;
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
             
                // 1. Panggil method-nya dan simpan di variabel bernama 'p'
                Profile p = getProfileFromDatabase(t.getId()); 
                
                // 2. Set profile yang sudah didapat (p) ke dalam object Talent (t)
                t.setProfile(p);
                
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

    private matcha.model.Profile getProfileFromDatabase(String talentId) {
        matcha.model.Profile p = new matcha.model.Profile();
        // Asumsi mas punya method setOfferedServices atau inisiasi ArrayList di dalam Profile.java
        
        // 1. Query untuk mengambil bio profil (Sesuaikan nama tabel 'profiles' dengan DB mas)
        String queryProfile = "SELECT * FROM profiles WHERE talent_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queryProfile)) {
            
            stmt.setString(1, talentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p.updateBio(rs.getString("bio")); // Memakai method updateBio sesuai UML
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal mengambil profil: " + e.getMessage());
        }

        // 2. Query untuk mengambil daftar layanan / Service (Sesuaikan nama tabel 'services')
        String queryService = "SELECT * FROM services WHERE talent_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queryService)) {
            
            stmt.setString(1, talentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    matcha.model.Service s = new matcha.model.Service();
                    // Menggunakan nama atribut sesuai di laporan Tugas Besar
                    // Asumsi ada setter di model Service mas
                    s.setServiceId(rs.getString("id")); 
                    s.setServiceName(rs.getString("nama_layanan"));
                    s.setBaseRate(rs.getDouble("tarif_dasar"));

                    p.addService(s); // Memakai method addService sesuai UML
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal mengambil layanan: " + e.getMessage());
        }

        return p;
    }

    // Mengambil profil berdasarkan ID talent
    public Talent getTalentById(String talentId) {
        for (Talent t : masterTalentList) {
            if (t.getId() != null && t.getId().equals(talentId)) {
                return t; // Mengembalikan object Talent (lengkap dengan Profilenya)
            }
        }
        return null; // Jika tidak ketemu, biarkan return null
    }
}