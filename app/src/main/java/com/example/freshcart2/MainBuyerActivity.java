package com.example.freshcart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.freshcart2.adapters.AdapterOrderUser;
import com.example.freshcart2.adapters.AdapterShop;
import com.example.freshcart2.models.ModelShop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainBuyerActivity extends AppCompatActivity {

    private TextView nameTv, emailTv, phoneTv, tabShopsTv, tabOrdersTv;
    private ImageButton logoutBtn, editProfileBtn, cartBtn,locationBtn;
    private RelativeLayout shopRl, ordersRl;
    private ImageView profileIv;
    private RecyclerView shopRv, orderRv;
    FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    private ArrayList<com.example.freshcart2.models.ModelOrderUser> orderList;
    private AdapterOrderUser adapterOrderUser;


    private ArrayList<ModelShop> shopList;
    private AdapterShop adapterShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buyer);

        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        checkUser();

        nameTv = findViewById(R.id.nameTV);
        emailTv = findViewById(R.id.emailTV);
        phoneTv = findViewById(R.id.phoneTV);
        tabShopsTv = findViewById(R.id.tabShopsTV);
        tabOrdersTv = findViewById(R.id.tabOrdersTV);
        logoutBtn = findViewById(R.id.logoutBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        cartBtn = findViewById(R.id.cartBtn);
        shopRl = findViewById(R.id.shopRL);
        ordersRl = findViewById(R.id.ordersRL);
        profileIv = findViewById(R.id.profileIV);
        shopRv = findViewById(R.id.shopRV);
        orderRv = findViewById(R.id.orderRv);
        locationBtn = findViewById(R.id.locationBtn);

        showShopUI();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                checkUser();
            }
        });

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainBuyerActivity.this, com.example.freshcart2.ProfileEditBuyerActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tabShopsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShopUI();
            }
        });

        tabOrdersTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrdersUI();
            }
        });
        
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainBuyerActivity.this);
                builder.setTitle("Choose Area:")
                        .setItems(Constants.shopCategories, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String selected = Constants.shopCategories[which];
//                                locationBtn.(selected);
                                if (selected.equals("All")){
//                                    loadShopProducts();
                                }
                                else {
//                                    adapterProductBuyer.getFilter().filter(selected);
                                }
                            }
                        }).show();
            }
        });
    }

    private void showShopUI() {
        shopRl.setVisibility(View.VISIBLE);
        ordersRl.setVisibility(View.GONE);

        tabShopsTv.setTextColor(getResources().getColor(R.color.colorBlack));
        tabShopsTv.setBackgroundResource(R.drawable.shap_rect04);

        tabOrdersTv.setTextColor(getResources().getColor(R.color.colorWhite));
        tabOrdersTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    private void showOrdersUI() {
        shopRl.setVisibility(View.GONE);
        ordersRl.setVisibility(View.VISIBLE);

        tabOrdersTv.setTextColor(getResources().getColor(R.color.colorBlack));
        tabOrdersTv.setBackgroundResource(R.drawable.shap_rect04);

        tabShopsTv.setTextColor(getResources().getColor(R.color.colorWhite));
        tabShopsTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    private void checkUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainBuyerActivity.this, com.example.freshcart2.LoginActivity.class));
            finish();;
        }
        else {
            loadMyInfo();
        }
    }

    private void loadMyInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("uid").equalTo(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            String name = ""+ds.child("name").getValue();
                            String email = ""+ds.child("email").getValue();
                            String phone = ""+ds.child("phone").getValue();
                            String profileImage = ""+ds.child("profileImage").getValue();
                            String accountType = ""+ds.child("accountType").getValue();
                            String city = ""+ds.child("city").getValue();

                            nameTv.setText(name);
                            emailTv.setText(email);
                            phoneTv.setText(phone);
                            try {
                                Picasso.get().load(profileImage).placeholder(R.drawable.ic_person_gray).into(profileIv);
                            }
                            catch (Exception e) {
                                profileIv.setImageResource(R.drawable.ic_person_gray);
                            }
                            loadShop(city);
                            loadOrders();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void loadOrders() {
        orderList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String uid = ""+ds.getRef().getKey();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Orders");
                    ref.orderByChild("orderBy").equalTo(mAuth.getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()){
                                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                                            com.example.freshcart2.models.ModelOrderUser modelOrderUser = ds.getValue(com.example.freshcart2.models.ModelOrderUser.class);

                                            orderList.add(modelOrderUser);
                                        }
                                        adapterOrderUser = new AdapterOrderUser(MainBuyerActivity.this, orderList);
                                        orderRv.setAdapter(adapterOrderUser);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadShop(final String myCity) {

        shopList = new ArrayList<com.example.freshcart2.models.ModelShop>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("accountType").equalTo("Seller")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        shopList.clear();
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            com.example.freshcart2.models.ModelShop modelShop = ds.getValue(com.example.freshcart2.models.ModelShop.class);

//                            String shopCity = ""+ds.child("city").getValue();

//                            if (shopCity.equals(myCity)){
//                                shopList.add(modelShop);
                            shopList.add(modelShop);
//                            }
                        }

                        adapterShop = new AdapterShop(MainBuyerActivity.this,shopList);
                        shopRv.setAdapter(adapterShop);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
