package com.example.kfc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewRegister = findViewById(R.id.textView2);
        editTextEmail = findViewById(R.id.editTextEmail);
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

                if (!email.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USER_EMAIL", email);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                }
            }
        });
    }
}
