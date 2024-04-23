package com.example.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    View loginView;
    Button btnLExit, btnLogin, btnLSignUp;
    EditText etLoginUsername, etLoginPassword;
    ImageButton btnToggleLoginPassword;

    View signupView;
    Button btnSExit, btnSignUp, btnSLogin;
    EditText etSignUpUsername, etSignUpPassword, etSConfirmPassword;
    TextView passwordMismatchError;
    ImageButton btnTogglePassword, btnToggleConfirmPassword;
    RelativeLayout rlConfirm;


    Fragment LoginFragment, SignUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        SharedPreferences.Editor editor1 = sPref.edit();


        boolean flag = sPref.getBoolean("isLogin", false);
        if(flag)
        {
            Intent intent = new Intent(MainActivity.this,
                    Home.class);
            startActivity(intent);
            finish();
        }

        btnToggleLoginPassword.setOnClickListener(v -> {
            int selection = etLoginPassword.getSelectionEnd();

            if (etLoginPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnToggleLoginPassword.setImageResource(R.drawable.visibility_off_24px);
            } else {
                etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnToggleLoginPassword.setImageResource(R.drawable.visibility_24px);
            }

            // Restore cursor position
            etLoginPassword.setSelection(selection);
        });

        btnTogglePassword.setOnClickListener(v -> {
            int selection = etSignUpPassword.getSelectionEnd();

            if (etSignUpPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                etSignUpPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.visibility_off_24px);
            } else {
                etSignUpPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnTogglePassword.setImageResource(R.drawable.visibility_24px);
            }

            // Restore cursor position
            etSignUpPassword.setSelection(selection);
        });

        btnToggleConfirmPassword.setOnClickListener(v -> {
            int selection = etSConfirmPassword.getSelectionEnd();

            if (etSConfirmPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                etSConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnToggleConfirmPassword.setImageResource(R.drawable.visibility_off_24px);
            } else {
                etSConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnToggleConfirmPassword.setImageResource(R.drawable.visibility_24px);
            }

            // Restore cursor position
            etSConfirmPassword.setSelection(selection);
        });

        btnLSignUp.setOnClickListener(view -> manager.beginTransaction()
                .hide(LoginFragment)
                .show(SignUpFragment)
                .commit());

        btnSLogin.setOnClickListener(view -> manager.beginTransaction()
                .show(LoginFragment)
                .hide(SignUpFragment)
                .commit());

        etSConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String confirmPassword = s.toString();
                String originalPassword = Objects.requireNonNull(etSignUpPassword.getText()).toString(); // Assuming etPassword is your original password field
                if (!confirmPassword.equals(originalPassword)) {
                    // Passwords don't match, show error message
                    passwordMismatchError.setVisibility(View.VISIBLE);
                    rlConfirm.setBackgroundResource(R.drawable.edit_text_border_error); // Set error border color
                } else {
                    // Passwords match, hide error message
                    passwordMismatchError.setVisibility(View.GONE);
                    rlConfirm.setBackgroundResource(R.drawable.edit_text_border);
                }
            }
        });

        btnSignUp.setOnClickListener(view -> {
            String Pass = Objects.requireNonNull(etSignUpPassword.getText()).toString();
            String ConfirmPass = Objects.requireNonNull(etSConfirmPassword.getText()).toString();
            String SUsername = Objects.requireNonNull(etSignUpUsername.getText()).toString();
            if (!(Pass.isEmpty()) && !(ConfirmPass.isEmpty()) && !(SUsername.isEmpty())) {
                if(Pass.equals(ConfirmPass))
                {
                    editor.putString("username", Objects.requireNonNull(etSignUpUsername.getText()).toString());
                    editor.putString("password", Objects.requireNonNull(etSignUpPassword.getText()).toString());
                    editor.apply();

                    Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                    editor1.putBoolean("isLogin", true);
                    editor1.apply();

                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            }
            else {
                Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnLExit.setOnClickListener(v -> finish());
        btnSExit.setOnClickListener(v -> finish());

        btnLogin.setOnClickListener(view -> {
            String email = sPref.getString("email", null);
            String password = sPref.getString("password", null);
            if(Objects.requireNonNull(etLoginPassword.getText()).toString().equals(email)
                    && Objects.requireNonNull(etLoginPassword.getText()).toString().equals(password))
            {
                editor1.putBoolean("isLogin", true);
                editor1.apply();

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void init()
    {
        manager = getSupportFragmentManager();

        loginView = Objects.requireNonNull(manager.findFragmentById(R.id.fragLogin)).requireView();
        etLoginUsername = loginView.findViewById(R.id.etLoginUsername);
        etLoginPassword = loginView.findViewById(R.id.etLoginPassword);
        btnLExit = loginView.findViewById(R.id.btnLExit);
        btnLogin = loginView.findViewById(R.id.btnLogin);
        btnLSignUp = loginView.findViewById(R.id.btnLSignUp);
        btnToggleLoginPassword  = loginView.findViewById(R.id.btnToggleLoginPassword);
        LoginFragment = manager.findFragmentById(R.id.fragLogin);

        signupView = Objects.requireNonNull(manager.findFragmentById(R.id.fragSignup)).requireView();
        etSignUpUsername = signupView.findViewById(R.id.etSignUpUsername);
        etSignUpPassword = signupView.findViewById(R.id.etSignUpPassword);
        etSConfirmPassword = signupView.findViewById(R.id.etSConfirmPassword);
        btnSExit = signupView.findViewById(R.id.btnSExit);
        btnSignUp = signupView.findViewById(R.id.btnSignUp);
        btnSLogin = signupView.findViewById(R.id.btnSLogin);
        passwordMismatchError = signupView.findViewById(R.id.passwordMismatchError);
        btnTogglePassword  = signupView.findViewById(R.id.btnTogglePassword);
        btnToggleConfirmPassword  = signupView.findViewById(R.id.btnToggleConfirmPassword);
        rlConfirm = signupView.findViewById(R.id.rlConfirm);
        SignUpFragment = manager.findFragmentById(R.id.fragSignup);
    }
}