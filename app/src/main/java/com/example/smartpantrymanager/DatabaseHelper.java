package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.content.ContentValues;
import android.database.Cursor;




public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_PANTRY = "pantry_items";
    public static final String TABLE_RECIPES = "recipes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final  String COLUMN_CATEGORY = "category";
    public static final  String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final  String COLUMN_EXPIRY_DATE = "expiry_date";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
    public static final String COLUMN_RECIPE_STEPS = "steps";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public  void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_CATEGORY +  " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                COLUMN_UNIT + " TEXT NOT NULL, " +
                COLUMN_EXPIRY_DATE + " TEXT)";

        db.execSQL(createTable);
        String createRecipeTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INGREDIENTS + " TEXT NOT NULL, " +
                COLUMN_RECIPE_STEPS + " TEXT NOT NULL)";

        db.execSQL(createRecipeTable);
        addRecipe(db,
                "Amagwinya",
                "flour:500:g,yeast:10:g,sugar:20:g,water:300:ml",
                "Mix the flour, yeast and sugar. Add warm water and knead into a soft dough. Alllow the dough to rise, shape into portions and fry until golden brown!");

        addRecipe(db,
                "Engish Breakfast",
                "egg:2:pieces,bacon:2:pieces,bread:2:pieces,tomato:1:pieces",
                "Cook the bacon and tomato on a pan on medium heat. Fry the eggs in butter and toast the bread. Butter the toast. Plate all ingredients and serve.");

        addRecipe(db,
                "Butter Chicken",
                "chicken:500:g,butter:50:g,tomato:2:pieces,cream:200:ml,onion:1:pieces,masala:10:grams",
                "Cook the chicken until browned in the butter. Remove chicken off stove. Add the onions and chillies. Wait until its fried until golden and then add your masala. Next add tomatoes. Wait until the water has cooked out of the tomato. Add in the chicken and coat in gravy. Add cream until thick. Serve in a bowl or plate.");

        addRecipe(db,
                "Shakshuka",
                "egg:3:pieces,tomato:3:pieces,onion:1:pieces,masala:5:grams",
                "Cook the chopped onion and tomatoes until soft. Add masala. Make spaces in the sauce, add the eggs and cook until the eggs are set.");

        addRecipe(db,
                "Potato Curry",
                "potato:4:pieces,onion:1:pieces,tomato:2:pieces,curry powder:20:g",
                "Cook the onion with curry powder. Add the tomatoes and potatoes and simmer with water until the potatoes are tender.");

        addRecipe(
                db,
                "egg Sandwhich",
                "bread:2:pieces,egg:2:pieces,mayonaise:20:g",
                "Cook the eggs, chop them and mix with mayonaise. Place the egg mixture between the bread slices.");

        addRecipe(db,
                "Braai Broodjie",
                "bread:2:pieces,cheese:50:g,tomato:1:pieces,onion:1:pieces",
                "Place cheese, sliced tomato and onion between the bread. Braai until the bread is golden and the cheese has melted.");

        addRecipe(db,
                "Roti",
                "flour:250:g,butter:30:g,water:150:ml",
                "Combine the flour and butter until it turns to crumbs. Gradually add water and knead into a soft dough. Divide the dough into 8 pieces, roll them out into circles and cook each roti in a flat hoot pan till it puffs up.");

        addRecipe(db,
                "Waffles",
                "flour:200:g,egg:2:pieces,milk:250:ml,sugar:30:g",
                "Mix the flour and sugar. Add the eggs and milk mix into a batter. Cook in a waffle maker until golden");

        addRecipe(db,
                "Pancakes",
                "flour:200:g,egg:2:pieces,milk:300:ml,sugar:20:g",
                "Mix the flour and sugar. Add the eggs and milk and whisk until smooth.Cook portions of batter in a hot pan.");

        addRecipe(db,
                "Egg and bacon bun",
                "bun:1:pieces,egg:1:pieces,bacon:2:pieces",
                "Cook the bacon and egg. Sluce the bun in half and place the cooked bacon and egg inside.");

        addRecipe(db,
                "Corn dog",
                "Sausage:2:pieces,flour:150:g,egg:1:pieces,buttermilk:150:ml",
                "Mix the flour, egg and milk into a batter. Pour the batter into a long cup until it reaches half way. Place a skewer inside the sausage and dip the sausage into the batter. Deep fry the corn dog until golden brown and enjoy. "

                );

        addRecipe(db,
                "Bunny chow",
                "unsliced bread loaf:1:pieces, chicken:10:pieces,potatoe:2:pieces,curry powder:20:g,onion:3:pieces,tomato:5:pieces, chillies:3:pieces",
                "Cut onion and chillies and fry on stove till golden brown. Add tomato and curry powder. Add chicken and potatoe. Once Chicken is done cooking remove off stove. Cut the unsliced bread in half and diig out the center of the loaf. Dish curry into  the center and serve on a plate"

                );

        addRecipe(db,
                "Chakalaka",
                "carrot:2:pieces,onion:1:pieces,tomato:2:pieces,baked beans:400:g",
                "Cook the onion and grated carrot until softened. Add the tomatoes and baked beans and simmer until combined.");

        addRecipe(db,
                "Pap and tomato gravy",
                "maize meal:250:g,water:500:ml,tomato:3:pieces,onion:1:pieces",
                        "Cook the maize meal with water until thick. Cook the tomato and onion separately to make a gravy and serve together.");

        addRecipe(db,
                "Chicken curry",
                "chicken:500:g, potato:3:pieces,onion:1:pieces,curry powder:20:g",
                "Cook the onion with curry powder.Add the chicken and potatoes and simmer until the chicken is fully cooked and potatoes are tender.");

        addRecipe(db,
                "Cheese and tomato toastie",
                "bread:2:pieces,cheese:50:g,tomato:1:pieces",
                "Place sliced tomato and cheese between the bread and toast until golden and the cheese has melted.");

        addRecipe(db,
                "French toast",
                "bread:2:pieces,egg:2:pieces,milk:100:ml",
                "Beat the eggs and milk together. Dib the bread into the mixture and cook in a pan until golden on both sides.");

        addRecipe(db,
                "Boerewors roll",
                "boerewors:1:pieces,roll:1:pieces,onion:1:pieces",
                "Cook the boerewors thoroughly. Cook the sliced onion until soft and serve both insiide the roll");

        addRecipe(db,
                "Vetkoek and mince",
                "flour:300:g,yeast:10:g,water:200:ml,mince:200:g",
                "Make a soft dough using flour, yeast and water and allow it to rise. Fry portions until golden. Cook the mince thorough and serve inside the vetkoek.");




    }

    public boolean addItem(String name, String category, int quantity, String unit, String expiryDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_EXPIRY_DATE, expiryDate);
        long result = db.insert(TABLE_PANTRY, null, values);
        return result != -1;


    }

    public Cursor getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_PANTRY,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC"
        );
    }

    public boolean deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted =  db.delete(
                TABLE_PANTRY,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
        return rowsDeleted > 0;
    }

    public boolean updateItem(int id, String name, String category, int quantity, String unit, String expiryDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit);
        values.put(COLUMN_EXPIRY_DATE, expiryDate);

        int rowsUpdated = db.update(
                TABLE_PANTRY,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
        return rowsUpdated > 0;

    }
    private void addRecipe(SQLiteDatabase db, String name, String ingredients, String steps) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, name);
        values.put(COLUMN_RECIPE_INGREDIENTS, ingredients);
        values.put(COLUMN_RECIPE_STEPS, steps);

        db.insert(TABLE_RECIPES, null, values);
    }

    public Cursor getAllRecipes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                COLUMN_RECIPE_NAME + " ASC"
        );
    }

    public boolean canMakeRecipe(String recipeIngredients){
        String[] requiredIngredients = recipeIngredients.split(",");


        for (String requiredIngredient : requiredIngredients){
            String[] parts = requiredIngredient.split(":");
            if(parts.length != 3) {
                return false;
            }
            String requiredName = normalizeIngredientName(parts[0]);
            double requiredQuantity = Double.parseDouble(parts[1]);
            String requiredUnit = normalizeUnit(parts[2]);

            boolean ingredientFound = false;


            Cursor cursor = getAllItems();

            while (cursor.moveToNext()){
                String pantryName = cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_NAME)
                );

                int pantryQuantity = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)
                );

                String pantryUnit = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_UNIT)
                );

                String normalizedPantryName = normalizeIngredientName(pantryName);
                String normalizedPantryUnit = normalizeUnit(pantryUnit);

                if (normalizedPantryName.equals(requiredName)){

                    double convertedPantryQuantity =
                            convertQuantity(pantryQuantity, normalizedPantryUnit, requiredUnit);

                    if (convertedPantryQuantity >= requiredQuantity){
                        ingredientFound = true;
                        break;
                    }
                }


            }
            cursor.close();
            if (!ingredientFound){
                return false;
            }
        }
        return true;

    }

    private String normalizeIngredientName(String name){
        String normalized = name.trim().toLowerCase();

        if (normalized.equals("tomatoes")){
            return "tomato";
        }

        if (normalized.equals("potatoes")){
            return "potato";
        }

        if (normalized.equals("eggs")){
            return "egg";
        }

        if (normalized.equals("bananas")){
            return "banana";
        }

        if (normalized.equals("carrots")){
            return "carrot";
        }

        if (normalized.equals("onions")){
            return "onion";
        }

        if (normalized.equals("sausages")){
            return "sausage";
        }

        return normalized;

    }

    private String normalizeUnit(String unit){
        String normalized = unit.trim().toLowerCase();

        if (normalized.equals("grams") || normalized.equals("gram")){
            return "g";
        }
        if (normalized.equals("kilograms") || normalized.equals("kilogram") || normalized.equals("kgs")) {
            return "kg";
        }

        if (normalized.equals("millilitres") ||
        normalized.equals("milliliters")||
        normalized.equals("millilitre") ||
        normalized.equals("milliliter")){
            return "ml";
        }

        if (normalized.equals("litres") ||
        normalized.equals("liters")||
        normalized.equals("litre") ||
        normalized.equals("liter")) {
            return "l";
        }
        if (normalized.equals("piece") ||
        normalized.equals("pieces")){
            return "pieces";
        }
        return normalized;

    }


    private double convertQuantity(double quantity, String pantryUnit, String requiredUnit){
        if(pantryUnit.equals(requiredUnit)){
            return quantity;
        }

        if(pantryUnit.equals("kg") && requiredUnit.equals("g")){
            return quantity * 1000;
        }

        if (pantryUnit.equals("g") && requiredUnit.equals("kg")){
            return quantity / 1000;
        }

        if (pantryUnit.equals("l") && requiredUnit.equals("ml")){
            return quantity * 1000;
        }

        if (pantryUnit.equals("ml") && requiredUnit.equals("l")){
            return quantity / 1000;
        }
        return -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);

    }
}
