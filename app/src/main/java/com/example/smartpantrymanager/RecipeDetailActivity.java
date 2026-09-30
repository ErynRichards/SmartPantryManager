package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);

        TextView textRecipeName = findViewById(R.id.textRecipeName);
        TextView textRecipeIngredients = findViewById(R.id.textRecipeIngredients);
        TextView textRecipeSteps = findViewById(R.id.textRecipeSteps);
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v ->{
            finish();
        });

        Intent intent = getIntent();
        String recipeName = intent.getStringExtra("RECIPE_NAME");
        String ingredients = intent.getStringExtra("RECIPE_INGREDIENTS");
        String steps = intent.getStringExtra("RECIPE_STEPS");

        textRecipeName.setText(recipeName != null ? recipeName : "Recipe");

        String formattedIngredients = "";
        if (ingredients != null) {
            formattedIngredients = ingredients
                    .replace(",", "\n")
                    .replace(":", " ");
        }

        textRecipeIngredients.setText(
                "INGREDIENTS\n\n" + formattedIngredients
        );

        textRecipeSteps.setText(
                "PREPARATION STEPS\n\n" + ( steps != null ? steps : "No preparation steps available.")
        );






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}