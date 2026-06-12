/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

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
