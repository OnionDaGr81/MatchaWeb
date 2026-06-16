package com.matcha.controller;

import com.matcha.service.UserService;
import io.javalin.http.Context;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // Fungsi ini akan dipanggil oleh Javalin
    public void fetchAllUsers(Context ctx) {
        ctx.json(userService.getAllUsers()); // Otomatis jadi JSON berkat Jackson
    }

    public void getUserById(Context ctx) {
        String userId = ctx.pathParam("userId");
        ctx.json(userService.getUserById(userId));
    }
}