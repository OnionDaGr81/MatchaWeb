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

public class Talent extends User {
    private Profile profile;
    private ArrayList<Schedule> scheduleList = new ArrayList<>();
    private boolean isAvailable;

    @Override
    public boolean verifyIdentity() {
        // TODO: Implementasi verifikasi KTP/portofolio khusus Talent
        return false;
    }

    public void toggleAvailability() {
        // TODO: Ubah status isAvailable (true ke false, atau sebaliknya)
    }

    public void addSchedule(Schedule jadwal) {
        // TODO: Tambahkan jadwal baru ke scheduleList
    }
}