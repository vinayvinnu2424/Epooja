package com.example.epoojatest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epoojatest.AdaptersandModels.AdapterImage;
import com.example.epoojatest.AdaptersandModels.ProductDetailsDataModel;
import com.example.epoojatest.AdaptersandModels.ProductDetailsModel;
import com.example.epoojatest.R;
import com.example.epoojatest.Retrofit.RetrofitBaseurl;
import com.example.epoojatest.Retrofit.RetrofitMethod;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView img_close;

    ImageView img_heart;

    TextView txt_title2;
    TextView txt_product1;
    TextView txt_available;
    TextView txt_name;
    TextView txt_details;
    TextView txt_cost;
    TextView txt_stock;
    TextView txt_count;
    LinearLayout linear_1;
    Button btnSoldOut;
    RatingBar rating_bar;
    RelativeLayout relative_minus;
    RelativeLayout relative_plus;
    CardView card_fav;
    CardView card_addcart;
    String product_id;
    String name;

    String favcolor ="0";
    ViewPager viewPager;

    AdapterImage adapterImage;
    List<ProductDetailsDataModel.ImageModel> imageModelList;


    int aval =0;
    float rating = 0.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ProductDetailsActivity.this, R.color.colorAccent));

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            product_id = b.getString("product_id");
            name = b.getString("name");

        }

        txt_title2 = findViewById(R.id.txt_title2);
        txt_product1 = findViewById(R.id.txt_product1);
        txt_available = findViewById(R.id.txt_available);
        txt_name = findViewById(R.id.txt_name);
        txt_details = findViewById(R.id.txt_details);
        txt_count = findViewById(R.id.txt_count);
        img_close = findViewById(R.id.img_close);
        img_heart = findViewById(R.id.img_heart);
        txt_cost = findViewById(R.id.txt_cost);
        txt_stock = findViewById(R.id.txt_stock);
        btnSoldOut = findViewById(R.id.btnSoldOut);
        linear_1 = findViewById(R.id.linear_1);
        rating_bar = findViewById(R.id.rating_bar);
        relative_minus = findViewById(R.id.relative_minus);
        relative_plus = findViewById(R.id.relative_plus);
        card_fav = findViewById(R.id.card_fav);
        card_addcart = findViewById(R.id.card_addcart);
        viewPager = findViewById(R.id.viewPager);

        imageModelList = new ArrayList<>();

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });

        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favcolor == "0"){
                    img_heart.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                }else{
                    favcolor = "1";
                    img_heart.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                }
            }
        });

        relative_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(txt_count.getText().toString()) < aval) {
                    if (Integer.parseInt(txt_count.getText().toString()) >= 1) {
                        Integer c = Integer.parseInt(txt_count.getText().toString()) + 1;
                        txt_count.setText(c + "");
                    }
                }
            }
        });
        relative_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(txt_count.getText().toString()) >1){
                    Integer c = Integer.parseInt(txt_count.getText().toString()) -1;
                    txt_count.setText(c+"");
                }else {

                }
            }
        });
        Productdetails();
    }
    private void Productdetails() {
        RetrofitMethod retrofitMethod = RetrofitBaseurl.getRetrofitInstance().create(RetrofitMethod.class);
        Call<ProductDetailsModel> call = retrofitMethod.getProducts(product_id, "1", "");
        call.enqueue(new Callback<ProductDetailsModel>() {
            @Override
            public void onResponse(Call<ProductDetailsModel> call, Response<ProductDetailsModel> response) {

                if (response.isSuccessful()) {
                    ProductDetailsModel data = response.body();

                    //set Details
                    txt_name.setText(data.getProductDetailsDataModel().getName());
                    txt_stock.setText(data.getProductDetailsDataModel().getStock_status());
                    txt_details.setText(data.getProductDetailsDataModel().getDescription());
                    txt_cost.setText("INR:"+data.getProductDetailsDataModel().getActualprice());
                    txt_available.setText("availability: "+data.getProductDetailsDataModel().getAvailability());

                    rating = Float.parseFloat(data.getProductDetailsDataModel().getRating());
                    aval = Integer.parseInt(data.getProductDetailsDataModel().getAvailability());
                    if (aval < 1) {
                        btnSoldOut.setVisibility(View.VISIBLE);
                        linear_1.setVisibility(View.GONE);
                    } else {
                        btnSoldOut.setVisibility(View.GONE);
                        linear_1.setVisibility(View.VISIBLE);
                    }

                    //set List
                    if (!data.getProductDetailsDataModel().getImages().isEmpty()){
                        for (int i=0; i<data.getProductDetailsDataModel().getImages().size(); i++) {
                            imageModelList.add(data.getProductDetailsDataModel().getImages().get(i));
                        }
                    }

                    adapterImage = new AdapterImage(getApplicationContext(), imageModelList,product_id);
                    viewPager.setAdapter(adapterImage);

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