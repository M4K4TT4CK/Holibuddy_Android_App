package com.mikem.vacationapp.UI;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikem.vacationapp.R;

import java.util.Objects;

// Design the application to include the following information, including appropriate GUI (graphical user interface) elements (e.g., navigation, input, and information) for each layout
public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide action bar
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        //animation for textview
        TextView holiText = findViewById(R.id.textView);
        ObjectAnimator animator = ObjectAnimator.ofFloat(holiText, "rotation", 0, 360);
        animator.setDuration(500);
        animator.start();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VacationList.class);
            startActivity(intent);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
}