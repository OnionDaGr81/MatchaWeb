/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 * Menangani proses pembayaran dan pencetakan struk bukti transaksi.
 * Mengimplementasikan IPayable untuk polimorfisme metode pembayaran.
 * Modul 4: Kalkulasi Tarif dan Transaksi
 */
public class Payment implements IPayable {

    private String paymentId;
    private Invoice tagihan;
    private String paymentMethod;
    private String paymentStatus;

    // Static agar saldo tidak reset setiap kali objek Payment baru dibuat
    private static double walletBalance = 10_000_000.0; // Simulasi saldo awal Rp 10 juta

    public Payment(String paymentId, Invoice tagihan, String paymentMethod) {
        this.paymentId = paymentId;
        this.tagihan = tagihan;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
    }

    @Override
    public boolean processPayment() {
        // Batalkan jika tagihan tidak tersedia
        if (tagihan == null) {
            paymentStatus = "Failed";
            System.out.println("[Payment] Error: Tagihan tidak tersedia!");
            return false;
        }

        double totalAmount = tagihan.calculateTotal();

        // Cek apakah saldo mencukupi sebelum memotong
        if (walletBalance < totalAmount) {
            paymentStatus = "Failed";
            System.out.printf("[Payment] Saldo tidak cukup! Dibutuhkan Rp %,.0f, Saldo: Rp %,.0f%n",
                totalAmount, walletBalance);
            return false;
        }

        // Potong saldo dan tandai pembayaran berhasil
        walletBalance -= totalAmount;
        paymentStatus = "Success";
        System.out.printf("[Payment] Pembayaran berhasil! Sisa saldo: Rp %,.0f%n", walletBalance);
        return true;
    }

    @Override
    public void generateReceipt() {
        // Cetak struk hanya jika status pembayaran sukses
        if ("Success".equals(paymentStatus)) {
            System.out.println("\n========= BUKTI PEMBAYARAN =========");
            System.out.println("Payment ID        : " + paymentId);
            System.out.println("Metode Pembayaran : " + paymentMethod);
            System.out.println("Status            : " + paymentStatus);
            System.out.println(tagihan.getInvoiceDetails());
            System.out.println("Tanggal Transaksi : " + java.time.LocalDateTime.now());
            System.out.println("====================================\n");
        } else {
            System.out.println("[Payment] Struk tidak dapat dicetak — pembayaran belum berhasil.");
        }
    }

    public String getPaymentId()                        { return paymentId; }
    public void setPaymentId(String p)                  { this.paymentId = p; }

    public Invoice getTagihan()                         { return tagihan; }
    public void setTagihan(Invoice t)                   { this.tagihan = t; }

    public String getPaymentMethod()                    { return paymentMethod; }
    public void setPaymentMethod(String m)              { this.paymentMethod = m; }

    public String getPaymentStatus()                    { return paymentStatus; }
    public void setPaymentStatus(String s)              { this.paymentStatus = s; }

    public static double getWalletBalance()             { return walletBalance; }
    public static void setWalletBalance(double b)       { walletBalance = b; }
}