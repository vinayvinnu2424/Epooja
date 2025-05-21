package com.example.epoojatest.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.epoojatest.AdaptersandModels.ProductAdapter;
import com.example.epoojatest.AdaptersandModels.ProductModel;
import com.example.epoojatest.Config.ProductItemThree;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductActivity extends AppCompatActivity {
    ImageView img_close;
    TextView txt_title;

    CardView cardview_progress;

    String Name;
    String Category_id;

    RecyclerView recyclerview;
    RecyclerView.LayoutManager layoutManager;
    ProductAdapter productAdapter;
    ArrayList<ProductModel.category_products> productModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(CategoryProductActivity.this,R.color.colorAccent));
        Intent intent = getIntent();
        Bundle b =intent.getExtras();
        if (b!=null){
            Name = b.getString("Name");
            Category_id = b.getString("Category_id");
        }

        img_close = findViewById(R.id.img_close);

        txt_title = findViewById(R.id.txt_title);
        cardview_progress = findViewById(R.id.cardview_progress);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //recyclerview list
        recyclerview = findViewById(R.id.recyclerview);
//        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setLayoutManager(new LinearLayoutManager(CategoryProductActivity.this, LinearLayoutManager.VERTICAL, false));
        productModelArrayList = new ArrayList<>();


        productapi();
    }

    private void productapi() {

        ProgressDialog progressdialog = new ProgressDialog(CategoryProductActivity.this);
        progressdialog.setTitle("EpoojaStore");
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(true);
        progressdialog.show();

        cardview_progress.setVisibility(View.GONE);
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<ProductModel> call = retrofitMethod.getAlldata(Category_id,"1","1","20");

        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                cardview_progress.setVisibility(View.GONE);
                progressdialog.dismiss();

                ProductModel productModel = response.body();

                if (productModel!=null){

                    for (int i = 0; i < productModel.getCategory_products().size(); i++){
                        productModelArrayList.add(productModel.getCategory_products().get(i));
                    }
                    productAdapter = new ProductAdapter(productModelArrayList, getApplicationContext(), new ProductItemThree() {
                        @Override
                        public void onItemClick(View view, String Product_id, String name, String image) {

                        }
                    }) {
                        @Override
                        public void onItemClick(View view, String Product_id, String name, String image) {

                        }
                    };
                    recyclerview.setAdapter(productAdapter);

                }else {
                    Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                cardview_progress.setVisibility(View.GONE);
                progressdialog.dismiss();
            }
        });
    }
}