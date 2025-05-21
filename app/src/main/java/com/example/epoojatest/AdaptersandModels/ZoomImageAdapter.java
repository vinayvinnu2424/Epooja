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
import com.jsibbold.zoomage.ZoomageView;

import java.util.List;

public class ZoomImageAdapter extends PagerAdapter {

    Context Mcontext;
    List<ProductDetailsDataModel.ImageModel> my_data;
    public ZoomImageAdapter(Context Mcontext, List<ProductDetailsDataModel.ImageModel> my_data) {
        this.my_data = my_data;
        this.Mcontext = Mcontext;
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
        View view = inflater.inflate(R.layout.zoomimage, container, false);

        ZoomageView zoomageView = view.findViewById(R.id.myZoomageView);

        Glide.with(Mcontext)
                .load( "https://www.epoojastore.in/image/"+my_data.get(position).getUrl())
                .placeholder(R.drawable.splashbg)
                .error(R.drawable.splashbg)
                .into(zoomageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}


