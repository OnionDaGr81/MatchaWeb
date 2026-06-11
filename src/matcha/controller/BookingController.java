/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Chaesar
 */
import matcha.model.Booking;
import matcha.model.Client;
import matcha.model.Schedule;
import matcha.model.Service;
import matcha.model.Talent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class BookingController {
    private ArrayList<Booking> masterBookingList = new ArrayList<>();

    public Booking createBooking(Client client, Talent talent, Service service, LocalDateTime start, LocalDateTime end) {
      // Validasi input dasar 
        if (client == null || talent == null || service == null) {
            throw new IllegalArgumentException("[BookingController] Client, Talent, dan Service tidak boleh null.");
        }
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("[BookingController] Rentang waktu tidak valid.");
        }

        // Validasi double-booking: iterasi semua booking aktif milik talent ini
        for (Booking existing : masterBookingList) {
            boolean sameTalent  = existing.getTalent().equals(talent);
            boolean activeStatus = Booking.STATUS_PENDING.equals(existing.getStatus())
                                || Booking.STATUS_CONFIRMED.equals(existing.getStatus());

            if (sameTalent && activeStatus) {
                Schedule existingSchedule = existing.getBookingSchedule();
                // isOverlapping()  true jika dua interval saling tumpang-tindih
                if (existingSchedule.isOverlapping(start, end)) {
                    throw new BookingConflictException(
                        "[BookingController] Talent '" + talent.getName() +
                        "' sudah dipesan pada rentang waktu yang dipilih. " +
                        "Booking yang konflik: " + existing.getBookingId()
                    );
                }
            }
        }

    
        // Buat objek Schedule dan Booking
        Schedule newSchedule = new Schedule(start, end);
        String   newId       = "BKG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Booking  newBooking  = new Booking(newId, client, talent, service, newSchedule);
        // status default adalah "Pending" (diset di constructor Booking)

        masterBookingList.add(newBooking);
        System.out.println("[BookingController] Booking berhasil dibuat: " + newBooking);
        return newBooking;
    }

    // READ
     public Booking findBookingById(String bookingId) {
        for (Booking b : masterBookingList) {
            if (b.getBookingId().equals(bookingId)) {
                return b;
            }
        }
        return null;
    }
    
     // Mengambil semua booking milik seorang Client.
    public ArrayList<Booking> getBookingsByClient(Client client) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking b : masterBookingList) {
            if (b.getClient().equals(client)) {
                result.add(b);
            }
        }
        return result;
    }
     // Mengambil semua booking yang diterima seorang Talent.
    public ArrayList<Booking> getBookingsByTalent(Talent talent) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking b : masterBookingList) {
            if (b.getTalent().equals(talent)) {
                result.add(b);
            }
        }
        return result;
    }
     //Mengambil salinan seluruh daftar booking (read-only view).
    public ArrayList<Booking> getAllBookings() {
        return new ArrayList<>(masterBookingList);
    }

    // UPDATE
    
     //Mengkonfirmasi sebuah booking berdasarkan ID.
     //Mengunci jadwal talent secara otomatis via confirmBooking().
     public boolean confirmBooking(String bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            System.err.println("[BookingController] ERROR: Booking '" + bookingId + "' tidak ditemukan.");
            return false;
        }
        booking.confirmBooking();
        System.out.println("[BookingController] Booking '" + bookingId + "' berhasil dikonfirmasi.");
        return true;
    }
 
     //Memperbarui status booking secara dinamis.
     //Memanfaatkan validasi transisi di Booking.updateStatus().
    public boolean updateBookingStatus(String bookingId, String newStatus) {
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            System.err.println("[BookingController] ERROR: Booking '" + bookingId + "' tidak ditemukan.");
            return false;
        }
        booking.updateStatus(newStatus);
        System.out.println("[BookingController] Status booking '" + bookingId +
                           "' diperbarui menjadi '" + newStatus + "'.");
        return true;
    }

     // DELETE / CANCEL
    public boolean cancelBooking(String bookingId) {
          Booking booking = findBookingById(bookingId);
        if (booking == null) {
            System.err.println("[BookingController] ERROR: Booking '" + bookingId + "' tidak ditemukan.");
            return false;
        }
        booking.cancelBooking();  // memanggil bookingSchedule.releaseSlot() di dalamnya
        System.out.println("[BookingController] Booking '" + bookingId + "' berhasil dibatalkan.");
        return true;
    }

     // Runtime exception yang dilempar saat terjadi double-booking pada talent.
    public static class BookingConflictException extends RuntimeException {
        public BookingConflictException(String message) {
            super(message);
        }
    }
}