/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Bagian diskon untuk Talent, yang bisa berupa diskon berdasarkan aktivitas favorit mereka
public class Talent {
    private String talentId;
    private String name;
    private Profile profile;

    // Daftar aturan diskon personal milik talent berdasarkan aktivitas favoritnya
    private List<DiscountRule> discountRules = new ArrayList<>();

    public Talent(String talentId, String name, Profile profile) {
        this.talentId = talentId;
        this.name = name;
        this.profile = profile;
    }

    // Talent menambahkan aturan diskon untuk aktivitas yang dia sukai
    public void addDiscountRule(DiscountRule rule) {
        if (rule != null) {
            this.discountRules.add(rule);
        }
    }

    // Kembalikan salinan list agar tidak bisa dimodifikasi dari luar (enkapsulasi)
    public List<DiscountRule> getDiscountRules() {
        return Collections.unmodifiableList(discountRules);
    }

    public String getTalentId()             { return talentId; }
    public void setTalentId(String id)      { this.talentId = id; }
    public String getName()                 { return name; }
    public void setName(String name)        { this.name = name; }
    public Profile getProfile()             { return profile; }
    public void setProfile(Profile p)       { this.profile = p; }
}

public class Talent extends User {

    private String kategori;
    private double tarifPerJam;
    private boolean tersedia;

    public Talent() {
    }

    public Talent(String id, String nama, String email,
                  String password, String noTelp,
                  String kategori, double tarifPerJam,
                  boolean tersedia) {

        super(id, nama, email, password, noTelp);
        this.kategori = kategori;
        this.tarifPerJam = tarifPerJam;
        this.tersedia = tersedia;
    }

    @Override
    public boolean verifyIdentity() {
        return email != null
                && !email.isEmpty()
                && noTelp != null
                && !noTelp.isEmpty();
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getTarifPerJam() {
        return tarifPerJam;
    }

    public void setTarifPerJam(double tarifPerJam) {
        this.tarifPerJam = tarifPerJam;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
