package com.cruzer.healthify;

public class FoodItem {
    private String foodName;
    private int caloriesPerServing;
    private int numServings;

    public FoodItem(String name, int calories, int servings){
        foodName = name;
        caloriesPerServing = calories;
        numServings = servings;
    }

    public String getFoodName(){
        return foodName;
    }

    public int getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public int getNumServings() {
        return numServings;
    }
}
