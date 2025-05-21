package com.example.epoojatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.epoojatest.Activities.SearchActivity;
import com.example.epoojatest.AdaptersandModels.DrawerAdapter;
import com.example.epoojatest.AdaptersandModels.DrawerModel;
import com.example.epoojatest.Config.OnItemClickListener;
import com.example.epoojatest.Fragments.CartFragment;
import com.example.epoojatest.Fragments.FavouriteFragment;
import com.example.epoojatest.Fragments.HomeFragment;
import com.example.epoojatest.Fragments.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView img_glob;
    ImageView img_cart;
    CardView card_search;

    RecyclerView recyclerView;

    DrawerAdapter drawarAdapter;

    private List<DrawerModel> drawarModelList;
    Fragment epoojatestfragment;
    public static FrameLayout fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_glob = findViewById(R.id.img_glob);
        img_cart = findViewById(R.id.img_cart);
        card_search = findViewById(R.id.card_search);


        card_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serach = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(serach);
            }
        });


        epoojatestfragment = new HomeFragment();
        changeFragment(epoojatestfragment);

        drawer_List();

    }

    private void drawer_List() {

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        drawarModelList = new ArrayList<>();
        drawarModelList.add(new DrawerModel("Home", R.drawable.home_ic));
        drawarModelList.add(new DrawerModel("Cart", R.drawable.cart_ic));
        drawarModelList.add(new DrawerModel("Favourite", R.drawable.fav_ic));
        drawarModelList.add(new DrawerModel("Profile", R.drawable.profile_ic));

        drawarAdapter=new DrawerAdapter(drawarModelList, getApplicationContext(),new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (position==0){
                    //home
                    epoojatestfragment = new HomeFragment();
                    changeFragment(epoojatestfragment);

                }else if (position == 1){
                    //cart
                    epoojatestfragment = new CartFragment();
                    changeFragment(epoojatestfragment);

                } else if (position == 2) {
                   //fav
                    epoojatestfragment = new FavouriteFragment();
                    changeFragment(epoojatestfragment);

                }else if (position == 3){
                    //profile
                    epoojatestfragment = new ProfileFragment();
                    changeFragment(epoojatestfragment);

                }else {
                }
            }
        });
        recyclerView.setAdapter(drawarAdapter);

    }
    private void changeFragment(Fragment epooja) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelay,epooja);
        transaction.addToBackStack("tag");
        transaction.commit();
    }
}