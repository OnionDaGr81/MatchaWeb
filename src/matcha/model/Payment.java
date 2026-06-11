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

    @Override
    public boolean processPayment() {
        // TODO: Buat simulasi pemotongan saldo e-wallet pengguna
        return false;
    }

    @Override
    public void generateReceipt() {
        // TODO: Print struk bukti pembayaran jika processPayment() sukses
    }
}