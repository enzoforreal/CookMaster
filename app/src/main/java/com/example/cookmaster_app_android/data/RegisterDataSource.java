package com.example.cookmaster_app_android.data;

import com.example.cookmaster_app_android.data.api.ApiService;
import com.example.cookmaster_app_android.data.model.LoggedInUser;
import com.example.cookmaster_app_android.data.model.User;
import com.example.cookmaster_app_android.network.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDataSource {
    private ApiService apiService;

    public RegisterDataSource() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public Result<LoggedInUser> register(String nom, String prenom, String adresse, String mot_de_passe, String email, String telephone, RegisterCallback callback) {
        // Créez un objet User avec les valeurs saisies par l'utilisateur
        User user = new User(nom, prenom, adresse, email, mot_de_passe, telephone);

        // Appel de la méthode createUser de l'API pour créer un nouvel utilisateur
        Call<Void> call = apiService.createUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // L'utilisateur a été créé avec succès
                    LoggedInUser loggedInUser = new LoggedInUser(user.getNom(), user.getPrenom(), (String) user.getEmail());
                    callback.onSuccess(loggedInUser);
                } else {
                    // Erreur lors de la création de l'utilisateur
                    String errorBody = null;
                    try {
                        errorBody = response.errorBody().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    callback.onError(new Exception(errorBody));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Erreur lors de l'exécution de la requête
                callback.onError((Exception) t);
            }
        });
        return null;
    }


    public interface RegisterCallback {
        void onRegisterSuccess(LoggedInUser loggedInUser);

        void onRegisterError(Throwable t);

        void onSuccess(LoggedInUser loggedInUser);

        void onError(Exception e);
    }
}
