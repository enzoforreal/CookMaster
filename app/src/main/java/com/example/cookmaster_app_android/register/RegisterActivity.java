package com.example.cookmaster_app_android.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cookmaster_app_android.R;
import com.example.cookmaster_app_android.data.RegisterDataSource;
import com.example.cookmaster_app_android.data.Result;
import com.example.cookmaster_app_android.data.model.LoggedInUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etAddress;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhoneNumber;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView tvErrorMessage = findViewById(R.id.tvErrorMessage);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupère les valeurs saisies par l'utilisateur
                String nom = etFirstName.getText().toString();
                String prenom = etLastName.getText().toString();
                String adresse = etAddress.getText().toString();
                String email = etEmail.getText().toString();
                String mot_de_passe = etPassword.getText().toString();
                String telephone = etPhoneNumber.getText().toString();

                // Crée une instance de RegisterDataSource
                RegisterDataSource registerDataSource = new RegisterDataSource();

                // Appel de la méthode register() avec les données récupérées
                Result<LoggedInUser> result = registerDataSource.register(nom, prenom, adresse, mot_de_passe, email, telephone, new RegisterDataSource.RegisterCallback() {
                    @Override
                    public void onRegisterSuccess(LoggedInUser loggedInUser) {

                    }

                    @Override
                    public void onRegisterError(Throwable t) {

                    }

                    @Override
                    public void onSuccess(LoggedInUser loggedInUser) {
                        // Inscription réussie, vous pouvez traiter l'utilisateur connecté ici
                        tvErrorMessage.setVisibility(View.GONE); // Cacher le message d'erreur s'il est affiché
                        // Faites ce que vous devez faire après une inscription réussie
                    }

                    @Override
                    public void onError(Exception error) {
                        // Erreur lors de l'inscription, vous pouvez traiter l'erreur ici
                        String errorMessage = "Erreur : " + error.getMessage();
                        tvErrorMessage.setText(errorMessage);
                        tvErrorMessage.setVisibility(View.VISIBLE); // Afficher le message d'erreur
                    }
                });








            }
}
        );}
}
