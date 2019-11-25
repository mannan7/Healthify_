package com.cruzer.healthify;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HowActive extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<String> list;
    TextView activeText;
    Spinner dropdown;
    String tmp;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    Intent intent;
    float actFac;

    ImageView sedRun;
    ImageView lightRun;
    ImageView activeRun;

    /**
     public void activeInfo(View view){
     l

     if (dropdown.getSelectedItem().toString() == "Sedentary"){

     activeText.setText("You do not move much huh?");
     Toast.makeText(this, "SEDENTARY", Toast.LENGTH_SHORT).show();
     }

     if (dropdown.getSelectedItem().toString() == "Lightly Active"){

     activeText.setText("You move a bit");
     Toast.makeText(this, "LIGHTLY ACTIVE", Toast.LENGTH_SHORT).show();

     }

     if (dropdown.getSelectedItem().toString() == "Active"){

     activeText.setText("You wouldn't be using this app");
     Toast.makeText(this, "ACTIVE", Toast.LENGTH_SHORT).show();
     }
     }
     **/

    public void toNextActivity(View view){
        intent = new Intent(this, RecommendedCaloricIntake.class);
        if (dropdown.getSelectedItem().toString() == "Sedentary"){
            actFac = (float) 1.2;
        }
        else if (dropdown.getSelectedItem().toString() == "Lightly Active"){
            actFac = (float) 1.3;
        }
        else{
            actFac = (float) 1.4;
        }

        editor = mPrefs.edit();
        editor.putFloat("ACTIVITY_FACTOR",actFac);
        editor.apply();

        startActivity(intent);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        if (dropdown.getSelectedItem().toString() == "Sedentary"){

            tmp = "The average person is sedentary. Anyone that spends less than 30 minutes per day intentionally exercising, is considered sedentary. \n"+
                    "Some of your activities may include working at your desk, walking your dog, shopping at Publix.";
            activeText.setText(tmp);

            sedRun.setVisibility(View.VISIBLE);
            lightRun.setVisibility(View.INVISIBLE);
            activeRun.setVisibility(View.INVISIBLE);

            // Toast.makeText(this, "SEDENTARY", Toast.LENGTH_SHORT).show();
        }

        if (dropdown.getSelectedItem().toString() == "Lightly Active"){

            tmp ="A user that spends at least 30 minutes or more intentionally exercising.\n" +
                    "Your activities may include walking the dog, mowing the lawn, gardening, being a car sales man, or even just jogging around the block.";

            activeText.setText(tmp);

            sedRun.setVisibility(View.INVISIBLE);
            lightRun.setVisibility(View.VISIBLE);
            activeRun.setVisibility(View.INVISIBLE);

            //  Toast.makeText(this, "LIGHTLY ACTIVE", Toast.LENGTH_SHORT).show();

        }

        if (dropdown.getSelectedItem().toString() == "Active"){

            tmp ="A user that spends 1.5-2 hours out of their day intentionally exercising. \n" +
                    "Their normal daily activities may include waiting tables, mowing the lawn, or walking their dog. However most Active users set aside an extra 1-2 hours a day just for exercise.";
            activeText.setText(tmp);
            // Toast.makeText(this, "ACTIVE", Toast.LENGTH_SHORT).show();

            sedRun.setVisibility(View.INVISIBLE);
            lightRun.setVisibility(View.INVISIBLE);
            activeRun.setVisibility(View.VISIBLE);
        }

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_active);

        activeText = findViewById(R.id.activeText);


        //get the spinner from the xml.
        dropdown = findViewById(R.id.activeSpinner);

        String[] choices = new String[]{"Sedentary", "Lightly Active", "Active"};
        list = new ArrayList<>(Arrays.asList(choices));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        //images
        sedRun = (ImageView) findViewById(R.id.sedRun);
        lightRun = (ImageView) findViewById(R.id.lightRun);
        activeRun = (ImageView) findViewById(R.id.activeRun);

        mPrefs = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        String fName = mPrefs.getString("FIRST_NAME", "noData");
        String lName = mPrefs.getString("LAST_NAME", "noData");
        Integer weight = mPrefs.getInt("WEIGHT", 0);
        Integer height = mPrefs.getInt("HEIGHT", 0);
        Integer age = mPrefs.getInt("AGE", 0);
        String gender = mPrefs.getString("GENDER", "noData");
    }
}
