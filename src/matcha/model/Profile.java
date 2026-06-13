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
    private String location;

    public Profile(String bio, String location) {
        this.bio = bio;
        this.location = location;
    }

    public String getBio()               { return bio; }
    public void setBio(String bio)       { this.bio = bio; }
    public String getLocation()          { return location; }
    public void setLocation(String loc)  { this.location = loc; }


   public void addService(Service service) {
        // Menambahkan objek service ke dalam list
        this.offeredServices.add(service);
    }

    public void updateBio(String newBio) {
        // Mengubah atribut bio
        this.bio = newBio;
    }

    public void displayProfile() {
        System.out.println("\n=== PROFIL TALENT ===");
        System.out.println("Bio    : " + (this.bio != null ? this.bio : "Belum ada bio."));
        System.out.println("Lokasi : " + (this.location != null ? this.location : "Belum diatur."));
        
        System.out.println("Layanan yang ditawarkan:");
        if (this.offeredServices.isEmpty()) {
            System.out.println("  - Belum ada layanan.");
        } else {
            for (Service s : this.offeredServices) {
                // Memanggil getServiceDetails() dari kelas Service
                System.out.println("  - " + s.getServiceDetails() + " (Rp" + s.getBaseRate() + ")");
            }
        }

        // Opsional: Cek jika objek rating sudah diinisialisasi
        if (this.reputasi != null) {
            System.out.println("Rating : " + this.reputasi.calculateAverageRating() + " / 5.0");
        }
    }

    // Tambahkan Getter ini agar CatalogController nanti bisa mengecek daftar layanan
    public ArrayList<Service> getOfferedServices() {
        return this.offeredServices;
    }
    
    public Rating getRating() {
    if (this.reputasi == null) {
        this.reputasi = new Rating();
    }
    return this.reputasi;
}

}
