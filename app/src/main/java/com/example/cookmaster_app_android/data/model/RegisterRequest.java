package com.example.cookmaster_app_android.data.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {


    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("adresse")
    private String adresse;

    @SerializedName("mot_de_passe")
    private  String mot_de_passe ;

    @SerializedName("email")
    private  String email ;

    @SerializedName("telephone")
    private  String telephone ;

    public RegisterRequest(String nom, String prenom , String adresse , String mot_de_passe, String email ,String telephone){
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse =  adresse;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.telephone = telephone;


    }

}
