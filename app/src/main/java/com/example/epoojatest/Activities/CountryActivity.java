package com.example.epoojatest.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.epoojatest.MainActivity;
import com.example.epoojatest.R;

import java.util.prefs.Preferences;

public class CountryActivity extends AppCompatActivity {
    CardView card_country;
    String result = "";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(CountryActivity.this,R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        card_country = findViewById(R.id.card_country);

        card_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrypopup();
            }

            private void countrypopup() {

                AlertDialog dialog;
                AlertDialog.Builder mbuilder =new AlertDialog.Builder(CountryActivity.this);
                View mview =  LayoutInflater.from(CountryActivity.this).inflate(R.layout.custompopup,null);
                TextView bt_yes =(TextView) mview.findViewById(R.id.txt_ok);
                TextView bt_no=(TextView) mview.findViewById(R.id.txt_cancel);
                RadioButton radioButton1= (RadioButton) mview.findViewById(R.id.radiobutton1);
                RadioButton radioButton2= (RadioButton) mview.findViewById(R.id.radiobutton2);

                mbuilder.setView(mview);
                dialog=mbuilder.create();
                dialog.show();

                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);

                bt_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent log = new Intent(CountryActivity.this, MainActivity.class);
                        startActivity(log);
                    }
                });

                bt_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent login = new Intent(CountryActivity.this, LoginsActivity.class);
                        startActivity(login);
                    }
                });
            }
        });

    }


}