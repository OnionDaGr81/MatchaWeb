/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 5: Ulasan dan Reputasi
 */
public class Review {
    private String reviewId;
    private Booking bookingRef;
    private String comment;

    public void submitReview(String komentar) {
        // TODO: Simpan komentar jika validasi reviewer berhasil
    }

    public boolean validateReviewer() {
        // TODO: Pastikan status bookingRef sudah 'Selesai' sebelum bisa mereview
        return false;
    }
}
