/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 1: Manajemen Pengguna
 */
import java.util.ArrayList;

public class Client extends User {
    private ArrayList<Booking> bookingHistory = new ArrayList<>();

    @Override
    public boolean verifyIdentity() {
        // TODO: Implementasi cara verifikasi identitas khusus Client
        return false;
    }

    public void requestBooking(Talent talent, Service service) {
        // TODO: Logika untuk membuat pesanan baru
    }

    public void viewHistory() {
        // TODO: Tampilkan isi dari bookingHistory
    }
}