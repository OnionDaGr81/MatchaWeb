/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 3: Pemesanan dan Penjadwalan
 */
import java.time.LocalDateTime;

public class Schedule {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;

    public boolean checkAvailability(LocalDateTime requestStart, LocalDateTime requestEnd) {
        // TODO: Cek apakah jadwal bertabrakan atau isBooked bernilai true
        return false;
    }

    public void bookSlot() {
        // TODO: Ubah isBooked menjadi true
    }
}
