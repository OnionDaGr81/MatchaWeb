/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

import java.util.ArrayList;
import matcha.model.User;

public class AuthController {

    private ArrayList<User> daftarUser;

    public AuthController() {
        daftarUser = new ArrayList<>();
    }

    public void register(User user) {

        if (user.verifyIdentity()) {
            daftarUser.add(user);
            System.out.println(
                    "Registrasi berhasil: "
                    + user.getNama());
        } else {
            System.out.println(
                    "Registrasi gagal. Data tidak valid.");
        }
    }

    public User login(String email, String password) {

        for (User user : daftarUser) {

            if (user.login(email, password)) {

                System.out.println(
                        "Login berhasil. Selamat datang "
                        + user.getNama());

                return user;
            }
        }

        System.out.println(
                "Email atau password salah!");

        return null;
    }

    public void logout(User user) {

        if (user != null) {
            user.logout();
        }
    }

    public ArrayList<User> getDaftarUser() {
        return daftarUser;
    }
}
