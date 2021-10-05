package com.campizza.backend.campizzabackend.security.payloads;

public class PasswordChangeResponse {
    private String email;

    public PasswordChangeResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
