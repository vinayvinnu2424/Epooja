package com.example.epoojatest.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epoojatest.AdaptersandModels.CategoryModel;
import com.example.epoojatest.AdaptersandModels.LoginModel;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edit_mobremail;
    CardView card_sign;
    ImageView img_google;
    TextView txt_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        card_sign = findViewById(R.id.card_sign);
        edit_mobremail = findViewById(R.id.edit_mobremail);
        img_google = findViewById(R.id.img_google);
        txt_1 = findViewById(R.id.txt_1);

        card_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_mobremail.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    loginotp();
                }
            }
        });

        loginotp();
    }

    private void loginotp() {
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<LoginModel> call = retrofitMethod.loginWithOTP(edit_mobremail.getText().toString());
        call.enqueue(new Callback<LoginModel>() {

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    if (loginModel != null) {
                        String status = loginModel.getStatus();
                        String otp = loginModel.getOtp();
                        String id = loginModel.getId();
                        txt_1.setText(loginModel.getStatus());
                        Intent intent = new Intent(getApplicationContext(), LoginotpActivity.class);
                        intent.putExtra("otp", otp);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}