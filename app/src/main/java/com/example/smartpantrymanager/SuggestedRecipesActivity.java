package com.example.smartpantrymanager;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private ListView listSuggestedRecipes;
    private TextView textNoRecipes;
    private DatabaseHelper databaseHelper;

    private ArrayList<String> recipeNames;
    private ArrayList<String> recipeIngredients;
    private ArrayList<String> recipeSteps;
    private ArrayList<Integer> recipeIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);

        listSuggestedRecipes = findViewById(R.id.listSuggestedRecipes);
        textNoRecipes = findViewById(R.id.textNoRecipes);
        databaseHelper = new DatabaseHelper(this);


        recipeNames = new ArrayList<>();
        recipeIngredients = new ArrayList<>();
        recipeSteps = new ArrayList<>();
        recipeIds = new ArrayList<>();

        loadSuggestedRecipes();

        listSuggestedRecipes.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(
                    SuggestedRecipesActivity.this,
                    RecipeDetailActivity.class
            );

            intent.putExtra("RECIPE_ID", recipeIds.get(position));
            intent.putExtra("RECIPE_NAME", recipeNames.get(position));
            intent.putExtra("RECIPE_INGREDIENTS", recipeIngredients.get(position));
            intent.putExtra("RECIPE_STEPS", recipeSteps.get(position));
            startActivity(intent);

        });




        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadSuggestedRecipes(){
        recipeNames.clear();
        recipeIngredients.clear();
        recipeSteps.clear();
        recipeIds.clear();

        Cursor cursor = databaseHelper.getAllRecipes();

        while(cursor.moveToNext()){
            int recipeId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                            DatabaseHelper.COLUMN_RECIPE_ID
                    )
            );
            String recipeName = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            DatabaseHelper.COLUMN_RECIPE_NAME
                    )
            );

            String ingredients = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            DatabaseHelper.COLUMN_RECIPE_INGREDIENTS
                    )
            );

            String steps = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            DatabaseHelper.COLUMN_RECIPE_STEPS
                    )
            );

            if (databaseHelper.canMakeRecipe(ingredients)){
                recipeIds.add(recipeId);
                recipeNames.add(recipeName);
                recipeIngredients.add(ingredients);
                recipeSteps.add(steps);
            }


        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                recipeNames
        );

        listSuggestedRecipes.setAdapter(adapter);

        if (recipeNames.isEmpty()){
            textNoRecipes.setVisibility(View.VISIBLE);
            listSuggestedRecipes.setVisibility(View.GONE);
        }else{
            textNoRecipes.setVisibility(View.GONE);
            listSuggestedRecipes.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(databaseHelper != null){
            loadSuggestedRecipes();
        }
    }
}