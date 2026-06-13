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
import matcha.model.Profile;
import matcha.model.Review;
import matcha.model.Talent;

public class ReviewController {
    private String[] badWords = {"kata_kasar1", "kata_kasar2"}; // Contoh filter

    public boolean submitReviewAndRating(Booking completedBooking, String comment, int score) {
        // 1. Validasi apakah status booking sudah "Completed"
        if (completedBooking == null
                || !Booking.STATUS_COMPLETED.equals(completedBooking.getStatus())) {
            return false;
        }

        // 2. Filter string comment, sensor jika ada badWords
        String filteredComment = filterComment(comment);

        // 3. Masukkan review ke sistem
        Review review = new Review();
        review.setBookingRef(completedBooking);
        review.submitReview(filteredComment);

        if (!review.validateReviewer()) {
            return false;
        }

        // 4. Tambahkan score ke objek Rating milik profil Talent terkait
        Talent talent = completedBooking.getTalent();
        if (talent == null) {
            return false;
        }
        Profile profile = talent.getProfile();
        if (profile == null) {
            return false;
        }
        profile.getRating().addScore(score);

        // 5. Panggil calculateAverageRating() untuk mengupdate skor talent
        profile.getRating().calculateAverageRating();

        return true;
    }

    private String filterComment(String rawComment) {
        if (rawComment == null) {
            return "";
        }
        String result = rawComment;
        for (String bad : badWords) {
            if (bad != null && !bad.isEmpty()) {
                String censor = "*".repeat(bad.length());
                result = result.replaceAll("(?i)" + java.util.regex.Pattern.quote(bad), censor);
            }
        }
        return result;
    }
}