package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class PantryActivity extends AppCompatActivity {
    private  ListView listPantryItems;
    private DatabaseHelper databaseHelper;

    private ArrayList<String> pantryItems;
    private ArrayList<Integer> pantryItemIds;
    private ArrayList<String> pantryItemNames;
    private ArrayList<String> pantryItemCategories;
    private ArrayList<Integer> pantryItemQuantities;
    private ArrayList<String> pantryItemExpiryDates;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry);
        Button buttonAddItem = findViewById(R.id.buttonAddItem);

        listPantryItems = findViewById(R.id.listPantryItems);
        databaseHelper = new DatabaseHelper(this);
        pantryItems = new ArrayList<>();
        pantryItemIds = new ArrayList<>();
        pantryItemNames = new ArrayList<>();
        pantryItemCategories = new ArrayList<>();
        pantryItemQuantities = new ArrayList<>();
        pantryItemExpiryDates = new ArrayList<>();


        Cursor cursor = databaseHelper.getAllItems();
        while (cursor.moveToNext()){

            int itemId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)
            );
            pantryItemIds.add(itemId);

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)
            );

            String category = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)
            );

            String quantity = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY)
            );

            String expiryDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPIRY_DATE)
            );
            pantryItemNames.add(name);
            pantryItemCategories.add(category);
            pantryItemQuantities.add(Integer.parseInt(quantity));
            pantryItemExpiryDates.add(expiryDate);

            pantryItems.add(
                    name +
                            "\nCategory: " + category +
                            " | Quantity: " + quantity +
                            "\nExpiry: " + expiryDate
            );




        }
        cursor.close();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pantryItems
        );

        listPantryItems.setAdapter(adapter);

        listPantryItems.setOnItemClickListener((parent, view, position, id) -> {
                    int itemId = pantryItemIds.get(position);
                    String itemName = pantryItemNames.get(position);
                    String itemCategory = pantryItemCategories.get(position);
                    int itemQuantity = pantryItemQuantities.get(position);
                    String itemExpiryDate = pantryItemExpiryDates.get(position);

                    AlertDialog deleteDialog = new AlertDialog.Builder(PantryActivity.this)
                            .setTitle(itemName)
                            .setMessage("What would you like to do with this pantry item?")
                            .setPositiveButton("DELETE", null)
                            .setNeutralButton("EDIT", null)
                            .setNegativeButton("CANCEL", null)
                            .create();


                    deleteDialog.setOnShowListener(dialog -> {
                                Button deleteButton =
                                        deleteDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                                Button cancelButton =
                                        deleteDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                Button editButton =
                                        deleteDialog.getButton(AlertDialog.BUTTON_NEUTRAL);


                                deleteButton.setTextColor(
                                        getResources().getColor(R.color.white, getTheme())
                                );

                                deleteButton.setBackgroundTintList(
                                        getResources().getColorStateList(R.color.pantry_green, getTheme()
                                        )
                                );

                                cancelButton.setTextColor(
                                        getResources().getColor(
                                                R.color.pantry_dark_green,
                                                getTheme()
                                        )
                                );

                                editButton.setTextColor(
                                        getResources().getColor(
                                                R.color.pantry_dark_green,
                                                getTheme()
                                        )
                                );

                                editButton.setOnClickListener(v -> {
                                    Intent intent = new Intent(
                                            PantryActivity.this,
                                            AddItemActivity.class
                                    );

                                    intent.putExtra("ITEM_ID", itemId);
                                    intent.putExtra("ITEM_NAME", itemName);
                                    intent.putExtra("ITEM_CATEGORY", itemCategory);
                                    intent.putExtra("ITEM_QUANTITY", itemQuantity);
                                    intent.putExtra("ITEM_EXPIRY_DATE", itemExpiryDate);

                                    startActivity(intent);
                                    deleteDialog.dismiss();


                                });


                                deleteButton.setOnClickListener(v -> {

                                    boolean deleted = databaseHelper.deleteItem(itemId);

                                    if (deleted) {


                                        pantryItems.remove(position);
                                        pantryItemIds.remove(position);
                                        pantryItemNames.remove(position);
                                        pantryItemCategories.remove(position);
                                        pantryItemQuantities.remove(position);
                                        pantryItemExpiryDates.remove(position);

                                        adapter.notifyDataSetChanged();

                                        Toast.makeText(
                                                PantryActivity.this,
                                                "Item deleted successfully",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                        deleteDialog.dismiss();
                                    }
                                });
                            });
                        deleteDialog.show();

                    });



        buttonAddItem.setOnClickListener(v ->{
            Intent intent = new Intent(PantryActivity.this, AddItemActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadPantryItems(){
        pantryItems.clear();
        pantryItemIds.clear();
        pantryItemNames.clear();
        pantryItemCategories.clear();
        pantryItemQuantities.clear();
        pantryItemExpiryDates.clear();

        Cursor cursor = databaseHelper.getAllItems();

        while(cursor.moveToNext()){
            int itemId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)
            );

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)
            );

            String category = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)
            );

            int quantity = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY)
            );

            String expiryDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPIRY_DATE)
            );

            pantryItemIds.add(itemId);
            pantryItemNames.add(name);
            pantryItemCategories.add(category);
            pantryItemQuantities.add(quantity);
            pantryItemExpiryDates.add(expiryDate);

            pantryItems.add(
                    name +
                            "\nCategory: " + category +
                            " | Quantity: " + quantity +
                            "\nExpiry: " + expiryDate
            );

        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if( adapter != null){
            loadPantryItems();
        }
    }

}