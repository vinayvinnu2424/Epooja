package com.example.epoojatest.AdaptersandModels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.epoojatest.Activities.ZoomImageActivity;
import com.example.epoojatest.R;

import java.util.List;

public class AdapterImage  extends PagerAdapter {

    Context Mcontext;
    List<ProductDetailsDataModel.ImageModel> my_data;
    String productid = "";
    public AdapterImage(Context Mcontext, List<ProductDetailsDataModel.ImageModel> my_data, String productid) {
        this.my_data = my_data;
        this.Mcontext = Mcontext;
        this.productid = productid;
    }

    @Override
    public int getCount() {
        return my_data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(Mcontext);
        View view = inflater.inflate(R.layout.item_image, container, false);

        ImageView imageView = view.findViewById(R.id.ivCode);

        Glide.with(Mcontext)
                .load( "https://www.epoojastore.in/image/"+my_data.get(position).getUrl())
                .placeholder(R.drawable.splashbg)
                .error(R.drawable.splashbg)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent zoom = new Intent(context, ZoomImageActivity.class);
                zoom.putExtra("images_array",my_data.get(position).getUrl());
                zoom.putExtra("product_id",productid);
                zoom.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(zoom);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
