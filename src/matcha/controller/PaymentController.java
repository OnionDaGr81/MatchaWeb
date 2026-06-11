/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Didit
 */
import matcha.model.Booking;
import matcha.model.Invoice;
import matcha.model.Payment;
import matcha.model.Service;

import java.util.UUID;

public class PaymentController {
    // Konstanta untuk biaya tambahan
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
        
        // 2. Terapkan polimorfisme (tambahkan biaya transport jika talent offline)
        // Catatan: Menentukan layanan offline dengan pengecekan apakah service memerlukan on-site
        // Untuk sekarang, kita asumsikan semua layanan yang bukan virtual/online memerlukan transport
        double extraFee = calculateExtraFee(booking);
        
        // 3. Buat dan kembalikan objek Invoice
        String invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Invoice invoice = new Invoice(invoiceId, booking, baseRate, extraFee);
        
        System.out.println("[PaymentController] Invoice berhasil dibuat: " + invoiceId);
        System.out.println("[PaymentController] Base Rate: Rp " + baseRate + ", Extra Fee: Rp " + extraFee);
        
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
    
    /**
     * Method helper untuk menghitung biaya tambahan berdasarkan karakteristik booking
     * @param booking Data booking yang akan dihitung biayanya
     * @return Nilai biaya tambahan (transport, etc)
     */
    private double calculateExtraFee(Booking booking) {
        double extraFee = 0.0;
        
        // Logika menentukan apakah talent bekerja offline (on-site)
        // Saat ini, asumsi: semua layanan dari talent memerlukan transport fee
        // Bisa dikembangkan dengan menambah atribut service type (online/offline)
        if (booking.getTalent() != null && booking.getTalent().getProfile() != null) {
            // Jika talent memiliki profil, tambahkan biaya transport standar
            // Ini dapat dikustomisasi lebih lanjut berdasarkan jarak, lokasi, dll
            extraFee = OFFLINE_TRANSPORT_FEE;
        }
        
        return extraFee;
    }
    
    /**
     * Method helper untuk validasi metode pembayaran yang tersedia
     * @param paymentMethod Nama metode pembayaran
     * @return true jika metode pembayaran valid
     */
    private boolean isValidPaymentMethod(String paymentMethod) {
        // List metode pembayaran yang tersedia
        return paymentMethod.equalsIgnoreCase("e-wallet") ||
               paymentMethod.equalsIgnoreCase("credit-card") ||
               paymentMethod.equalsIgnoreCase("debit-card") ||
               paymentMethod.equalsIgnoreCase("transfer-bank") ||
               paymentMethod.equalsIgnoreCase("cash");
    }
}
