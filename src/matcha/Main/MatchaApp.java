package matcha.Main;

import matcha.controller.CatalogController;
import matcha.model.Talent;
import matcha.util.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;

public class MatchaApp {
    public static void main(String[] args) {
        System.out.println("TESTING MATCHA WEB ");

        // TEST 1: Cek Koneksi Database
        System.out.println("\n[1] Mencoba koneksi ke database MySQL...");
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null) {
                System.out.println("KONEKSI DATABASE BERHASIL!");
            }
        } catch (Exception e) {
            System.out.println("KONEKSI GAGAL: " + e.getMessage());
            System.out.println("Cek lagi DBUtil.java, pastikan URL, User, dan Password sudah benar.");
            return; // Hentikan program kalau database belum nyambung
        }

        // TEST 2: Cek Tarik Data (CatalogController)
        System.out.println("\n[2] Menarik data Talent dari tabel users...");
        CatalogController catalog = new CatalogController();
        ArrayList<Talent> talents = catalog.getAllAvailableTalents();

        if (talents.isEmpty()) {
            System.out.println("Data talent kosong. Pastikan data dummy sudah di-insert ke phpMyAdmin.");
        } else {
            System.out.println("Berhasil menemukan " + talents.size() + " talent:");
            for (Talent t : talents) {
                System.out.println("   -> ID: " + t.getId() + " | Nama: " + t.getNama() + " | Email: " + t.getEmail());
            }
        }

        System.out.println("\n=== 🎉 TESTING SELESAI 🎉 ===");
    }
}