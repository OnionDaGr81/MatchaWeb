/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

/**
 *
 * @Penanggung Jawab: Bastian
 */
import matcha.model.Talent;
import matcha.model.Service;
import java.util.ArrayList;
import matcha.util.FileStorageUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CatalogController {
    private ArrayList<Talent> masterTalentList; // List semua talent yang ada di sistem

    public CatalogController() {
        // Load data talent dari file JSON menggunakan FileStorageUtil
        this.masterTalentList = FileStorageUtil.readFromFile(
            "data/talents.json", 
            new TypeToken<ArrayList<Talent>>(){}.getType()
        );
        
        // Jaga-jaga jika file JSON belum ada / kosong
        if (this.masterTalentList == null) {
            this.masterTalentList = new ArrayList<>();
        }
    }

    public ArrayList<Talent> getAllAvailableTalents() {
        ArrayList<Talent> availableTalents = new ArrayList<>();
        
        for (Talent t : masterTalentList) {
            // Asumsi di kelas Talent ada getter isAvailable()
            if (t.isAvailable()) { 
                availableTalents.add(t);
            }
        }
        return availableTalents;
    }

    public ArrayList<Talent> searchTalentByService(String serviceName) {
        ArrayList<Talent> filteredTalents = new ArrayList<>();
        
        for (Talent t : masterTalentList) {
            // Asumsi di kelas Talent ada getter getProfile()
            if (t.getProfile() != null) {
                // Looping layanan yang ada di dalam profil talent tersebut
                for (Service s : t.getProfile().getOfferedServices()) {
                    // Pakai equalsIgnoreCase agar pencarian tidak sensitif huruf besar/kecil
                    if (s.getServiceName().equalsIgnoreCase(serviceName)) {
                        filteredTalents.add(t);
                        break; // Jika ketemu layanannya, langsung lanjut ke talent berikutnya
                    }
                }
            }
        }
        return filteredTalents;
    }

    public void showTalentProfile(Talent talent) {
        if (talent != null && talent.getProfile() != null) {
            // Memanggil method displayProfile() dari objek Profile milik Talent
            talent.getProfile().displayProfile();
        } else {
            System.out.println("Profil tidak ditemukan atau talent belum mengatur profil.");
        }
    }
}
