package com.example.freshcarts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ProfileEditUserActivity extends AppCompatActivity {
private ImageButton backBtn;
private ImageView profileIV;
private EditText fullName_E_Profile,userNameET,emailET,phone_numberET,cityET;
private Button updateBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_user);


        backBtn= findViewById(R.id.backBtn1);
        profileIV= findViewById(R.id.profileIV);
        fullName_E_Profile = findViewById(R.id.fullName_E_Profile);
        userNameET = findViewById(R.id.userNameET);
        phone_numberET = findViewById(R.id.phone_numberET);
        cityET = findViewById(R.id.cityET);
        updateBT = findViewById(R.id.updateBT);




profileIV.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ImagePicker.Companion.with(this)
                .crop()
                .cropOval()
                .maxResultSize(512, 512, true)
                .createIntentFromDialog((Function1) (new Function1() {
                    public Object invoke(Object var1) {
                        this.invoke((Intent) var1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull Intent it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        launcher.launch(it);
                    }
                }));

    }
});

backBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    });
updateBT.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});






}}


