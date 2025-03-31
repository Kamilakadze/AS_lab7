package com.example.lab7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin, btnSignUp;
    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(v -> {
            SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
            String savedUsername = credentials.getString("Username", null);
            String savedPassword = credentials.getString("Password", null);

            String inputUsername = edUsername.getText().toString();
            String inputPassword = edPassword.getText().toString();

            if (savedUsername != null && savedPassword != null &&
                    savedUsername.equalsIgnoreCase(inputUsername) &&
                    savedPassword.equalsIgnoreCase(inputPassword)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Переход к активности с чтением файла
                startActivity(new Intent(this, FileActivity.class));
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
    }
}
