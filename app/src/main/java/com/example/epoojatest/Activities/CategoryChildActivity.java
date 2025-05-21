package com.example.epoojatest.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epoojatest.AdaptersandModels.CategoryModel;
import com.example.epoojatest.AdaptersandModels.ChildAdapter;
import com.example.epoojatest.Config.ChildItemThree;
import com.example.epoojatest.MainActivity;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryChildActivity extends AppCompatActivity {
    ImageView img_close;
    TextView txt_title1;
    String parent_id = "";
    String Name = "";

    CardView cardview_progress;

    RecyclerView recyclerview;
    ChildAdapter childAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(CategoryChildActivity.this,R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            parent_id = b.getString("parent_id");
            Name = b.getString("Name");
        }
        cardview_progress = findViewById(R.id.cardview_progress);
        txt_title1 = findViewById(R.id.txt_title1);
        img_close = findViewById(R.id.img_close);
        recyclerview = findViewById(R.id.recyclerview);
        txt_title1.setText(Name);


        getsubcat();

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void getsubcat() {

        ProgressDialog progressdialog = new ProgressDialog(CategoryChildActivity.this);
        progressdialog.setTitle("EpoojaStore");
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(true);
        progressdialog.show();

        cardview_progress.setVisibility(View.GONE);
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<CategoryModel> call = retrofitMethod.getCategories();

        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                Log.e(TAG, "onResponse: " + response.code());

                cardview_progress.setVisibility(View.GONE);
                progressdialog.dismiss();

                CategoryModel categoryModel = response.body();

                if (categoryModel != null) {

                    for (int i = 0; i < categoryModel.getCategories().size(); i++) {

                        if (categoryModel.getCategories().get(i).getSubcategories() != null) {

                            for (int j = 0; j < categoryModel.getCategories().get(i).getSubcategories().size(); j++) {

                                if (parent_id.equalsIgnoreCase(categoryModel.getCategories().get(i).getSubcategories().get(j).getParent_id())) {
                                    recyclerview.setLayoutManager(layoutManager);
                                    recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                                    childAdapter = new ChildAdapter(categoryModel.getCategories().get(i).getSubcategories(), CategoryChildActivity.this, new ChildItemThree() {
                                        @Override
                                        public void onItemClick(View view, String category_id, String name, String image) {

                                        }
                                    });
                                    recyclerview.setAdapter(childAdapter);
                                }

                            }

                        }


                    }


                } else {
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                cardview_progress.setVisibility(View.GONE);
                progressdialog.dismiss();
            }
        });


    }
}