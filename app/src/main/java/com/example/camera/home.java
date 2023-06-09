package com.example.camera;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    ImageView profile, logout, cam,call,mail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.pro);
        logout = findViewById(R.id.logout);
        cam = findViewById(R.id.cam);
        call = findViewById(R.id.call_btn);
        mail = findViewById(R.id.mail);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

//      using image sliding feature
        slideModels.add(new SlideModel(R.drawable.img_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this, user_info.class);
                startActivity(i);
                finish();
            }
        });

//      using the camera feature to report the garbage feature
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

//      Adding a clling feature to the app using which the use can call to register the complaints
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(home.this,"Ok btn pressed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1929"));
                startActivity(intent);
            }
        });

//      Adding a mailing feature to the app using which user can report the grivances
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"grievance@bmc.gov.in"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Garbage Complaint");
                intent.putExtra(Intent.EXTRA_TEXT, "***Type your Complaint below**");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, login.class);
                startActivity(i);
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(home.this,"Sucesfully Loged Out!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}