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

public class SignupActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView textViewLogin = findViewById(R.id.textView2);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonSignUp = findViewById(R.id.buttonSignup);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Login sahifasiga o'tish
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Signup tugmachasi bosilganda name, emailni saqlash va ProfileActivity ga o'tish
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Email kiritilishi shart", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignupActivity.this, "Noto'g'ri email formati", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_EMAIL", email);
                editor.putString("USER_NAME", name);
                editor.apply();

                Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                intent.putExtra("USER_EMAIL", email);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
            }
        });
    }
}
