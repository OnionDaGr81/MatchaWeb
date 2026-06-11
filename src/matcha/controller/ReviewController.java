/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Alif
 */
import matcha.model.Booking;
import matcha.model.Review;
import matcha.model.Talent;

public class ReviewController {
    private String[] badWords = {"kata_kasar1", "kata_kasar2"}; // Contoh filter

    public boolean submitReviewAndRating(Booking completedBooking, String comment, int score) {
        // TODO:
        // 1. Validasi apakah status booking sudah "Selesai"
        // 2. Filter string comment, sensor jika ada badWords
        // 3. Masukkan review ke sistem
        // 4. Tambahkan score ke objek Rating milik profil Talent terkait
        // 5. Panggil calculateAverageRating() untuk mengupdate skor talent
        return false;
    }

    private String filterComment(String rawComment) {
        // TODO: Logika menyensor kata kasar menjadi "***"
        return rawComment;
    }
}
