package com.example.freshcarts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshcarts.adpter.CategoryAdapter;
import com.example.freshcarts.adpter.DiscounteProductAdpter;
import com.example.freshcarts.adpter.RecentlyViewedAdapter;
import com.example.freshcarts.model.Category;
import com.example.freshcarts.model.DiscountedProduct;
import com.example.freshcarts.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

import static com.example.freshcarts.R.drawable.b1;
import static com.example.freshcarts.R.drawable.b2;
import static com.example.freshcarts.R.drawable.b3;
import static com.example.freshcarts.R.drawable.b4;
import static com.example.freshcarts.R.drawable.card1;
import static com.example.freshcarts.R.drawable.card10;
import static com.example.freshcarts.R.drawable.card11;
import static com.example.freshcarts.R.drawable.card12;
import static com.example.freshcarts.R.drawable.card2;
import static com.example.freshcarts.R.drawable.card3;
import static com.example.freshcarts.R.drawable.card4;
import static com.example.freshcarts.R.drawable.card5;
import static com.example.freshcarts.R.drawable.card6;
import static com.example.freshcarts.R.drawable.card7;
import static com.example.freshcarts.R.drawable.card8;
import static com.example.freshcarts.R.drawable.card9;
import static com.example.freshcarts.R.drawable.discountberry;
import static com.example.freshcarts.R.drawable.discountboost;
import static com.example.freshcarts.R.drawable.discountbourn;
import static com.example.freshcarts.R.drawable.discountbrocoli;
import static com.example.freshcarts.R.drawable.discountmeat;
import static com.example.freshcarts.R.drawable.discounttatatea;
import static com.example.freshcarts.R.drawable.ic_home_cookies;
import static com.example.freshcarts.R.drawable.ic_home_dairy;
import static com.example.freshcarts.R.drawable.ic_home_drink;
import static com.example.freshcarts.R.drawable.ic_home_fruits;
import static com.example.freshcarts.R.drawable.ic_home_juce;
import static com.example.freshcarts.R.drawable.ic_home_spices;
import static com.example.freshcarts.R.drawable.ic_home_veggies;

public class Mainactivity3 extends AppCompatActivity  {


    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    DiscounteProductAdpter discountedProductAdapter;
    List<DiscountedProduct> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;
    TextView allCategory;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity3);




        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mainactivity3.this, AllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProduct(1, discountberry));
        discountedProductsList.add(new DiscountedProduct(2, discounttatatea));
        discountedProductsList.add(new DiscountedProduct(4, discountberry));
        discountedProductsList.add(new DiscountedProduct(5, discountbrocoli));
        discountedProductsList.add(new DiscountedProduct(4, discountberry));
        discountedProductsList.add(new DiscountedProduct(5, discountbourn));
        discountedProductsList.add(new DiscountedProduct(6, discountboost));

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, ic_home_fruits));
        categoryList.add(new Category(2, ic_home_cookies));
        categoryList.add(new Category(3, ic_home_dairy));
        categoryList.add(new Category(4, ic_home_drink));
        categoryList.add(new Category(5, ic_home_juce));
        categoryList.add(new Category(6, ic_home_spices));
        categoryList.add(new Category(8, ic_home_veggies));
        categoryList.add(new Category(4, ic_home_drink));
        categoryList.add(new Category(5, ic_home_juce));
        categoryList.add(new Category(6, ic_home_spices));
        categoryList.add(new Category(8, ic_home_veggies));



        // adding data to model
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 30", "1", "PC", card1, b1));

        recentlyViewedList.add(new RecentlyViewed("Strawberry", "Rich in nutrients, vitamins, minerals and antioxidants and fiber", "₹ 30", "1", "PC", card2, b2));

        recentlyViewedList.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "₹ 85", "1", "KG", card3, b3));

        recentlyViewedList.add(new RecentlyViewed( "Watermelon", "Watermelon has high water content and also provides some fiber.", "₹ 80", "1", "KG",card4,b4));

        recentlyViewedList.add(new RecentlyViewed( "Parle 20-20 Cookies", "A golden brown crispiness to melt in the mouth and leave you with the taste of perfection", "₹ 40", "1", "KG",card5,b4));

        recentlyViewedList.add(new RecentlyViewed("Maza", "this fruit the most popular one amongst every other mango drink.", "₹ 45", "1", "L",card6, b3));

        recentlyViewedList.add(new RecentlyViewed("garlic-sev murmura", "Gopal Namkeen Garlic Sev Murmura is an extremely tasty fun snack.", "₹ 30", "100", "G", card7, b3));

        recentlyViewedList.add(new RecentlyViewed("Tata Tea Gold", "the superior balance of rich taste and irresistible aroma with Tata Tea Gold", "₹ 483", "1", "KG", card8, b3));

        recentlyViewedList.add(new RecentlyViewed("Parachute oil", "Untouched by hand- goes through 27 quality tests and 5 stage purification process – for 100% purity every time", "₹ 224", "1", "Ml", card9, b2));

        recentlyViewedList.add(new RecentlyViewed("Basmati Rice", "Super quality\n" +
                "Long grain\n" + "World's largest rice miller\n", "₹ 169", "1", "KG", card10, b3));

        recentlyViewedList.add(new RecentlyViewed("Sports Nutrition drink", "Enriched with Envita Nutrients (Iron, Vitamin A, C, Folic Acid, B12 and B6)", "₹ 325", "750", "G", card11, b2));

        recentlyViewedList.add(new RecentlyViewed("Cream & Onion", "100 percent vegetarian, best quality potatoes, finest quality of spices and edible vegetable oi", "₹ 483", "52", "G", card12, b3));       recentlyViewedList.add(new RecentlyViewed("garlic-sev murmura", "Gopal Namkeen Garlic Sev Murmura is an extremely tasty fun snack.", "₹ 30", "100", "G", card7, b3));

        recentlyViewedList.add(new RecentlyViewed("Tata Tea Gold", "the superior balance of rich taste and irresistible aroma with Tata Tea Gold", "₹ 483", "1", "KG", card8, b3));

        recentlyViewedList.add(new RecentlyViewed("Parachute oil", "Untouched by hand- goes through 27 quality tests and 5 stage purification process – for 100% purity every time", "₹ 224", "1", "Ml", card9, b2));

        recentlyViewedList.add(new RecentlyViewed("Basmati Rice", "Super quality\n" +
                "Long grain\n" + "World's largest rice miller\n", "₹ 169", "1", "KG", card10, b3));

        recentlyViewedList.add(new RecentlyViewed("Sports Nutrition drink", "Enriched with Envita Nutrients (Iron, Vitamin A, C, Folic Acid, B12 and B6)", "₹ 325", "750", "G", card11, b2));

        recentlyViewedList.add(new RecentlyViewed("Cream & Onion", "100 percent vegetarian, best quality potatoes, finest quality of spices and edible vegetable oi", "₹ 483", "52", "G", card12, b3));



        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList);

    }


    private void setDiscountedRecycler(List<DiscountedProduct> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscounteProductAdpter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }


    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);

    //Now again we need to create a adapter and model class for recently viewed items.
    // lets do it fast.


    }
}




