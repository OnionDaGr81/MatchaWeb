/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 * Mengatur alur pembuatan invoice dan proses pembayaran booking.
 * Penanggung Jawab: Didit
 */

import matcha.model.Booking;
import matcha.model.DiscountRule;
import matcha.model.Invoice;
import matcha.model.Payment;
import matcha.model.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PaymentController {

    // Konstanta untuk biaya tambahan transport layanan offline
    private static final double OFFLINE_TRANSPORT_FEE = 50000.0; // Rp 50.000 untuk layanan offline

    public Invoice generateInvoice(Booking booking) {
        // Validasi input
        if (booking == null) {
            throw new IllegalArgumentException("[PaymentController] Booking tidak boleh null.");
        }

        Service bookedService = booking.getBookedService();
        if (bookedService == null) {
            throw new IllegalArgumentException("[PaymentController] Service tidak ditemukan dalam booking.");
        }

        // 1. Ambil data layanan dari Booking
        double baseRate = bookedService.getRate();

        // 2. Hitung biaya tambahan (transport jika talent offline)
        double extraFee = calculateExtraFee(booking);

        // 3. Hitung diskon dari dua sumber: Provider (event) dan Talent (aktivitas favorit)
        double discount = calculateDiscount(booking);

        // 4. Buat dan kembalikan objek Invoice
        // Invoice menghitung totalAmount sendiri dari komponen yang diberikan
        String invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Invoice invoice = new Invoice(invoiceId, booking, baseRate, extraFee, discount);

        System.out.println("[PaymentController] Invoice berhasil dibuat: " + invoiceId);
        System.out.printf("[PaymentController] Base Rate: Rp %,.0f | Extra Fee: Rp %,.0f | Diskon: Rp %,.0f | Total: Rp %,.0f%n",
            baseRate, extraFee, discount, invoice.calculateTotal());

        return invoice;
    }

    public boolean payInvoice(Invoice invoice, String paymentMethod) {
        // Validasi input
        if (invoice == null) {
            throw new IllegalArgumentException("[PaymentController] Invoice tidak boleh null.");
        }

        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("[PaymentController] Payment Method tidak boleh kosong.");
        }

        // Validasi method pembayaran yang tersedia
        boolean validPaymentMethod = isValidPaymentMethod(paymentMethod);
        if (!validPaymentMethod) {
            throw new IllegalArgumentException("[PaymentController] Metode pembayaran '" + paymentMethod + "' tidak valid.");
        }

        // 1. Buat objek Payment yang mengimplementasikan IPayable
        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Payment payment = new Payment(paymentId, invoice, paymentMethod);

        System.out.println("[PaymentController] Proses pembayaran dimulai dengan ID: " + paymentId);

        // 2. Jalankan method processPayment() (simulasi potong e-wallet klien)
        boolean paymentSuccess = payment.processPayment();

        // 3. Jika berhasil, jalankan generateReceipt()
        if (paymentSuccess) {
            payment.generateReceipt();
            return true;
        } else {
            System.out.println("[PaymentController] Pembayaran gagal diproses.");
            return false;
        }
    }

    // KALKULASI DISKON

    /**
     * Menghitung total diskon dari dua sumber:
     * 1. Diskon Provider — event/hari besar yang sedang berlangsung (Valentine, Imlek, Lebaran, dll)
     * 2. Diskon Talent  — aktivitas favorit talent yang cocok dengan layanan yang dipesan
     *
     * Jika keduanya berlaku, nilai persentase dijumlahkan.
     * Total diskon dibatasi maksimum 50% dari base rate.
     *
     * @param booking Data booking yang akan dihitung diskonnya
     * @return Nilai total diskon dalam Rupiah
     */
    private double calculateDiscount(Booking booking) {
        if (booking == null || booking.getBookedService() == null) return 0.0;

        double baseRate = booking.getBookedService().getRate();

        // Null-safe: pakai string kosong jika getServiceName() null
        String serviceName = booking.getBookedService().getServiceName() != null
            ? booking.getBookedService().getServiceName() : "";

        LocalDate today = LocalDate.now();
        double totalPct = 0.0;

        // Lapisan 1: Diskon dari Provider — cek event yang aktif hari ini
        List<DiscountRule> providerDiscounts =
            ProviderDiscountRegistry.getInstance().getActiveDiscounts(today);

        for (DiscountRule rule : providerDiscounts) {
            System.out.printf("[Diskon Provider] %s: %.0f%%%n",
                rule.getLabel(), rule.getPercentage() * 100);
            totalPct += rule.getPercentage();
        }

        // Lapisan 2: Diskon dari Talent — cek apakah layanan cocok dengan aktivitas favorit talent
        if (booking.getTalent() != null) {
            for (DiscountRule rule : booking.getTalent().getDiscountRules()) {
                if (rule.matchesActivity(serviceName)) {
                    System.out.printf("[Diskon Talent] \"%s\" cocok dengan layanan \"%s\": %.0f%%%n",
                        rule.getLabel(), serviceName, rule.getPercentage() * 100);
                    totalPct += rule.getPercentage();
                }
            }
        }

        // Batasi total diskon maksimum 50% dari base rate
        if (totalPct > 0.50) {
            System.out.println("[PaymentController] Total diskon melebihi 50%, dibatasi ke 50%.");
            totalPct = 0.50;
        }

        double finalDiscount = baseRate * totalPct;

        // Tampilkan ringkasan diskon jika ada
        if (finalDiscount > 0) {
            System.out.printf("[PaymentController] Total diskon: %.0f%% = Rp %,.0f%n",
                totalPct * 100, finalDiscount);
        }

        return finalDiscount;
    }

    /**
     * Menghitung biaya tambahan berdasarkan karakteristik booking.
     * Saat ini: semua talent yang memiliki profil dikenakan biaya transport offline.
     * Dapat dikembangkan berdasarkan jarak, lokasi, atau tipe layanan (online/offline).
     *
     * @param booking Data booking yang akan dihitung biayanya
     * @return Nilai biaya tambahan dalam Rupiah
     */
    private double calculateExtraFee(Booking booking) {
        double extraFee = 0.0;

        // Jika talent memiliki profil, tambahkan biaya transport standar
        if (booking.getTalent() != null && booking.getTalent().getProfile() != null) {
            extraFee = OFFLINE_TRANSPORT_FEE;
        }

        return extraFee;
    }

    /**
     * Validasi metode pembayaran yang diterima sistem.
     *
     * @param paymentMethod Nama metode pembayaran
     * @return true jika metode pembayaran valid
     */
    private boolean isValidPaymentMethod(String paymentMethod) {
        // Daftar metode pembayaran yang tersedia
        return paymentMethod.equalsIgnoreCase("e-wallet")      ||
               paymentMethod.equalsIgnoreCase("credit-card")   ||
               paymentMethod.equalsIgnoreCase("debit-card")    ||
               paymentMethod.equalsIgnoreCase("transfer-bank") ||
               paymentMethod.equalsIgnoreCase("cash");
    }
}
