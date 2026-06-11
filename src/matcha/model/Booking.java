/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 3: Pemesanan dan Penjadwalan
 */
public class Booking {
    private String bookingId;
    private Client client;
    private Talent talent;
    private Service bookedService;
    private Schedule bookingSchedule;
    private String status;

    public void confirmBooking() {
        // TODO: Ubah status menjadi 'Confirmed' dan panggil bookSlot() di jadwal terkait
    }

    public void cancelBooking() {
        // TODO: Ubah status menjadi 'Cancelled' dan bebaskan jadwal
    }

    public void updateStatus(String newStatus) {
        // TODO: Update status pemesanan secara dinamis
    }
}