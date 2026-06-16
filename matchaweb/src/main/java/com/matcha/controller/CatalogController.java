package com.matcha.controller;

import com.matcha.service.CatalogService; 
import io.javalin.http.Context;
import main.java.com.matcha.model.ServiceItem;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController() {
        this.catalogService = new CatalogService();
    }

    // --- ENDPOINT 1: Tampilkan Semua Talent ---
    // Akan merespons request GET ke /api/talents
    public void getAllTalents(Context ctx) {
        try {
            // (Opsional) Mengambil query parameter jika user melakukan pencarian
            // Contoh URL: /api/talents?search=gaming
            String keyword = ctx.queryParam("search");

            // Memanggil service untuk mengambil daftar talent
            List<Map<String, Object>> talents = catalogService.getTalentCatalog(keyword);

            ctx.status(200).json(Map.of(
                "status", "success",
                "message", "Berhasil mengambil daftar talent",
                "data", mockTalents
            ));

        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                "status", "error",
                "message", "Terjadi kesalahan pada server saat mengambil data talent."
            ));
        }
    }

    // --- ENDPOINT 2: Tampilkan Layanan Milik Satu Talent ---
    // Akan merespons request GET ke /api/talents/{talentId}/services
    public void getTalentServices(Context ctx) {
        try {
            // Menangkap parameter {talentId} yang ada di URL path
            String talentId = ctx.pathParam("talentId");

            List<ServiceItem> services = catalogService.getTalentServices(talentId);
    
            ctx.status(200).json(Map.of(
                "status", "success",
                "message", "Berhasil mengambil katalog layanan",
                "data", mockServices
            ));

        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                "status", "error",
                "message", "Gagal mengambil detail layanan talent."
            ));
        }
    }
}