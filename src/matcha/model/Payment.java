/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 4: Kalkulasi Tarif dan Transaksi
 */
public class Payment implements IPayable {
    private String paymentId;
    private Invoice tagihan;
    private String paymentMethod;
    private String paymentStatus;
    private double walletBalance = 10000000.0; // Simulasi saldo awal e-wallet Rp 10 juta

    public Payment(String paymentId, Invoice tagihan, String paymentMethod) {
        this.paymentId = paymentId;
        this.tagihan = tagihan;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
    }

    @Override
    public boolean processPayment() {
        // Simulasi pemotongan saldo e-wallet pengguna
        if (tagihan == null) {
            paymentStatus = "Failed";
            System.out.println("Error: Tagihan tidak tersedia!");
            return false;
        }

        double totalAmount = tagihan.calculateTotal();

        // Cek saldo e-wallet
        if (walletBalance < totalAmount) {
            paymentStatus = "Failed";
            System.out.println("Error: Saldo e-wallet tidak cukup! Dibutuhkan Rp " + totalAmount + ", Saldo Anda: Rp " + walletBalance);
            return false;
        }

        // Proses pemotongan saldo
        walletBalance -= totalAmount;
        paymentStatus = "Success";
        System.out.println("Payment berhasil diproses!");
        System.out.println("Saldo e-wallet saat ini: Rp " + walletBalance);
        return true;
    }

    @Override
    public void generateReceipt() {
        // Print struk bukti pembayaran jika processPayment() sukses
        if ("Success".equals(paymentStatus)) {
            System.out.println("\n========= BUKTI PEMBAYARAN ==========");
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Metode Pembayaran: " + paymentMethod);
            System.out.println("Status: " + paymentStatus);
            System.out.println(tagihan.getInvoiceDetails());
            System.out.println("Tanggal Transaksi: " + java.time.LocalDateTime.now());
            System.out.println("====================================\n");
        } else {
            System.out.println("Struk tidak dapat dicetak karena pembayaran belum berhasil!");
        }
    }

    // Getter dan Setter
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Invoice getTagihan() {
        return tagihan;
    }

    public void setTagihan(Invoice tagihan) {
        this.tagihan = tagihan;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
}