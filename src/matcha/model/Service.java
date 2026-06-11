/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 2 : Manajemen Profil dan Layanan
 */
public class Service {
    private String serviceId;
    private String serviceName;
    private double baseRate;
    private String deskripsi;

    public double getRate() {
        // TODO: Kembalikan nilai baseRate, bisa ditambahkan logika polimorfisme nanti
        return baseRate;
    }

    public String getServiceDetails() {
        // TODO: Kembalikan string berisi gabungan serviceName dan deskripsi
        return "";
    }
}
