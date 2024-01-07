package com.ayhanapp.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayhanapp.recipeapp.Listeners.RecipeDetailsListener;
import com.ayhanapp.recipeapp.Models.RecipeDetailsResponse;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

public class RecipeDetails extends AppCompatActivity {
    int id;
    TextView textView_meal_name,textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    ManageRequest request;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id= Integer.parseInt(getIntent().getStringExtra("id"));
        request = new ManageRequest(this);
        request.getRecipeDetails(recipeDetailsListener,id);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading Details");
        progress.show();
    }

    private void findViews() {

        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);

    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            progress.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetails.this,LinearLayoutManager.HORIZONTAL,false));

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this,message,Toast.LENGTH_SHORT).show();
        }
    };
}