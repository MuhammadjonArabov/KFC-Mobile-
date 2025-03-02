package com.example.kfc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewEmail = findViewById(R.id.textView2);

        // Intent orqali emailni olish
        Intent intent = getIntent();
        String email = intent.getStringExtra("USER_EMAIL");

        // Agar Intent bo'sh bo'lsa, SharedPreferences dan olish
        if (email == null || email.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            email = sharedPreferences.getString("USER_EMAIL", "Email topilmadi");
        }

        // TextView-ga emailni o‘rnatish
        textViewEmail.setText(email);
    }
}
