package com.example.kfc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewRegister = findViewById(R.id.textView2);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Ro‘yxatdan o‘tish sahifasiga o'tish
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Login tugmachasi bosilganda emailni saqlash va ProfileActivity ga o'tish
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email kiriting!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(LoginActivity.this, "Noto‘g‘ri email formati!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Parolni kiriting!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Emailni saqlash
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_EMAIL", email);
                editor.apply();

                // Profile sahifasiga o'tish
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.putExtra("USER_EMAIL", email);
                startActivity(intent);
            }
        });
    }
}
