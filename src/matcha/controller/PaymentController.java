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

public class PaymentController {
    
    public Invoice generateInvoice(Booking booking) {
        // TODO: 
        // 1. Ambil data layanan dari Booking
        // 2. Terapkan polimorfisme (tambahkan biaya transport jika talent offline)
        // 3. Buat dan kembalikan objek Invoice
        return null;
    }

    public boolean payInvoice(Invoice invoice, String paymentMethod) {
        // TODO: 
        // 1. Buat objek Payment yang mengimplementasikan IPayable
        // 2. Jalankan method processPayment() (simulasi potong e-wallet klien)
        // 3. Jika berhasil, jalankan generateReceipt()
        return false;
    }
}
