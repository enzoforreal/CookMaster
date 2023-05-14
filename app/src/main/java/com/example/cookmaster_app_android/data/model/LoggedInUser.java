package com.example.cookmaster_app_android.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String id;
    private String displayName;

    public LoggedInUser(int id, String displayName) {
        this.id = String.valueOf(id);
        this.displayName = displayName;
    }

    public LoggedInUser(String nom, String prenom, String email) {
    }

    public String getUserId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
