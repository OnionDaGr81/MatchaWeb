/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 4: Kalkulasi Tarif dan Transaksi
 */
public class Invoice {
    private String invoiceId;
    private Booking relatedBooking;
    private double totalAmount;
    private double extraFee;

    public double calculateTotal() {
        // TODO: Hitung total biaya (baseRate dari Service + extraFee)
        return 0.0;
    }

    public String getInvoiceDetails() {
        // TODO: Kembalikan rincian tagihan dalam bentuk teks
        return "";
    }
}