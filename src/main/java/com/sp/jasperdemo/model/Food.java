package com.sp.jasperdemo.model;


public class Food {
    private String foodName;
    private String mealtime;
    private int fat;
    private int protein;
    private int carb;

    public Food() {
    }

    public Food(String foodName, String mealtime, int fat, int protein, int carb) {
        this.foodName = foodName;
        this.mealtime = mealtime;
        this.fat = fat;
        this.protein = protein;
        this.carb = carb;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getMealtime() {
        return mealtime;
    }

    public void setMealtime(String mealtime) {
        this.mealtime = mealtime;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }
}
