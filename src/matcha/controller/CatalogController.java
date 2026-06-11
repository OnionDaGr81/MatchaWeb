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

public class CatalogController {
    private ArrayList<Talent> masterTalentList; // List semua talent yang ada di sistem

    public CatalogController() {
        // TODO: Load data talent dari media penyimpanan (file txt/json) ke masterTalentList
    }

    public ArrayList<Talent> getAllAvailableTalents() {
        // TODO: Lakukan iterasi pada masterTalentList, kembalikan hanya talent yang isAvailable = true
        return new ArrayList<>();
    }

    public ArrayList<Talent> searchTalentByService(String serviceName) {
        // TODO: Filter talent yang menawarkan layanan tertentu (misal: "Teman Nonton")
        return new ArrayList<>();
    }

    public void showTalentProfile(Talent talent) {
        // TODO: Ambil objek Profile dari Talent dan panggil displayProfile()
    }
}
