package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.PasswordToken;

public class ResetRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
