package com.olih.culinaryrecipes.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olih.culinaryrecipes.R;
import com.olih.culinaryrecipes.helper.Constants;
import com.olih.culinaryrecipes.helper.DatabaseHelper;
import com.olih.culinaryrecipes.model.Favorite;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView mFoodDetailImageView;
    private TextView mIntroTextView, mIngredientTextView, mMakeTextView;
    private FloatingActionButton mFavoriteFab;

    private Dialog settingsDialog;

    private int foodId, image;
    private String name, intro, ingredient, make;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mFoodDetailImageView = (ImageView) findViewById(R.id.food_detail_image_view);
        mIntroTextView = (TextView) findViewById(R.id.intro_text_view);
        mIngredientTextView = (TextView) findViewById(R.id.ingredient_text_view);
        mMakeTextView = (TextView) findViewById(R.id.make_text_view);
        mFavoriteFab = (FloatingActionButton) findViewById(R.id.favorite_fab);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new DatabaseHelper(getApplicationContext());

        if (getIntent() != null){
            foodId = getIntent().getExtras().getInt(DatabaseHelper.KEY_ID);
            name = getIntent().getExtras().getString(DatabaseHelper.KEY_NAME);
            intro = getIntent().getExtras().getString(DatabaseHelper.KEY_INTRO);
            ingredient = getIntent().getExtras().getString(DatabaseHelper.KEY_INGREDIENT);
            make = getIntent().getExtras().getString(DatabaseHelper.KEY_MAKE);
            image = getIntent().getExtras().getInt(DatabaseHelper.KEY_IMAGE);

            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), image);

            mFoodDetailImageView.setImageBitmap(bitmap);
            toolbar.setTitle(name);
            setSupportActionBar(toolbar);
            mIntroTextView.setText(intro);
            mIngredientTextView.setText(ingredient);
            mMakeTextView.setText(make);

        }

        mFavoriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.mode == 1){
                    deleteFavorite();
                }else {
                    addFavorite();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/*");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Culinary Recipes");
            startActivity(Intent.createChooser(shareIntent, "Share link using"));
            return true;
        }else if (id == R.id.action_about){
            settingsDialog = new Dialog(this);
            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.about_layout
                    , null));
            settingsDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dismissListener(View view){
        settingsDialog.dismiss();
    }

    private void addFavorite() {
        int favoriteCount = db.getFavoriteCount(foodId);
        Log.e("RecipeDetailActivity", "favoriteCount :" + favoriteCount);

        if (favoriteCount == 0) {
            long favorite_id = db.createFavorite(foodId);
            Log.e("RecipeDetailActivity", "favorite_id :" + favorite_id);
            db.closeDB();
            Toast.makeText(this, "Di tambah ke Favorit", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Sudah di dalam Favorit", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteFavorite(){

        int delete = db.deleteFavorite(foodId);

        if (delete > 0){
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Favorit Terhapus", Toast.LENGTH_SHORT).show();
        }
    }
}
