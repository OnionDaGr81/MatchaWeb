/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 6: Sistem Notifikasi   
 */
import java.time.LocalDateTime;

public class Notification implements INotifiable {
    private String notificationId;
    private User recipient; // Menggunakan polimorfisme, bisa mengarah ke Client atau Talent
    private String message;
    private LocalDateTime timestamp;

    @Override
    public void sendAlert(String message) {
        // TODO: Cetak pesan notifikasi ke layar/sistem untuk recipient terkait
    }

    public void triggerBookingStatusAlert(Booking booking) {
        // TODO: Buat trigger otomatis, misalnya jika booking dikonfirmasi, kirim sendAlert()
    }
}