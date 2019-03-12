package com.olih.culinaryrecipes.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.olih.culinaryrecipes.R;
import com.olih.culinaryrecipes.fragment.RecipeListFragment;
import com.olih.culinaryrecipes.helper.Constants;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final int REQUEST_CODE_GALLERY = 999;

    Dialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.nav_menu1);


//        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);

//        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS FOOD(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, image BLOB)");

    }

    @Override
    protected void onResume() {
        super.onResume();
        insertData();
    }

    private void insertData() {
//        try{
//            sqLiteHelper.insertData(
//                    edtName.getText().toString().trim(),
//                    edtPrice.getText().toString().trim(),
//                    imageViewToByte(imageView)
//            );
//            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
//            edtName.setText("");
//            edtPrice.setText("");
//            imageView.setImageResource(R.mipmap.ic_launcher);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_menu1:
                fragment = new RecipeListFragment();
                Constants.mode = 0;
                Log.e("MainActivity", "mode :"+Constants.mode);
                break;
            case R.id.nav_menu2:
                fragment = new RecipeListFragment();
                Constants.mode = 1;
                Log.e("MainActivity", "mode :"+Constants.mode);
                break;
            case R.id.nav_menu3:
                fragment = new RecipeListFragment();
                Constants.mode = 2;
                Log.e("MainActivity", "mode :"+Constants.mode);
                break;
            case R.id.nav_menu4:
                fragment = new RecipeListFragment();
                Constants.mode = 3;
                break;
            case R.id.nav_menu5:
                fragment = new RecipeListFragment();
                Constants.mode = 4;
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }
}
