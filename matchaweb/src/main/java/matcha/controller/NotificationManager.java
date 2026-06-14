/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab:Anam
 */
import matcha.model.Notification;
import matcha.model.User;
import matcha.model.Booking;
import java.time.LocalDateTime;

public class NotificationManager {

    public void notifyUser(User recipient, String message) {
        // 1. Buat objek Notification baru (implements INotifiable)
        // Catatan: Sesuaikan dengan konstruktor atau setter di kelas Notification Anda.
        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setMessage(message);
        
        // 2. Set timestamp waktu saat ini
        notification.setTimestamp(LocalDateTime.now());
        
        // 3. Panggil method sendAlert() untuk menampilkan pesan ke user
        notification.sendAlert(notification.getMessage());
    }

    public void onBookingStatusChanged(Booking booking) {
        // Method ini akan dipanggil oleh BookingController/PaymentController.
        String status = booking.getStatus();
        
        // Jika status "Paid", kirim notif ke Talent "Anda mendapat pesanan baru!".
        if ("Paid".equalsIgnoreCase(status)) {
            notifyUser(booking.getTalent(), "Anda mendapat pesanan baru!");
        }
        // Jika status "Confirmed", kirim notif ke Client "Pesanan Anda disetujui Talent!".
        else if ("Confirmed".equalsIgnoreCase(status)) {
            notifyUser(booking.getClient(), "Pesanan Anda disetujui Talent!");
        }
    }
}