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

public class NotificationManager {

    public void notifyUser(User recipient, String message) {
        // TODO: 
        // 1. Buat objek Notification baru (implements INotifiable)
        // 2. Set timestamp waktu saat ini
        // 3. Panggil method sendAlert() untuk menampilkan pesan ke user
    }

    public void onBookingStatusChanged(Booking booking) {
        // TODO:
        // Method ini akan dipanggil oleh BookingController/PaymentController.
        // Jika status "Paid", kirim notif ke Talent "Anda mendapat pesanan baru!".
        // Jika status "Confirmed", kirim notif ke Client "Pesanan Anda disetujui Talent!".
    }
}
