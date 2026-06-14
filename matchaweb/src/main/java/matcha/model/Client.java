/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.model;

import java.util.ArrayList;

public class Client extends User {

    private static int counter = 1;

    private ArrayList<Booking> bookingHistory =
            new ArrayList<>();

    public Client(String nama,
                  String email,
                  String password,
                  String noTelp) {

        super(
                String.format(
                        "CL%03d",
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
    public void requestBooking(
            Talent talent,
            Service service) {

        System.out.println(
                "Booking berhasil dibuat.");
    }

    public void viewHistory() {

        System.out.println(
                "Jumlah booking : "
                + bookingHistory.size());
    }
}
