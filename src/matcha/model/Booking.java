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
    // Status constants – hindari magic string di seluruh codebase
    public static final String STATUS_PENDING   = "Pending";
    public static final String STATUS_CONFIRMED = "Confirmed";
    public static final String STATUS_CANCELLED = "Cancelled";
    public static final String STATUS_COMPLETED = "Completed";

    // Atribut
    private String bookingId;
    private Client client;
    private Talent talent;
    private Service bookedService;
    private Schedule bookingSchedule;
    private String status;

     // ─── Constructor ───────────────────────────────────────────────────────────
    public Booking(String bookingId, Client client, Talent talent,
                   Service bookedService, Schedule bookingSchedule) {
        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("Booking ID tidak boleh kosong.");
        }
        if (client == null || talent == null || bookedService == null || bookingSchedule == null) {
            throw new IllegalArgumentException("Client, Talent, Service, dan Schedule wajib diisi.");
        }
        this.bookingId       = bookingId;
        this.client          = client;
        this.talent          = talent;
        this.bookedService   = bookedService;
        this.bookingSchedule = bookingSchedule;
        this.status          = STATUS_PENDING;  // default awal
    }

    public void confirmBooking() {
        if (STATUS_CANCELLED.equals(this.status)) {
            throw new IllegalStateException(
                "Booking [" + bookingId + "] tidak dapat dikonfirmasi karena sudah dibatalkan.");
        }
        if (STATUS_CONFIRMED.equals(this.status)) {
            return; // idempotent – sudah confirmed, tidak perlu aksi
        }
        this.status = STATUS_CONFIRMED;
        this.bookingSchedule.bookSlot();  // kunci slot di jadwal terkait
    }

    public void cancelBooking() {
        if (STATUS_COMPLETED.equals(this.status)) {
            throw new IllegalStateException(
                "Booking [" + bookingId + "] tidak dapat dibatalkan karena sudah selesai.");
        }
        if (STATUS_CANCELLED.equals(this.status)) {
            return; // idempotent
        }
        this.status = STATUS_CANCELLED;
        this.bookingSchedule.releaseSlot();  // bebaskan slot jadwal
    }

    public void updateStatus(String newStatus) {
         if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Status baru tidak boleh kosong.");
        }
        boolean valid = false;
        switch (this.status) {
            case STATUS_PENDING:
                valid = STATUS_CONFIRMED.equals(newStatus) || STATUS_CANCELLED.equals(newStatus);
                break;
            case STATUS_CONFIRMED:
                valid = STATUS_COMPLETED.equals(newStatus) || STATUS_CANCELLED.equals(newStatus);
                break;
            default:
                valid = false; // Cancelled / Completed adalah terminal state
        }
        if (!valid) {
            throw new IllegalStateException(
                "Transisi status tidak valid: " + this.status + " → " + newStatus);
        }
        this.status = newStatus;
        // Sinkronkan slot jika status berubah ke Cancelled melalui updateStatus
        if (STATUS_CANCELLED.equals(newStatus)) {
            this.bookingSchedule.releaseSlot();
        }
    }

    // Getters
    public String   getBookingId()       { return bookingId; }
    public Client   getClient()          { return client; }
    public Talent   getTalent()          { return talent; }
    public Service  getBookedService()   { return bookedService; }
    public Schedule getBookingSchedule() { return bookingSchedule; }
    public String   getStatus()          { return status; }

    @Override
    public String toString() {
        return String.format("Booking[id=%s, client=%s, talent=%s, status=%s, schedule=%s]",
            bookingId,
            client.getName(),
            talent.getName(),
            status,
            bookingSchedule
        );
    }
}
