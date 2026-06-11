/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Chaesar
 */
import matcha.model.Client;
import matcha.model.Talent;
import matcha.model.Service;
import matcha.model.Booking;
import matcha.model.Schedule;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingController {
    private ArrayList<Booking> masterBookingList = new ArrayList<>();

    public Booking createBooking(Client client, Talent talent, Service service, LocalDateTime start, LocalDateTime end) {
        // TODO: 
        // 1. Buat objek Schedule baru
        // 2. Cek apakah talent punya jadwal yang bentrok menggunakan checkAvailability()
        // 3. Jika aman, buat objek Booking, set status "Pending"
        // 4. Masukkan ke masterBookingList
        return null; // Ganti dengan return objek booking jika sukses
    }

    public boolean cancelBooking(String bookingId) {
        // TODO: Cari booking berdasarkan ID, batalkan, dan bebaskan jadwal talent
        return false;
    }
}