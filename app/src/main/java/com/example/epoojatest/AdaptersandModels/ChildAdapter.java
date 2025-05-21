package com.example.epoojatest.AdaptersandModels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epoojatest.Activities.CategoryChildActivity;
import com.example.epoojatest.Activities.CategoryProductActivity;
import com.example.epoojatest.Config.ChildItemThree;
import com.example.epoojatest.R;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter {

    ChildItemThree childItemthree;
    Context context;
    ArrayList<CategoryModel.ChildModel> my_data;
    CategoryModel.ChildModel childModel;

    public ChildAdapter(ArrayList<CategoryModel.ChildModel> my_data, Context context, ChildItemThree childItemthree) {
        this.my_data = my_data;
        this.context = context;
        this.childItemthree = childItemthree;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_listitems,parent,false);
        return new MenuViewHolder(view) ;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChildAdapter.MenuViewHolder holder1 = (ChildAdapter.MenuViewHolder) holder;
        childModel = my_data.get(position);
        holder1.txt_name.setText(childModel.getName());

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_pro;
        ImageView img_pro;
        TextView txt_name;
        public MenuViewHolder(View view) {
            super(view);

            linear_pro = view.findViewById(R.id.linear_pro);
            img_pro = view.findViewById(R.id.img_pro);
            txt_name = view.findViewById(R.id.txt_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Category_id = my_data.get(getAdapterPosition()).getCategory_id();
                    String Name = my_data.get(getAdapterPosition()).getName();
                    Context context = v.getContext();
                    Intent vk = new Intent(context, CategoryProductActivity.class);
                    vk.putExtra("Category_id",Category_id);
                    vk.putExtra("Name",Name);
                    context.startActivity(vk);
                }
            });
        }
    }
}
