/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matcha.controller;

import java.util.ArrayList;
import matcha.model.User;

public class AuthController {

    private ArrayList<User> daftarUser =
            new ArrayList<>();

    private User currentUser;

    public void register(
            User user) {

        if (isEmailRegistered(user.getEmail())) {

            System.out.println(
                    "Registrasi gagal: Email sudah terdaftar!");
            return;
        }

        if (user.verifyIdentity()) {

            daftarUser.add(user);

            System.out.println(
                    "Registrasi berhasil!");
        } else {

            System.out.println(
                    "Data tidak valid!");
        }
    }

    public boolean isEmailRegistered(
            String email) {

        for (User user : daftarUser) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public User login(
            String email,
            String password) {

        for (User user : daftarUser) {

            if (user.login(
                    email,
                    password)) {

                currentUser = user;

                System.out.println(
                        "Login berhasil!");

                return user;
            }
        }

        System.out.println(
                "Email atau password salah!");

        return null;
    }

    public void logout() {

        if (currentUser != null) {

            currentUser.logout();

            currentUser = null;
        }
    }

    public User getCurrentUser() {

        return currentUser;
    }
}
