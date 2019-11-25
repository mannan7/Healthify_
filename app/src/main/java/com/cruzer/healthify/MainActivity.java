package com.cruzer.healthify;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //SharedPreferences sharedPreferences;

    SharedPreferences mPrefs;

    SharedPreferences.Editor editor;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtWeight;
    EditText txtHeight;
    EditText txtAge;
    String txtGender;
    int intGoal;
    boolean metric;
    Switch metricSwitch;
    Spinner goal;
    Spinner dropdown;

    Button introBtn;
    ImageView introImg;
    Button submitBtn;

    // Button button5;

    public void begin(View view) {

        introBtn.setVisibility(View.GONE);
        introImg.setVisibility(View.GONE);
        submitBtn.setVisibility(View.VISIBLE);


    }

    public void button5(View view) {

        Intent intent2 = new Intent(getApplicationContext(), StepCounter.class);
        startActivity(intent2);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (dropdown.getSelectedItem() != null) {
            if (dropdown.getSelectedItem().toString() == "Male") {
                txtGender = "Male";
            } else {
                txtGender = "Female";
            }
        }
        if (goal.getSelectedItem() != null){
            if (goal.getSelectedItem().toString() == "Maintain Weight"){
                intGoal = 1;
            }
            else if (goal.getSelectedItem().toString() == "Gain Weight"){
                intGoal = 3;
            }
            else{
                intGoal = 2;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        txtGender = "Male";
    }

    public void toSecondActivity(View view) {

        Intent intent = new Intent(getApplicationContext(), HowActive.class);


        //sharedPreferences.edit().putString("firstName", String.valueOf(txtFirstName)).apply();
        //Expanded out the above to make it more readable in code
        mPrefs = this.getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        //initialized the editor using the above sharedPreferences variable
        editor = mPrefs.edit();
        //add the user information into SharedPreferences for use throughout the app
        editor.putString("FIRST_NAME", txtFirstName.getText().toString());
        editor.putString("LAST_NAME", txtLastName.getText().toString());
        editor.putInt("WEIGHT", Integer.valueOf(txtWeight.getText().toString()));
        editor.putInt("HEIGHT", Integer.valueOf(txtHeight.getText().toString()));
        editor.putInt("AGE", Integer.valueOf(txtAge.getText().toString()));
        editor.putString("GENDER", txtGender);
        editor.putBoolean("METRIC", metric);
        editor.putInt("GOAL", intGoal);

        //Apply the changes made to SharedPreferences
        editor.apply();

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = this.getSharedPreferences("android.content.SharedPreferences", MODE_PRIVATE);
        if (mPrefs.getString("FIRST_NAME", null) != null){
            if (mPrefs.getString("LAST_NAME", null) != null){
                if (mPrefs.getInt("WEIGHT", 0) != 0){
                    if (mPrefs.getInt("HEIGHT", 0) != 0){
                        if (mPrefs.getInt("AGE", 0) != 0){
                            if (mPrefs.getString("GENDER", null) != null){
                                if (mPrefs.getFloat("ACTIVITY_FACTOR", -1) == -1){
                                    Intent intent1 = new Intent(getApplicationContext(), HowActive.class);
                                    startActivity(intent1);
                                }
                                else {
                                    Intent intent2 = new Intent(getApplicationContext(), RecommendedCaloricIntake.class);
                                    startActivity(intent2);
                                }
                            }
                        }
                    }
                }
            }
        }

        metric = false;
        metricSwitch = findViewById(R.id.switch1);

        metricSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked){
                    metric = true;
                    txtWeight.setHint("Weight (kgs)");
                    txtHeight.setHint("Height (cm)");
                }
                else{
                    metric = false;
                    txtWeight.setHint("Weight (lbs)");
                    txtHeight.setHint("Height (inches)");
                }
            }
        });

        txtFirstName = findViewById(R.id.textFirstName);
        txtLastName = findViewById(R.id.textLastName);
        txtWeight = findViewById(R.id.textWeight);
        txtHeight = findViewById(R.id.textHeight);
        txtAge = findViewById(R.id.textAge);
        // button5 = findViewById(R.id.button5);

        introBtn = findViewById(R.id.introButton);
        introImg = findViewById(R.id.introPic);
        submitBtn = findViewById(R.id.button2);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner1);
        goal = findViewById(R.id.spinner2);
        //create a list of items for the spinner.
        String[] items = new String[]{"Male", "Female"};
        String[] items2 = new String[]{"Maintain Weight", "Lose Weight", "Gain Weight"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        goal.setAdapter(adapter2);
        goal.setOnItemSelectedListener(this);
    }
}