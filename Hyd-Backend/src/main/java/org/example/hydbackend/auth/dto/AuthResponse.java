package org.example.hydbackend.auth.dto;

public class AuthResponse {

    private String token;

    public String getToken(String token){
        return token;
    }
    public void setToken(String token){
        this.token=token;
    }
}
