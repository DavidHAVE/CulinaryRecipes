package com.olih.culinaryrecipes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.olih.culinaryrecipes.R;
import com.olih.culinaryrecipes.adapter.FoodRecyclerAdapter;
import com.olih.culinaryrecipes.helper.Constants;
import com.olih.culinaryrecipes.helper.DatabaseHelper;
import com.olih.culinaryrecipes.model.Favorite;
import com.olih.culinaryrecipes.model.Food;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    ListView mFoodListView;
    private SearchView mSearchView;

    List<Food> foods;
    List<Favorite> favorites;
    private boolean filterFood = false;
    private String foodName;

    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

//        mFoodListView = (ListView) rootView.findViewById(R.id.food_list_view);

        mSearchView = (SearchView) rootView.findViewById(R.id.search_view);

        db = new DatabaseHelper(getActivity());

        foods = new ArrayList<>();
        favorites = new ArrayList<>();
//        mFoodListAdapter = new FoodListAdapter(getActivity(), R.layout.food_items, foods);

        //Initializing Views
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);

//        //initializing our adapter
//        adapter = new FoodRecyclerAdapter(foods, getActivity());
//
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);

        setupSearchView();

        return rootView;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Constants.mode == 0) {
            mSearchView.setVisibility(View.VISIBLE);
            getAllFood();
            long count = db.getFoodCount();
            Log.e("RecipeListFragment", "foodCount :" + count);
        }else if (Constants.mode == 1){
            mSearchView.setVisibility(View.GONE);
            getAllFavorite();
        }else if (Constants.mode == 2){
            mSearchView.setVisibility(View.GONE);
            getAllCategory(Constants.CATEGORY[0]);
        }else if (Constants.mode == 3){
            mSearchView.setVisibility(View.GONE);
            getAllCategory(Constants.CATEGORY[1]);
        }else if (Constants.mode == 4){
            mSearchView.setVisibility(View.GONE);
            getAllCategory(Constants.CATEGORY[2]);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        mFoodListAdapter.clear();
//        adapter.notify();
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("GalleryActivity", "heroesName4 :" + query.toString());




        filterFood = true;
        Food food = new Food();
//        getLoaderManager().restartLoader(HERO_LOADER, null, this);
//            mGalleryGridView.setFilterText(query.toString());

        getAllFilterFood(query.toString());

//        adapter = new FoodRecyclerAdapter(foods, getActivity());
        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);


//        mFoodListAdapter.notifyDataSetChanged();
//        Log.e("GalleryActivity", "heroesName2 :" + query.toString());

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
//        String text = query;
//        mGalleryCursorAdapter.getFilter().equals(text);
//        heroesName = query;
//        Log.e("GalleryActivity", "heroesName :" + heroesName);


//        mGalleryGridView.notifyAll();

        if (TextUtils.isEmpty(query)) {
            filterFood = false;
//            getLoaderManager().restartLoader(HERO_LOADER, null, this);
//            mGalleryCursorAdapter.notifyDataSetChanged();
//            mGalleryGridView.clearTextFilter();
            getAllFood();
            Log.e("GalleryActivity", "heroesName3 :" + query.toString());
        }
        return false;
    }



    private void getAllFood() {
        // Getting all Food
        Log.d("RecipeListFragmenr", "Getting All Food");

        foods = db.getAllFood();
//        adapter.notifyDataSetChanged();
        //initializing our adapter
        adapter = new FoodRecyclerAdapter(foods, getActivity());

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
//        mFoodListAdapter.addAll(foods);
        for (Food food : foods) {
            Log.e("RecipeListFragment", food.getId()+", " +food.getName()+", "+food.getIntro()+", "+food.getIngredient());
        }
//        mFoodListView.setAdapter(mFoodListAdapter);

        db.closeDB();
    }

    private void getAllFilterFood(String foodName) {
        // Getting all Food
        Log.d("RecipeListFragmenr", "Getting All Food");
        Log.d("RecipeListFragmenr", "foodName :"+foodName);

        foods.clear();

        foods = db.getAllFilterFood(foodName);
//        adapter.notifyDataSetChanged();
        //initializing our adapter

        adapter = new FoodRecyclerAdapter(foods, getActivity());
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
//        mFoodListAdapter.addAll(foods);
        for (Food food : foods) {
            Log.e("RecipeListFragment", food.getId()+", " +food.getName()+", "+food.getIntro()+", "+food.getIngredient());
        }
//        mFoodListView.setAdapter(mFoodListAdapter);

        db.closeDB();
    }

    private void getAllFavorite(){
        Log.d("RecipeListFragmenr", "Getting All Favorite");

        foods = db.getAllFavorite();
        adapter = new FoodRecyclerAdapter(foods, getActivity());
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
        for (Food food : foods) {
            Log.e("RecipeListFragment", food.getId()+", " +food.getName()+", "+food.getIntro()+", "+food.getIngredient());
        }

        db.closeDB();
    }

    private void getAllCategory(String category){
        Log.d("RecipeListFragmenr", "Getting All CAtegory");

        foods = db.getAllCategory(category);
        adapter = new FoodRecyclerAdapter(foods, getActivity());
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
        for (Food food : foods) {
            Log.e("RecipeListFragment", food.getId()+", " +food.getName()+", "+food.getIntro()+", "+food.getIngredient());
        }

        db.closeDB();
    }

}
