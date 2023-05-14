package com.example.cookmaster_app_android.data.model;

public class LoginRequest {
    private  String  email;
    private  String mot_de_passe;


    public LoginRequest (String email , String mot_de_passe){
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return mot_de_passe;
    }
}
