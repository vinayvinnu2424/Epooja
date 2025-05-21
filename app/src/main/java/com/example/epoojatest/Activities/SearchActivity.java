package com.example.epoojatest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.epoojatest.MainActivity;
import com.example.epoojatest.R;

public class SearchActivity extends AppCompatActivity {
    EditText edit_search;
    ImageView img_back;
    ImageView img_closing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(SearchActivity.this,R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edit_search = findViewById(R.id.edit_search);
        img_back = findViewById(R.id.img_back);
        img_closing = findViewById(R.id.img_closing);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}