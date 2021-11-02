package com.example.freshcarts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.freshcarts.ui.login.LoginActivityfrash;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    ImageView profileIV;
    Button login_, register_;
    TextInputLayout fullnamesign_var, emailsign_var, phonenumber_var, passwordsign_var,username_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth fauth;


//permission constants
    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=200;

    //IMAGE PICK CONSTANS

    private static final int IMAGE_PICK_GALLERY_CODE=400;
    private static final int IMAGE_PICK_CAMERA_CODE=500;
    //PERMISSION ARRAYS
private String[] cameraPermissions;
private String[] storagePermissions;




//image picked uri
    private Uri image_uri;
    private Object Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        register_ = findViewById(R.id.Registersignup);
        profileIV = findViewById(R.id.profileIV);
        login_ = findViewById(R.id.loginbtn1);
        fullnamesign_var = findViewById(R.id.Fullname_signup);
        username_var = findViewById(R.id.username_signup);
        emailsign_var = findViewById(R.id.email_signup_field);
        passwordsign_var = findViewById(R.id.password_singup_field);
        phonenumber_var = findViewById(R.id.phone_number_signup_field);
        fauth = FirebaseAuth.getInstance();


        //INIT PERMISSIONS ARRAY
        cameraPermissions= new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        register_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname_ = fullnamesign_var.getEditText().getText().toString();
                String username_ = username_var.getEditText().getText().toString();
                String email_ = emailsign_var.getEditText().getText().toString();
                String password_ = passwordsign_var.getEditText().getText().toString();
                String phonenumber_ = phonenumber_var.getEditText().getText().toString();


                if (!fullname_.isEmpty()) {
                    fullnamesign_var.setError(null);
                    fullnamesign_var.setErrorEnabled(false);
                } else {
                    fullnamesign_var.setError("Enter full name");
                }

                 if (!username_.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                } else {
                    username_var.setError("Enter User Name");
                }


                if (!phonenumber_.isEmpty()) {
                    phonenumber_var.setError(null);
                    phonenumber_var.setErrorEnabled(false);
                } else {
                    phonenumber_var.setError("Enter phone number");
                }


                if (!password_.isEmpty()) {
                    passwordsign_var.setError(null);
                    passwordsign_var.setErrorEnabled(false);
                } else {
                    passwordsign_var.setError("Enter password");
                }


                if (email_.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
                    emailsign_var.setError(null);
                    emailsign_var.setErrorEnabled(false);

                } else {
                    emailsign_var.setError("Please enter valid email-id");
                }


                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("datauser");


                String fullname_s = fullnamesign_var.getEditText().getText().toString();
                String username_s=username_var.getEditText().getText().toString();
                String email_s = emailsign_var.getEditText().getText().toString();
                String password_s = passwordsign_var.getEditText().getText().toString();
                String phonenumber_s = phonenumber_var.getEditText().getText().toString();

                storing_data storing_data = new storing_data(fullname_s,username_s, email_s, password_s, phonenumber_s);
                reference.child(fullname_s).setValue(storing_data);



                profileIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showImagePickDialog();
                    }
                });
                }


        });


//                Toast.makeText(getApplicationContext(),"registeration successfull",Toast.LENGTH_SHORT).show();
//
//
//                fauth.createUserWithEmailAndPassword(email_,password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(register_.this,"user created" , Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),Mainactivity3.class));
//
//                        }else {Toast.makeText(register_.this,"errror !!!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                })
////





    }

    private void showImagePickDialog() {

        String[]option={"Camera","Gallery"};
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Pick Image")
                .setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {

                            if  (checkCameraPermission()) {
                                pickFromCamera();

                        } else {requestCameraPermission();


                            } }else {

                            if (checkStoragePermission()) {
                                pickFromGallery();

                            } else {
                                requestStoragePermission();
                            }
                        }
                        }
                }).show();
    }


private void pickFromGallery() {

//    Intent intent = new Intent(Intent);
//    intent.setType("image/*");



//    startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);

}



private void pickFromCamera() {
    ContentValues contentValues = new ContentValues();
    contentValues.put(MediaStore.Images.Media.TITLE, "Temp_Image_Title");
    contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp_Image_description");
    image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
    startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);

}
///////////////////////////////////////////////////////



    private void requestStoragePermission(){
    ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);

}




private boolean checkCameraPermission(){
        boolean result= ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);

    return result && result1;
    }
private boolean checkStoragePermission(){
        boolean result= ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);

    return result && result1;
    }




    private void requestCameraPermission(){
     ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }




//        private void setImagePickCamera(){
//    ContentValues contentValues= new ContentValues();
//    contentValues.put{
//       MediaStore.Video.MediaStore.
//    }
//}


    public void login(View view) {

                Intent intent = new Intent(signup.this, LoginActivityfrash.class);
                startActivity(intent);




    }
}