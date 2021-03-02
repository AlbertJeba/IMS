package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import static com.example.ims.R.anim.top_animation;
import static com.example.ims.R.anim.bottom_anim;

public class splash_screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;

    //variables
    Animation topanim, bottomanim;
    ImageView image;
    TextView title, owner;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        //Animation

        topanim = AnimationUtils.loadAnimation(this,top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,bottom_anim);

        //Hooks
        image = findViewById(R.id.imageView);
        title = findViewById(R.id.textView);
       // owner = findViewById(R.id.owner);
        progressBar = findViewById(R.id.progressBar);

        image.setAnimation(topanim);
        title.setAnimation(bottomanim);
        progressBar.setAnimation(bottomanim);
        //owner.setAnimation(bottomanim);

        image.setAnimation(topanim);
        title.setAnimation(bottomanim);
        progressBar.setAnimation(bottomanim);
        //owner.setAnimation(bottomanim);
        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {

                    startActivity(new Intent(getApplicationContext(), login_page.class));
                    finish();


            }
        },SPLASH_SCREEN);
    }
    }
