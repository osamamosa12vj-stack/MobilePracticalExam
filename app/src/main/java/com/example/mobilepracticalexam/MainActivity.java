package com.example.mobilepracticalexam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private MaterialButton btnLogin;

    private boolean doubleBackToExitPressedOnce = false;

    private final Handler handler =
            new Handler(Looper.getMainLooper());

    private final Runnable resetBackPress = () ->
            doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initialize Views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Dynamic Validation using TextWatcher
        TextWatcher loginWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {

                validateLogin();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        etEmail.addTextChangedListener(loginWatcher);
        etPassword.addTextChangedListener(loginWatcher);

        // Login Button
        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    && password.length() >= 6) {

                Intent intent = new Intent(
                        MainActivity.this,
                        ModeActivity.class
                );

                startActivity(intent);
            }
        });

        // Double Back Press Exit
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackPressed() {

                        if (doubleBackToExitPressedOnce) {

                            handler.removeCallbacks(resetBackPress);
                            finishAffinity();

                        } else {

                            doubleBackToExitPressedOnce = true;

                            Snackbar.make(
                                    findViewById(R.id.mainLayout),
                                    "Press back again to exit",
                                    Snackbar.LENGTH_SHORT
                            ).show();

                            handler.postDelayed(
                                    resetBackPress,
                                    2000
                            );
                        }
                    }
                }
        );
    }

    // Email and Password Validation
    private void validateLogin() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean validEmail =
                Patterns.EMAIL_ADDRESS.matcher(email).matches();

        boolean validPassword =
                password.length() >= 6;

        btnLogin.setEnabled(validEmail && validPassword);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(resetBackPress);
        super.onDestroy();
    }
}