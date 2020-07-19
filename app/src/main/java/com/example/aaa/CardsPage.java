package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardsPage extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static final int PICK_IMAGE1 = 100;
    static int PReqCode = 1;
    Uri imageUri;
    Uri imageUri1;
    ImageView change1;
    ImageView change;
    ImageView back;
    ImageView front;
    static int REQUESCODE = 1;
    private int requestCode;
    private int resultCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_page);
        change = findViewById(R.id.imageView53);
        change1 = findViewById(R.id.imageView52);
        back = findViewById(R.id.imageView42);
        front = findViewById(R.id.imageView51);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery1();
            }
        });





        ImageView Details = findViewById(R.id.imageView57);
        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(CardsPage.this , More_Details.class);
                startActivity(details);
            }
        });
        TextView back = findViewById(R.id.textView57);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(CardsPage.this , SectionsPage.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
        ImageView back1 = findViewById(R.id.imageView70);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(CardsPage.this , SectionsPage.class);
                startActivity(back1);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }

    private  void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery , PICK_IMAGE);
    }

    private void openGallery1() {
        //TODO Open Gallery Intent and wait for user to choose a picture

        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("Image/*");
        startActivityForResult(gallery,REQUESCODE);
        checkAndRequestForPermission();

    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode , resultCode , data );

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            // the user has successfully picked a picture
            // we need to save its reference to a uri variable

            imageUri1 = data.getData() ;
            front.setImageURI(imageUri1);
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            back.setImageURI(imageUri);
        }
    }

    private void checkAndRequestForPermission() {


        if(ContextCompat.checkSelfPermission(CardsPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(CardsPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                android.widget.Toast.makeText(CardsPage.this, "You Need To Accept To The Permission In Order Of Entering A Picture", Toast.LENGTH_LONG).show();
            }
            else {
                ActivityCompat.requestPermissions(CardsPage.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }
        } else {

            openGallery();

        }

    }


}