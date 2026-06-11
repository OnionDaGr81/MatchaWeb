/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Hendra
 */
import matcha.model.User;
import matcha.model.Client;
import matcha.model.Talent;

public class AuthController {
    private User currentUserSession; // Menyimpan data user yang sedang login

    public boolean registerClient(String nama, String email, String password, String noTelp) {
        // TODO: Buat objek Client baru, enkripsi password, dan simpan ke file/database internal
        return false;
    }

    public boolean registerTalent(String nama, String email, String password, String noTelp) {
        // TODO: Buat objek Talent baru, enkripsi password, dan simpan
        return false;
    }

    public boolean login(String email, String password) {
        // TODO: Cari user berdasarkan email, cocokkan password. 
        // Jika berhasil, masukkan objek user ke currentUserSession
        return false;
    }

    public void logout() {
        // TODO: Kosongkan currentUserSession
        this.currentUserSession = null;
    }

    public User getCurrentUser() {
        return currentUserSession;
    }
}