package com.olih.culinaryrecipes.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.olih.culinaryrecipes.model.Category;
import com.olih.culinaryrecipes.model.Favorite;
import com.olih.culinaryrecipes.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 09/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BudidayaLeleDB";

    //table names
    public static final String TABLE_FOOD = "food";
    public static final String TABLE_FAVORITE = "favorite";
    public static final String TABLE_CATEGORY = "category";

    //common column names
    public static final String KEY_ID = "id";

    //Food Table - column names
    public static final String KEY_NAME = "name";
    public static final String KEY_INTRO = "intro";
    public static final String KEY_INGREDIENT = "ingredient";
    public static final String KEY_MAKE = "make";
    public static final String KEY_IMAGE = "photo";
    public static final String KEY_CATEGORY_FK = "categoryId";

    //Favorite Table - column names
    public static final String KEY_FOOD_FK = "foodId";

    //Category Table - column names
    public static final String KEY_CATEGORY_NAME = "category";

    //Table Create Statements
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + KEY_NAME + " TEXT, " + KEY_INTRO + " TEXT, " + KEY_INGREDIENT
            + " TEXT, " + KEY_MAKE + " TEXT, " + KEY_IMAGE + " INTEGER,"
            + KEY_CATEGORY_FK + " INTEGER NOT NULL" + ")";

    private static final String CREATE_TABLE_FAVORITE = "CREATE TABLE "
            + TABLE_FAVORITE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FOOD_FK + " INTEGER NOT NULL" + ")";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CATEGORY_NAME + " VARCHAR NOT NULL" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating required tables
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_FAVORITE);
        db.execSQL(CREATE_TABLE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);

        //create new tables
        onCreate(db);
    }

    //"Information" table methods

//    public long createInformation(Information information){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, information.getTitle());
//        values.put(KEY_IMAGE, information.getImage());
//        values.put(KEY_DESCRIPTION, information.getDescription());
//
//        //insert row
//        long information_id = db.insert(TABLE_INFORMATION, null, values);
//
//        return information_id;
//    }
//
//    public Information getInformation(long information_id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT * FROM " + TABLE_INFORMATION + " WHERE "
//                + KEY_ID + " = " + information_id;
//
//        Log.e(LOG, selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c != null)
//            c.moveToFirst();
//
//            Information information = new Information();
//            information.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//            information.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
//            information.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
//            information.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
//
//            return information;
//    }
//
//    public List<Information> getAllInformation(){
//        List<Information> informations = new ArrayList<Information>();
//        String selectQuery = "SELECT * FROM " + TABLE_INFORMATION;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        //looping through all rows and adding to list
//        if (c.moveToFirst()){
//            do {
//                Information information = new Information();
//                information.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//                information.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
//                information.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
//                information.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
//
//                //adding to information list
//                informations.add(information);
//            }while (c.moveToNext());
//        }
//
//        return informations;
//    }
//
//    //delete information
//    public void deleteInformation(long information_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_INFORMATION, KEY_ID + " = ?",
//                new String[]{String.valueOf(information_id)});
//    }
//
//
//    public int getInformationCount(){
//        String countQuery = "SELECT * FROM " + TABLE_INFORMATION;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//        return count;
//    }

    public long createCategory(List<Category> categories){

        SQLiteDatabase db = this.getWritableDatabase();
        long category_id = 1;

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            for (Category category : categories) {
                values.put(KEY_CATEGORY_NAME, category.getCategoryName());
                category_id = db.insert(TABLE_CATEGORY, null, values);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            return category_id;
        }
    }


    //"food" table methods

    public long createFood(List<Food> foods){

//            for (Food food6 : foods) {
//                int foodId = food6.getId();
//                String name = food6.getName();
//                String intro = food6.getIntro();
//                String ingredient = food6.getIngredient();
//                String make = food6.getMake();
//                int image = food6.getPhoto();
//                food_id = db.insert(TABLE_FOOD, null, values);
//                Log.e("DatabaseHelper", "id :"+foodId +", name : " + name+", intro :"+intro);
//            }

        SQLiteDatabase db = this.getWritableDatabase();
        long food_id = 1;

        db.beginTransaction();

//        SQLiteDatabase db = this.getWritableDatabase();
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            for (Cities city : list) {
//                values.put(CityId, city.getCityid());
//                values.put(CityName, city.getCityName());
//                db.insert(TABLE_CITY, null, values);
//            }
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
        try {
            ContentValues values = new ContentValues();
            for (Food food : foods) {
//                ContentValues values = new ContentValues();
//                values.put(KEY_ID, food.getId());
                values.put(KEY_NAME, food.getName());
                values.put(KEY_INTRO, food.getIntro());
                values.put(KEY_INGREDIENT, food.getIngredient());
                values.put(KEY_MAKE, food.getMake());
                values.put(KEY_IMAGE, food.getPhoto());
                values.put(KEY_CATEGORY_FK, food.getCategoryId());
                food_id = db.insert(TABLE_FOOD, null, values);

            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            return food_id;
        }
    }

    public long createFavorite(int foodId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FOOD_FK, foodId);

        //insert row
        long favorite_id = db.insert(TABLE_FAVORITE, null, values);

        return favorite_id;
    }


//    public long createPond(Pond pond, long[] progress1_id, long[] progress2_id,
//                           long[] progress3_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, pond.getName());
//        values.put(KEY_SEED_AMOUNT, pond.getSeed_amount());
//        values.put(KEY_DATE, pond.getDate());
//
//        //insert row
//        long pond_id = db.insert(TABLE_POND, null, values);
//
//        //insert progress_ids
//        for(long progress_id : progress1_id){
//            createPondProgress(pond_id, progress_id);
//        }
//        for(long progress_id : progress2_id){
//            createPondProgress(pond_id, progress_id);
//        }
//        for(long progress_id : progress3_id){
//            createPondProgress(pond_id, progress_id);
//        }
//
//        return pond_id;
//    }


    public int getCategoryCount(){
        String countQuery = "SELECT * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }


    public Food getFood(long food_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " WHERE "
                + KEY_ID + " = " + food_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Food food = new Food();
        food.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        food.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        food.setIntro(c.getString(c.getColumnIndex(KEY_INTRO)));
        food.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT)));
        food.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
        food.setPhoto(c.getInt(c.getColumnIndex(KEY_IMAGE)));

        return food;
    }

