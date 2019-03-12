package com.olih.culinaryrecipes.model;

import java.sql.Blob;

/**
 * Created by apple on 3/9/18.
 */

public class Food {
    private int id;
    private String name;
    private String intro;
    private String ingredient;
    private String make;
    private int photo;
    private int categoryId;

    public Food(){
    }

    public Food(String name, String intro, String ingredient, String make, int photo, int categoryId){
        this.name = name;
        this.intro = intro;
        this.ingredient = ingredient;
        this.make = make;
        this.photo = photo;
        this.categoryId = categoryId;
    }

    public Food(int id, String name, String intro, String ingredient, String make, int photo, int categoryId){
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.ingredient = ingredient;
        this.make = make;
        this.photo = photo;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
