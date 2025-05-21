package com.example.epoojatest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epoojatest.MainActivity;
import com.example.epoojatest.R;

import java.util.prefs.Preferences;

public class LoginotpActivity extends AppCompatActivity {
    EditText editotp;
    Button btsubmit;
    String otp = "";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(LoginotpActivity.this, R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginotp);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            otp = b.getString("otp");
            id = b.getString("id");
        }
        editotp = findViewById(R.id.editotp);
        btsubmit = findViewById(R.id.btsubmit);

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editotp.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Otp", Toast.LENGTH_SHORT).show();
                } else {
                    Intent inte = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(inte);
                }
            }
        });

    }
}