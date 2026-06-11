/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 1: Manajemen Pengguna
 */
public abstract class User {
    protected String id;
    protected String nama;
    protected String email;
    protected String password;
    protected String noTelp;

    public boolean login(String email, String pass) {
        // TODO: Buat logika validasi email dan password
        return false;
    }

    public void logout() {
        // TODO: Buat logika penghapusan sesi
    }

    // Method abstrak yang wajib di-override oleh kelas turunannya
    public abstract boolean verifyIdentity();

    public String getNama() {
        return this.nama;
    }
}
