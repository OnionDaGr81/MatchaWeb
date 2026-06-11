/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

/**
 *
 * Modul 2 : Manajemen Profil dan Layanan
 */
import java.util.ArrayList;

public class Profile {
    private String bio;
    private ArrayList<Service> offeredServices = new ArrayList<>();
    private Rating reputasi; 

    public void addService(Service service) {
        // TODO: Tambahkan objek service ke dalam list offeredServices
    }

    public void updateBio(String newBio) {
        // TODO: Ubah atribut bio dengan nilai newBio
    }

    public void displayProfile() {
        // TODO: Print data bio, daftar layanan, dan rata-rata rating
    }
}
