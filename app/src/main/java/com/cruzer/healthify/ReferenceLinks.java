package com.cruzer.healthify;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class ReferenceLinks extends AppCompatActivity {

    TextView link1;
    TextView link2;
    TextView link3;
    String tmpStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference_links);

        link1 = findViewById(R.id.link1);
        link2 = findViewById(R.id.link2);
        link3 = findViewById(R.id.link3);

        tmpStr = "20 superfoods to help with weight loss:\nhttps://www.healthline.com/nutrition/20-most-weight-loss-friendly-foods";
        link1.setText(tmpStr);
        tmpStr = "Healthy exercises for men:\nhttps://www.menshealth.com/fitness/a19516747/the-best-exercises-for-men/";
        link2.setText(tmpStr);
        tmpStr = "Healthy exercises for women:\nhttps://www.womenshealthmag.com/fitness/a20052492/best-workout-for-women-0/";
        link3.setText(tmpStr);

        Linkify.addLinks(link1, Linkify.ALL);
        Linkify.addLinks(link2, Linkify.ALL);
        Linkify.addLinks(link3, Linkify.ALL);
    }

    public void backButton(View view){
        finish();
    }
}
