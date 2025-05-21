package com.example.epoojatest.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epoojatest.AdaptersandModels.LoginModel;
import com.example.epoojatest.MainActivity;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginsActivity extends AppCompatActivity {
    EditText edit_mobileno;
    Button bt_submit;
    TextView txt_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(LoginsActivity.this,R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        edit_mobileno = findViewById(R.id.edit_mobileno);
        bt_submit = findViewById(R.id.bt_submit);
        txt_otp = findViewById(R.id.txt_otp);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_mobileno.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }else {
                    otplogin();

                }
            }
        });
    }

    private void otplogin() {
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<LoginModel> call = retrofitMethod.loginWithOTP(edit_mobileno.getText().toString());
        call.enqueue(new Callback<LoginModel>() {

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    assert loginModel != null;
                    txt_otp.setText(loginModel.getOtp());
                    Intent intent = new Intent(LoginsActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}