package com.example.lab7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText edUsername, edPassword, edConfirmPassword;
    private Button btnCreateUser;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        edConfirmPassword = findViewById(R.id.ed_confirm_pwd);
        btnCreateUser = findViewById(R.id.btn_create_user);

        btnCreateUser.setOnClickListener(view -> {
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            String confirm = edConfirmPassword.getText().toString();

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences sp = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Username", username);
            editor.putString("Password", password);
            editor.apply();

            Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
