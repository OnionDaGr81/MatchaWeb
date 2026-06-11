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

    public Invoice(String invoiceId, Booking relatedBooking, double totalAmount, double extraFee) {
        this.invoiceId = invoiceId;
        this.relatedBooking = relatedBooking;
        this.totalAmount = totalAmount;
        this.extraFee = extraFee;
    }

    public double calculateTotal() {
        // Hitung total biaya (baseRate dari Service + extraFee)
        if (relatedBooking != null && relatedBooking.getBookedService() != null) {
            double baseRate = relatedBooking.getBookedService().getRate();
            totalAmount = baseRate + extraFee;
        }
        return totalAmount;
    }

    public String getInvoiceDetails() {
        // Kembalikan rincian tagihan dalam bentuk teks
        StringBuilder details = new StringBuilder();
        details.append("====== INVOICE DETAILS ======\n");
        details.append("Invoice ID: ").append(invoiceId).append("\n");
        if (relatedBooking != null) {
            details.append("Booking ID: ").append(relatedBooking.getBookingId()).append("\n");
            if (relatedBooking.getBookedService() != null) {
                details.append("Service: ").append(relatedBooking.getBookedService().getServiceDetails()).append("\n");
                details.append("Base Rate: Rp ").append(relatedBooking.getBookedService().getRate()).append("\n");
            }
        }
        details.append("Extra Fee: Rp ").append(extraFee).append("\n");
        details.append("Total Amount: Rp ").append(calculateTotal()).append("\n");
        details.append("============================\n");
        return details.toString();
    }

    // Getter dan Setter
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Booking getRelatedBooking() {
        return relatedBooking;
    }

    public void setRelatedBooking(Booking relatedBooking) {
        this.relatedBooking = relatedBooking;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(double extraFee) {
        this.extraFee = extraFee;
    }
}