/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

import java.util.ArrayList;
import java.util.List;

public class Talent extends User {

    private static int counter = 1;

    private Profile profile;
    private ArrayList<Schedule> scheduleList =
            new ArrayList<>();

    private boolean isAvailable;
    private ArrayList<DiscountRule> discountRules = 
            new ArrayList<>();

    public Talent(String nama,
                  String email,
                  String password,
                  String noTelp) {

        super(
                String.format(
                        "TL%03d",
                        counter++),
                nama,
                email,
                password,
                noTelp
        );
    }

@Override
public boolean verifyIdentity() {

    if (email == null || email.isEmpty()) {

        System.out.println(
                "Email tidak boleh kosong!");

        return false;
    }

    if (!email.endsWith("@gmail.com")) {

        System.out.println(
                "Email harus menggunakan @gmail.com");

        return false;
    }

    if (noTelp == null || noTelp.isEmpty()) {

        System.out.println(
                "Nomor telepon tidak boleh kosong!");

        return false;
    }

    return true;
}
    public void toggleAvailability() {

        isAvailable = !isAvailable;

        System.out.println(
                "Status tersedia : "
                + isAvailable);
    }

    public boolean isAvailable() {

        return isAvailable;
    }

    public void addSchedule(
            Schedule jadwal) {

        scheduleList.add(jadwal);
    }

     // Getter & setter Profile — dibutuhkan oleh CatalogController & PaymentController
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile p) {
        this.profile = p;
    }

    // Getter discountRules — dibutuhkan oleh PaymentController.calculateDiscount()
    public List<DiscountRule> getDiscountRules() {
        return discountRules;
    }

    // Tambah satu rule diskon ke talent (misal: aktivitas favorit "memasak" → 10%)
    public void addDiscountRule(DiscountRule rule) {
        if (rule != null) discountRules.add(rule);
    }
}