//    public long getLong( Cursor cursor, int columnIndex )
//    {
//        long value = 0;
//        try
//        {
//            if ( !cursor.isNull( columnIndex ) )
//            {
//                value = cursor.getLong( columnIndex );
//            }
//        }
//        catch ( Throwable tr )
//        {
////            TRLogger.innerErrorLog( “[c] — “ + columnIndex, tr );
//        }
//        return value;
//    }

    public List<Food> getAllCategory(String category){
        List<Food> foods = new ArrayList<Food>();

        String selectQuery = "";

        if (category.equals(Constants.CATEGORY[0])) {
            selectQuery = "SELECT id, name, intro, ingredient, make, photo, categoryId FROM food WHERE food.categoryId = 1";
        }else if(category.equals(Constants.CATEGORY[1])) {
            selectQuery = "SELECT id, name, intro, ingredient, make, photo, categoryId FROM food WHERE food.categoryId = 2";
        }else if (category.equals(Constants.CATEGORY[2])) {
            selectQuery = "SELECT id, name, intro, ingredient, make, photo, categoryId FROM food WHERE food.categoryId = 3";
//            selectQuery = "SELECT food.id, food.name, food.intro, food.ingredient, " +
//                    "category.category, food.make, food.photo FROM food JOIN category ON " +
//                    "category.id = 1";
//                    "WHERE category LIKE " + Constants.CATEGORY[2];
        }

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c.moveToFirst()){
            do {
                Food food = new Food();
                food.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                food.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                food.setIntro(c.getString(c.getColumnIndex(KEY_INTRO)));
                food.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT)));
                food.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
                food.setPhoto(c.getInt(c.getColumnIndex(KEY_IMAGE)));
                food.setCategoryId(c.getColumnIndex(KEY_CATEGORY_FK));
