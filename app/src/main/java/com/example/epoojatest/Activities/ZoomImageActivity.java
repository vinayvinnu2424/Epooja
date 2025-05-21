package com.example.epoojatest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epoojatest.AdaptersandModels.AdapterImage;
import com.example.epoojatest.AdaptersandModels.ProductDetailsDataModel;
import com.example.epoojatest.AdaptersandModels.ProductDetailsModel;
import com.example.epoojatest.AdaptersandModels.ZoomImageAdapter;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZoomImageActivity extends AppCompatActivity {
    ViewPager viewPager;

    ZoomImageAdapter zoomImageAdapter;
    List<ProductDetailsDataModel.ImageModel> imageModelList;
    String images_array = "";
    String product_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ZoomImageActivity.this, R.color.colorAccent));

        Intent intent = getIntent();
        Bundle b =intent.getExtras();
        if (b!=null){
            images_array  = b.getString("images_array");
            product_id = b.getString("product_id");
        }

        Toast.makeText(getApplicationContext(),product_id,Toast.LENGTH_SHORT).show();
        viewPager = findViewById(R.id.viewPager);

        imageModelList = new ArrayList<>();

      imagezoom();
    }

    private void imagezoom() {
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<ProductDetailsModel> call = retrofitMethod.getProducts(product_id, "1", "");
        call.enqueue(new Callback<ProductDetailsModel>() {
            @Override
            public void onResponse(Call<ProductDetailsModel> call, Response<ProductDetailsModel> response) {

                if (response.isSuccessful()) {
                    ProductDetailsModel data = response.body();


                    //set List
                    if (!data.getProductDetailsDataModel().getImages().isEmpty()) {
                        for (int i=0; i<data.getProductDetailsDataModel().getImages().size(); i++) {
                            imageModelList.add(data.getProductDetailsDataModel().getImages().get(i));
                        }
                    }
                    Toast.makeText(getApplicationContext()," Success",Toast.LENGTH_SHORT).show();
                    zoomImageAdapter = new ZoomImageAdapter(getApplicationContext(), imageModelList);
                    viewPager.setAdapter(zoomImageAdapter);

                } else {
                    // Handle error response
                    Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ProductDetailsModel> call, Throwable t) {
                // Handle failure
                Log.e("error",t.getMessage()+"");
            }
        });


    }



}