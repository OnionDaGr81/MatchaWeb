/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

import java.util.ArrayList;

public class Talent extends User {

    private static int counter = 1;

    private Profile profile;
    private ArrayList<Schedule> scheduleList =
            new ArrayList<>();

    private boolean isAvailable;

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
}
