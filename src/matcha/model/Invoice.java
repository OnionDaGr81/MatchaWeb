/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 * Menyimpan rincian tagihan dari sebuah booking, termasuk breakdown diskon.
 * Modul 4: Kalkulasi Tarif dan Transaksi
 */
public class Invoice {

    private String invoiceId;
    private Booking relatedBooking;
    private double baseRate;       // Harga dasar layanan sebelum tambahan apapun
    private double extraFee;       // Biaya tambahan, misal transport
    private double discountAmount; // Total potongan diskon (dari provider + talent)
    private double totalAmount;    // Harga final = baseRate + extraFee - discountAmount

    public Invoice(String invoiceId, Booking relatedBooking,
                   double baseRate, double extraFee, double discountAmount) {
        this.invoiceId = invoiceId;
        this.relatedBooking = relatedBooking;
        this.baseRate = baseRate;
        this.extraFee = extraFee;
        this.discountAmount = discountAmount;
        // Total dihitung otomatis saat invoice pertama kali dibuat
        this.totalAmount = baseRate + extraFee - discountAmount;
    }

    // Kembalikan total akhir yang harus dibayar client
    public double calculateTotal() {
        return totalAmount;
    }

    // Kembalikan rincian invoice lengkap untuk ditampilkan di struk
    public String getInvoiceDetails() {
        StringBuilder details = new StringBuilder();
        details.append("====== INVOICE DETAILS ======\n");
        details.append("Invoice ID : ").append(invoiceId).append("\n");

        if (relatedBooking != null) {
            details.append("Booking ID : ").append(relatedBooking.getBookingId()).append("\n");
            if (relatedBooking.getBookedService() != null)
                details.append("Service    : ")
                       .append(relatedBooking.getBookedService().getServiceDetails()).append("\n");
        }

        details.append("-----------------------------\n");
        details.append(String.format("Base Rate  : Rp %,.0f%n", baseRate));
        details.append(String.format("Extra Fee  : Rp %,.0f%n", extraFee));

        // Tampilkan baris diskon hanya jika ada potongan
        if (discountAmount > 0)
            details.append(String.format("Diskon     : -Rp %,.0f%n", discountAmount));

        details.append("-----------------------------\n");
        details.append(String.format("TOTAL      : Rp %,.0f%n", totalAmount));
        details.append("=============================\n");
        return details.toString();
    }

    // Method private untuk recalculate total setiap kali komponen harga diubah lewat setter
    private void recalcTotal() {
        this.totalAmount = this.baseRate + this.extraFee - this.discountAmount;
    }

    // Getters & Setters
    public String getInvoiceId()                         { return invoiceId; }
    public void setInvoiceId(String invoiceId)           { this.invoiceId = invoiceId; }

    public Booking getRelatedBooking()                   { return relatedBooking; }
    public void setRelatedBooking(Booking b)             { this.relatedBooking = b; }

    public double getBaseRate()                          { return baseRate; }
    public void setBaseRate(double v)                    { this.baseRate = v; recalcTotal(); }

    public double getExtraFee()                          { return extraFee; }
    public void setExtraFee(double v)                    { this.extraFee = v; recalcTotal(); }

    public double getDiscountAmount()                    { return discountAmount; }
    public void setDiscountAmount(double v)              { this.discountAmount = v; recalcTotal(); }

    public double getTotalAmount()                       { return totalAmount; }
    public void setTotalAmount(double v)                 { this.totalAmount = v; }
}