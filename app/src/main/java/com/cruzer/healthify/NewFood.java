package com.cruzer.healthify;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class NewFood extends AppCompatActivity {
    Gson gson = new Gson();
    EditText foodName;
    EditText caloriesPerServing;
    EditText numServings;
    SharedPreferences mPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        mPref = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        editor = mPref.edit();
    }

    public void submitFood(View view){
        foodName = findViewById(R.id.textFoodName);
        caloriesPerServing = findViewById(R.id.textCalories);
        numServings = findViewById(R.id.textServings);
        FoodItem newItem = new FoodItem(foodName.getText().toString(), Integer.valueOf(caloriesPerServing.getText().toString()), Integer.valueOf(numServings.getText().toString()));
        String json = gson.toJson(newItem);
        editor.putString("NEW_FOOD", json);
        editor.apply();
        setResult(RESULT_OK);
        finish();
    }
}