//                favorit.setFoodId(c.getInt(c.getColumnIndex(KEY_FOOD_FK)));

                //adding to information list
                foods.add(food);
            }while (c.moveToNext());
        }

        return foods;
    }

    public List<Food> getAllFood(){
        List<Food> foods = new ArrayList<Food>();
        String selectQuery = "SELECT id, name, intro, ingredient, make, photo FROM " + TABLE_FOOD;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Food food = new Food();
//                    food.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//                    food.setName(c.getString(c.getColumnIndex(KEY_NAME)));
//                    food.setIntro(c.getString(c.getColumnIndex(KEY_INTRO)));
//                    food.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT)));
//                    food.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
//                    food.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
                    food.setId(c.getInt(0));
                    food.setName(c.getString(1));
                    food.setIntro(c.getString(2));
                    food.setIngredient(c.getString(3));
                    food.setMake(c.getString(4));
                    food.setPhoto(c.getInt(5));

                    //adding to information list
                    foods.add(food);
                } while (c.moveToNext());
            }
        }

        return foods;
    }

    public List<Food> getAllFilterFood(String foodName){
        List<Food> foods = new ArrayList<Food>();
//        String selectQuery = "SELECT * FROM food WHERE name = ?";
        String selectQuery = "SELECT * FROM food WHERE name like "+'%'+foodName+'%';

//        return new CursorLoader(this,
//                HeroesContract.HeroesEntry.CONTENT_URI,
//                projection,
//                selection,
//                selectionArgs,
//                null);


//        String[] projection = {
//                HeroesContract.HeroesEntry._ID,
//                HeroesContract.HeroesEntry.COLUMN_HEROES_NAME,
//                HeroesContract.HeroesEntry.COLUMN_HEROES_DESCRIPTION,
//                HeroesContract.HeroesEntry.COLUMN_HEROES_IMAGE};
//        String selection = HeroesContract.HeroesEntry.COLUMN_HEROES_NAME + " LIKE ?";
//        CacheContract.COLUMN_TITLE + " LIKE ?",
//        String[] selectionArgs = new String[]{
//                '%' + String.valueOf(heroesName) + '%'
//        };

        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = new String[]{
                '%' + String.valueOf(foodName) + '%'
        };

//        String[] args = { "first string", "second@string.com" };
//        Cursor cursor = db.query(TABLE_FOOD, null, );

        Log.e(LOG, selectQuery);

        String[] args = { "first string", "second@string.com" };
        Cursor c = db.query(TABLE_FOOD, null, "name LIKE ?", selectionArgs, null, null, null);

//        Cursor c = db.rawQuery(selectQuery, null);
//        Cursor c = db.rawQuery(selectQuery, new String[]{"/%"+foodName+"%"});

//        new String[]{'%' + foodName + '%'})
//        rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});




        //looping through all rows and adding to list
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Food food = new Food();
//                    food.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//                    food.setName(c.getString(c.getColumnIndex(KEY_NAME)));
//                    food.setIntro(c.getString(c.getColumnIndex(KEY_INTRO)));
//                    food.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT)));
//                    food.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
//                    food.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
                    food.setId(c.getInt(0));
                    food.setName(c.getString(1));
                    food.setIntro(c.getString(2));
                    food.setIngredient(c.getString(3));
                    food.setMake(c.getString(4));
                    food.setPhoto(c.getInt(5));

                    //adding to information list
                    foods.add(food);
                } while (c.moveToNext());
            }
        }

        return foods;
    }

    public int getFoodCount(){
        String countQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }



//    TABLE FAVORITE

    public Favorite getFavorite(long favorite_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAVORITE + " WHERE "
                + KEY_ID + " = " + favorite_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Favorite favorite = new Favorite();
        favorite.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        favorite.setFoodId(c.getInt(c.getColumnIndex(KEY_FOOD_FK)));
        return favorite;
    }

    public List<Food> getAllFavorite(){
        List<Food> foods = new ArrayList<Food>();
//        String selectQuery = "SELECT * FROM " + TABLE_FAVORITE;
        String selectQuery = "SELECT favorite.foodId, food.id, food.name, food.intro, food.ingredient," +
                "food.make, food.photo FROM favorite JOIN food ON favorite.foodId = food.id";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c.moveToFirst()){
            do {
                Food food = new Food();
                food.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                food.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                food.setIntro(c.getString(c.getColumnIndex(KEY_INTRO)));
                food.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT)));
                food.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
                food.setPhoto(c.getInt(c.getColumnIndex(KEY_IMAGE)));
                food.setCategoryId(c.getColumnIndex(KEY_CATEGORY_FK));
//                favorit.setFoodId(c.getInt(c.getColumnIndex(KEY_FOOD_FK)));

                //adding to information list
                foods.add(food);
            }while (c.moveToNext());
        }

        return foods;
    }

    public int getFavoriteCount(int foodId){
        String countQuery = "SELECT * FROM " + TABLE_FAVORITE + " WHERE " + KEY_FOOD_FK + "= " + foodId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //delete Favorite
    public int deleteFavorite(int foodId){
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_FAVORITE, KEY_FOOD_FK + " = ?",
                new String[]{String.valueOf(foodId)});

        return delete;
    }


    //closing database
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
