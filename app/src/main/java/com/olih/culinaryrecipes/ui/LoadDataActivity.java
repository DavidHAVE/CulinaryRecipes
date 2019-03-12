package com.olih.culinaryrecipes.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.olih.culinaryrecipes.R;
import com.olih.culinaryrecipes.helper.Constants;
import com.olih.culinaryrecipes.helper.DatabaseHelper;
import com.olih.culinaryrecipes.model.Category;
import com.olih.culinaryrecipes.model.Food;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class LoadDataActivity extends AppCompatActivity {

    DatabaseHelper db;
    List<Category> categories;
    List<Food> foods;

    private ProgressDialog loadingDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            loadingDialog.dismiss();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = new DatabaseHelper(getApplicationContext());
        categories = new ArrayList<Category>();
        foods = new ArrayList<Food>();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        startTread();
    saveCategory();
    }

    public void startTread() {
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);
        loadingDialog.setMessage("Please Wait...");
        loadingDialog.show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
//                    getAllFood();
//                    saveFood();
//                    sleep(5000);
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

//    public void getAllPond() {
//        // Getting all Pond
//        Log.d("Get Pond", "Getting All Pond");
//
//        foods = db.getAllFood();
////        mPondAdapter.addAll(foods);
//        for (Pond pond : ponds) {
//            Log.e("Pond", pond.getId()+", " +pond.getName()+", "+pond.getSeed_amount()+", "+pond.getInital_date());
//        }
//        mKolamListView.setAdapter(mPondAdapter);
//
//        db.closeDB();
//    }

    private void saveCategory(){
        int categoryCount = db.getCategoryCount();
////
        Log.e("LoadDataActivity", "foodCount : " + categoryCount);

        if (categoryCount < 1){
            Category category = new Category(Constants.CATEGORY[0]);
            Category category1 = new Category(Constants.CATEGORY[1]);
            Category category2 = new Category(Constants.CATEGORY[2]);

            categories.add(category);
            categories.add(category1);
            categories.add(category2);

            if(categories.size() > 0) {
                long category_id = db.createCategory(categories);

                Log.e("LoadDataActivity", "categoryId : " + category_id);

//                loadingDialog.dismiss();

                db.closeDB();

                if (category_id > 0){
                    saveFood();
                }
            }
        }else {
            saveFood();
        }
    }

    private void saveFood() {


//        byte[] image1 = imageViewToByte(R.drawable.satu);
//        String image2 = imageViewToByte(R.drawable.dua).toString();
                Log.e("LoadDataActivity", "byte1 : " + imageViewToByte(R.drawable.satu) +", byte2 :"+imageViewToByte(R.drawable.dua));

//                byte[] image11 = image1.getBytes();

//        Log.e("LoadDataActivity", "byte11 : " + image11);

//        byte[] foodImage = food.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image1, 0, image1.length);
//        mImageView.setImageBitmap(bitmap);
//        holder.imageView.setImageBitmap(bitmap);

        int foodCount = db.getFoodCount();
////
        Log.e("LoadDataActivity", "foodCount : " + foodCount);
////
////
        if (foodCount < 1) {
////
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(false);
            loadingDialog.setMessage("Please Wait...");
            loadingDialog.show();
//
//            Food food = new Food(1, "Masakan 1", "Intro 1", "Ingredient 1", "Make 1", R.drawable.satu);
//            Food food1 = new Food(2, "Masakan 2", "Intro 2", "Ingredient 2", "Make 2", imageViewToByte(R.drawable.dua));
//            Food food2 = new Food(3, "Masakan 3", "Intro 3", "Ingredient 3", "Make 3", imageViewToByte(R.drawable.tiga));
//            Food food3 = new Food(4, "Masakan 4", "Intro 4", "Ingredient 4", "Make 4", imageViewToByte(R.drawable.empat));
//            Food food4 = new Food(5, "Masakan 5", "Intro 5", "Ingredient 5", "Make 5", imageViewToByte(R.drawable.lima));
//            Food food5 = new Food(6, "Masakan 6", "Intro 6", "Ingredient 6", "Make 6", imageViewToByte(R.drawable.enam));
//            Food food6 = new Food(7, "Masakan 7", "Intro 7", "Ingredient 6", "Make 6", imageViewToByte(R.drawable.enam));


            Food food = new Food("Masakan 1", "Intro 1", "Ingredient 1", "Make 1", R.drawable.satu, 1);
            Food food1 = new Food("Masakan 2", "Intro 2", "Ingredient 2", "Make 2", R.drawable.dua, 1);
            Food food2 = new Food("Masakan 3", "Intro 3", "Ingredient 3", "Make 3", R.drawable.tiga, 3);
            Food food3 = new Food("Masakan 4", "Intro 4", "Ingredient 4", "Make 4", R.drawable.empat, 2);
            Food food4 = new Food("Masakan 5", "Intro 5", "Ingredient 5", "Make 5", R.drawable.lima, 2);
            Food food5 = new Food("Masakan 6", "Intro 6", "Ingredient 6", "Make 6", R.drawable.enam, 3);
            Food food6 = new Food("Masakan 7", "Intro 7", "Ingredient 6", "Make 6", R.drawable.enam, 1);
            Food food7 = new Food("Mssaa", "Intro 7", "Ingredient 7", "Make 7", R.drawable.dua, 1);


////            Food food = new Food(1, "Masakan 1", "Intro 1", "Ingredient 1", "Make 1", );
////            Food food1 = new Food(2, "Masakan 2", "Intro 2", "Ingredient 2", "Make 2", imageViewToByte(R.drawable.dua));
////            Food food2 = new Food(3, "Masakan 3", "Intro 3", "Ingredient 3", "Make 3", imageViewToByte(R.drawable.tiga));
////            Food food3 = new Food(4, "Masakan 4", "Intro 4", "Ingredient 4", "Make 4", imageViewToByte(R.drawable.empat));
////            Food food4 = new Food(5, "Masakan 5", "Intro 5", "Ingredient 5", "Make 5", imageViewToByte(R.drawable.lima));
////            Food food5 = new Food(6, "Masakan 6", "Intro 6", "Ingredient 6", "Make 6", imageViewToByte(R.drawable.enam));
////            Food food6 = new Food(7, "Masakan 7", "Intro 7", "Ingredient 6", "Make 6", imageViewToByte(R.drawable.enam));
//
            foods.add(food);
            foods.add(food1);
            foods.add(food2);
            foods.add(food3);
            foods.add(food4);
            foods.add(food5);
            foods.add(food6);
            foods.add(food7);
//
////            long food_id = 0;
////            long food_id;
//
//            for (Food food66 : foods) {
//                int id = food66.getId();
//                String name = food66.getName();
//                String intro = food66.getIntro();
//                String ingredient = food66.getIngredient();
//                String make = food66.getMake();
//                int image = food66.getPhoto();
//                food_id = db.insert(TABLE_FOOD, null, values);
//                Log.e("LoadDataActivity", "id :+", name:"+name+", intro :"+intro+", image : "+image);
//            }
//            Log.e("LoadDataActivity", "food_id : " + food_id);
            Log.e("LoadDataActivity", "foodSize : " +foods.size());
//
            if(foods.size() > 0) {
                long food_id = db.createFood(foods);
//               food_id = db.createFood(foods);

                Log.e("LoadDataActivity", "food_id : " + food_id);


                loadingDialog.dismiss();

//                if (food_id == 7) {
                    db.closeDB();

                    startActivity(new Intent(this, MainActivity.class));
                    finish();
//                }
            }

        }else{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


//            namaKolam = mNamaKolamEditText.getText().toString();
//            jumlahBibit = Integer.parseInt(mJumlahBibitEditText.getText().toString());

            //creating progress
//            Progress progress1 = new Progress("Bulan 1", 0, 0, 0, 0, 0, 0);
//            Progress progress2 = new Progress("Bulan 2", 0, 0, 0, 0, 0, 0);
//            Progress progress3 = new Progress("Bulan 3", 0, 0, 0, 0, 0, 0);

//            long progress1_id = db.createProgress(progress1);
//            long progress2_id = db.createProgress(progress2);
//            long progress3_id = db.createProgress(progress3);

//            mInfoTextView.setText("Progress Count :" + db.getProgressCount());
//
//            Log.e("TambahKolam", "Progress Count :" + db.getProgressCount());
//
////            Toast.makeText(getApplicationContext(), "Proress Count :"+db.getProgressCount(), Toast.LENGTH_SHORT).show();
//            //creating pond
//            Pond pond1 = new Pond(namaKolam, jumlahBibit, tanggalTebar);
//
////            long pond1_id = db.createPond(pond1, new long[]{progress1_id}, new long[] {progress2_id},
////                    new long[]{progress3_id});
//
//            long pond1_id = db.createPond(pond1, new long[]{progress1_id});
//
//            db.createPondProgress(pond1_id, progress2_id);
//            db.createPondProgress(pond1_id, progress3_id);
//
//            Log.e("TambahKolam", "Pond Count :" + db.getPondCount());
//
//            // Getting all Pond
//            Log.d("Get Pond", "Getting All Pond");
//
//            List<Pond> ponds = db.getAllPond();
//            for (Pond pond : ponds) {
//                Log.e("Pond", pond.getId() + ", " + pond.getName() + ", " + pond.getSeed_amount() + ", " + pond.getInital_date());
//            }
//
//            // Getting all Progress
//            Log.d("Get Progress", "Getting All Progress");
//
//            List<Progress> progresses = db.getAllProgress();
//            for (Progress progress : progresses) {
//                Log.e("Progress", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", " + progress.getFeed_weight1());
//            }
//
//            // Getting all PondProgress
//            Log.d("Get Progress", "Getting All Progress");
//            List<PondProgress> pondProgresses = db.getAllPondProgress();
//            for (PondProgress pondProgress : pondProgresses) {
//                Log.e("PondProgress", pondProgress.getId() + ", " + pondProgress.getPond_id() + ", " + pondProgress.getProgress_id());
//            }


//            db.closeDB();
    }

    private byte[] imageViewToByte(int dataDrawable) {

        Resources res = getResources();
        Drawable drawable = res.getDrawable(dataDrawable);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();


//        Bitmap bitmap = Bitmap.createBitmap(R.drawable.nav_background);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
        return bitMapData;
    }
}
