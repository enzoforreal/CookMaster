package com.example.cookmaster_app_android.data.model;

import java.util.Date;

public class User {

        private int id;
        private String nom;
        private String prenom;
        private String adresse;
        private String email;
        private String telephone;
        private String mot_de_passe;
        private String photoDeProfil;
        private boolean est_admin;
        private Date created_at;
        private Date updated_at;

        public User(String nom, String prenom, String adresse, String email, String mot_de_passe, String telephone) {
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getPrenom() {
                return prenom;
        }

    public String getNom() {
            return nom;
    }

        public Object getEmail() {
                return email;
        }
}
