package com.olih.culinaryrecipes.model;

/**
 * Created by apple on 3/9/18.
 */

public class Favorite {

    private int id;
    private int foodId;

    public Favorite(){
    }

    public Favorite(int foodId){
        this.foodId = foodId;
    }

    public Favorite(int id, int foodId){
        this.id = id;
        this.foodId = foodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
