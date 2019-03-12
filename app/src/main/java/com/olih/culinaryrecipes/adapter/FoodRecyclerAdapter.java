package com.olih.culinaryrecipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olih.culinaryrecipes.R;
import com.olih.culinaryrecipes.helper.DatabaseHelper;
import com.olih.culinaryrecipes.model.Food;
import com.olih.culinaryrecipes.ui.RecipeDetailActivity;

import java.util.List;

/**
 * Created by apple on 3/10/18.
 */

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.ViewHolder> {

    private Context context;

    List<Food> foods;

    //Constructor of this class
    public FoodRecyclerAdapter(List<Food> foods, Context context){
        super();
        //Getting all superheroes
        this.foods = foods;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        final Food food = foods.get(position);

        int image = food.getPhoto();
        String name = food.getName();

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), image);

        holder.imageViewFood.setImageBitmap(bitmap);
        holder.textViewName.setText(name);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(food.getId(), food.getName(), food.getIntro(), food.getIngredient(), food.getMake(), food.getPhoto());
                Toast.makeText(context,food.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    private void openDetailActivity(int id, String name, String intro, String ingredient, String make, int photo)
    {
        Intent i = new Intent(context, RecipeDetailActivity.class);

        //PACK DATA TO SEND
        i.putExtra(DatabaseHelper.KEY_ID, id);
        i.putExtra(DatabaseHelper.KEY_NAME, name);
        i.putExtra(DatabaseHelper.KEY_INTRO, intro);
        i.putExtra(DatabaseHelper.KEY_INGREDIENT, ingredient);
        i.putExtra(DatabaseHelper.KEY_MAKE, make);
        i.putExtra(DatabaseHelper.KEY_IMAGE, photo);
        //open activity
        context.startActivity(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Views
        public ImageView imageViewFood;
        public TextView textViewName;
        ItemClickListener itemClickListener;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewFood = (ImageView) itemView.findViewById(R.id.imgFood);
            textViewName = (TextView) itemView.findViewById(R.id.txtName);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }
}
