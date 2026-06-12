/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 * Modul 1: Manajemen Pengguna
 */
public abstract class User {

    protected String id;
    protected String nama;
    protected String email;
    protected String password;
    protected String noTelp;

    public User() {
    }

    public User(String id, String nama, String email,
                String password, String noTelp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.noTelp = noTelp;
    }

    public boolean login(String email, String pass) {
        return this.email.equals(email)
                && this.password.equals(pass);
    }

    public void logout() {
        System.out.println(nama + " berhasil logout.");
    }

    public abstract boolean verifyIdentity();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
