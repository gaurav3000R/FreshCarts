package com.example.freshcart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.freshcart2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ImageView iconeIV;
    TextView iconText;
    Animation leftAnimation, bottomAnimation, topAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        iconeIV = findViewById(R.id.iconIv);
//        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_animation);
//        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user == null){
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    Pair[] pairs = new Pair[2];
//                    pairs[0] = new Pair<View, String>(iconeIV, "logo_image");
//                    pairs[1] = new Pair<View, String>(iconText, "logo_text");
//
//
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pairs);
//                        startActivity(intent, options.toBundle());
//                    }
                    startActivity(intent);
                    finish();
                }
                else {
                    checkUserType();
                }
            }
        }, 2500);
    }

    private void checkUserType() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot ds : dataSnapshot.getChildren()){}
                        String accountType = ""+dataSnapshot.child("accountType").getValue();
                            if(accountType.equals("Seller")){
                                startActivity(new Intent(SplashActivity.this, MainSellerActivity.class));
                                finish();
                            }
                            else{
                                startActivity(new Intent(SplashActivity.this, MainBuyerActivity.class));
                                finish();
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}



///////////////////////////////////////////////////////////
//package com.example.freshcart2;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.Window;
//        import android.view.WindowManager;
//
//public class SplashActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        Thread td =new  Thread(){
//            public void run(){            try {sleep(4000);
//
//            }
//            catch (Exception ex){
//                ex.printStackTrace();
//            }
//
//            finally {
//                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//            }
//        };td.start();





//
//
//    }
//}
//
