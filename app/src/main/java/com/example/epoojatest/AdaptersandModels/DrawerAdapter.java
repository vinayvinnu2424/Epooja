package com.example.epoojatest.AdaptersandModels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epoojatest.Config.OnItemClickListener;
import com.example.epoojatest.R;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private List<DrawerModel> drawarModelList;
    private Context context;
    private OnItemClickListener listener;

    private int selectedItem = 0;


    public DrawerAdapter(List<DrawerModel> drawarModelList, Context context, OnItemClickListener listener) {
        this.drawarModelList = drawarModelList;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DrawerModel drawarModel = drawarModelList.get(position);


        holder.txt_name.setText(drawarModel.getName());
        holder.img_icon.setImageResource(drawarModel.getImage());

        holder.itemView.setOnClickListener(view -> {

            int previousSelectedItem = selectedItem;
            selectedItem = position;
            notifyItemChanged(previousSelectedItem);
            notifyItemChanged(selectedItem);
            listener.onItemClick(position);


        });

        if (selectedItem == position){
            int color = ContextCompat.getColor(context, R.color.colorAccent);
            //image
            holder.img_icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            //text
            holder.txt_name.setTextColor(color);

        }else {
            int color = ContextCompat.getColor(context, R.color.gray);
            //image
            holder.img_icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            //text
            holder.txt_name.setTextColor(color);

        }


    }

    @Override
    public int getItemCount() {
        return drawarModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_;
        ImageView img_icon;
        TextView txt_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linear_ = itemView.findViewById(R.id.linear_);
            img_icon = itemView.findViewById(R.id.img_icon);
            txt_name = itemView.findViewById(R.id.txt_name);
        }
    }
}
