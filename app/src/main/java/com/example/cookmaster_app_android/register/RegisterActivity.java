package com.example.cookmaster_app_android.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookmaster_app_android.MainActivity;
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
        etFirstName = findViewById(R.id.textViewFirstName);
        etLastName = findViewById(R.id.textViewLastName);
        etAddress = findViewById(R.id.textViewAddress);
        etEmail = findViewById(R.id.textViewEmail);
        etPassword = findViewById(R.id.textViewPassword);
        etPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);
        // Obtenir une instance du AutofillManager
        AutofillManager autofillManager = getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            // Demander une saisie automatique pour le champ etFirstName
            autofillManager.requestAutofill(etFirstName);
            autofillManager.requestAutofill(etLastName);
            autofillManager.requestAutofill(etAddress);
            autofillManager.requestAutofill(etEmail);
            autofillManager.requestAutofill(etPassword);
            autofillManager.requestAutofill(etPhoneNumber);
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(AutofillManager.EXTRA_AUTHENTICATION_RESULT)) {
            // Récupérer les données de remplissage automatique et les traiter
            Bundle dataBundle = intent.getBundleExtra(AutofillManager.EXTRA_AUTHENTICATION_RESULT);
            if (dataBundle != null) {
                // Récupérer les données de remplissage automatique
                AutofillValue firstNameValue = dataBundle.getParcelable("lastname");
                AutofillValue lastNameValue = dataBundle.getParcelable("firstname");
                AutofillValue addressValue = dataBundle.getParcelable("address");
                AutofillValue emailValue = dataBundle.getParcelable("email");
                AutofillValue passwordValue = dataBundle.getParcelable("password");
                AutofillValue phoneNumberValue = dataBundle.getParcelable("phone");

                // Traiter les données de remplissage automatique
                if (firstNameValue != null) {
                    String firstName = firstNameValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur du prénom
                    TextView textViewFirstName = findViewById(R.id.textViewFirstName);
                    textViewFirstName.setText(firstName);
                }

                if (lastNameValue != null) {
                    String lastName = lastNameValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur du nom de famille
                    TextView textViewLastName = findViewById(R.id.textViewLastName);
                    textViewLastName.setText(lastName);
                }

                if (addressValue != null) {
                    String address = addressValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur de l'adresse
                    TextView textViewAddress = findViewById(R.id.textViewAddress);
                    textViewAddress.setText(address);
                }

                if (emailValue != null) {
                    String email = emailValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur de l'e-mail
                    TextView textViewEmail = findViewById(R.id.textViewEmail);
                    textViewEmail.setText(email);
                }

                if (passwordValue != null) {
                    String password = passwordValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur du mot de passe
                    TextView textViewPassword = findViewById(R.id.textViewPassword);
                    textViewPassword.setText(password);
                }

                if (phoneNumberValue != null) {
                    String phoneNumber = phoneNumberValue.getTextValue().toString();
                    // Faites quelque chose avec la valeur du numéro de téléphone
                    TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
                    textViewPhoneNumber.setText(phoneNumber);
                }
            }
        }


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
                        // Inscription réussie, vous pouvez traiter l'utilisateur connecté ici
                        tvErrorMessage.setVisibility(View.GONE); // Cacher le message d'erreur s'il est affiché
                        // Faites ce que vous devez faire après une inscription réussie
                        // Par exemple, vous pouvez afficher un message de succès ou rediriger l'utilisateur vers une autre activité
                        showToast("Inscription réussie");
                        redirectToHomeActivity();
                    }

                    @Override
                    public void onRegisterError(Throwable t) {
                        // Erreur lors de l'inscription, vous pouvez traiter l'erreur ici
                        String errorMessage = "Erreur : " + t.getMessage();
                        tvErrorMessage.setText(errorMessage);
                        tvErrorMessage.setVisibility(View.VISIBLE); // Afficher le message d'erreur
                    }

                    @Override
                    public void onSuccess(LoggedInUser loggedInUser) {
                        // Cette méthode n'est plus utilisée pour l'inscription, vous pouvez laisser vide
                    }

                    @Override
                    public void onError(Exception e) {
                        // Cette méthode n'est plus utilisée pour l'inscription, vous pouvez laisser vide
                    }
                });







            }
}
        );}

    private void redirectToHomeActivity() {
        // Rediriger l'utilisateur vers l'activité principale de l'application (par exemple, HomeActivity)
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Facultatif : fermer l'activité d'inscription pour qu'elle ne puisse pas être accessible en arrière-plan
    }

    private void showToast(String inscription_réussie) {
        // Afficher un message toast avec le message spécifié
        String message = inscription_réussie;
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
