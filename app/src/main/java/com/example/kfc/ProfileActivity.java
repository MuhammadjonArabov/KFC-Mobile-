package com.example.kfc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewEmail, textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewEmail = findViewById(R.id.textViewEmail);
        textViewName = findViewById(R.id.textViewName);

        // Intent orqali kelgan ma'lumotlarni olish
        Intent intent = getIntent();
        String email = intent.getStringExtra("USER_EMAIL");
        String name = intent.getStringExtra("USER_NAME");

        // SharedPreferences dan ma'lumotlarni olish
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        if (email == null || email.trim().isEmpty()) {
            email = sharedPreferences.getString("USER_EMAIL", "Email topilmadi");
        }

        if (name == null || name.trim().isEmpty()) {
            name = sharedPreferences.getString("USER_NAME", "Foydalanuvchi nomi yo‘q");
        }

        // Olingan ma'lumotlarni ekranga chiqarish
        textViewEmail.setText(email);
        textViewName.setText(name);
    }
}
