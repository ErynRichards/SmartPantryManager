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
    private ArrayList<String> pantryItemUnits;

    private ArrayList<String> pantryItemExpiryDates;
    private ArrayList<PantryItem> pantryItemList;
    private PantryAdapter adapter;


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
        pantryItemUnits = new ArrayList<>();
        pantryItemExpiryDates = new ArrayList<>();
        pantryItemList = new ArrayList<>();





        adapter = new PantryAdapter(
                this,
                pantryItemList
        );

        listPantryItems.setAdapter(adapter);

        listPantryItems.setOnItemClickListener((parent, view, position, id) -> {
                   PantryItem selectedItem = pantryItemList.get(position);

                    int itemId = selectedItem.getId();
                    String itemName = selectedItem.getName();
                    String itemCategory = selectedItem.getCategory();
                    int itemQuantity = selectedItem.getQuantity();
                    String itemUnit = selectedItem.getUnit();
                    String itemExpiryDate = selectedItem.getExpiryDate();

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
                                    intent.putExtra("ITEM_UNIT", itemUnit);
                                    intent.putExtra("ITEM_EXPIRY_DATE", itemExpiryDate);

                                    startActivity(intent);
                                    deleteDialog.dismiss();


                                });


                                deleteButton.setOnClickListener(v -> {

                                    boolean deleted = databaseHelper.deleteItem(itemId);

                                    if (deleted) {


                                        pantryItemList.remove(position);

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
        pantryItemUnits.clear();
        pantryItemExpiryDates.clear();
        pantryItemList.clear();

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
            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_UNIT)
            );

            String expiryDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPIRY_DATE)
            );

            pantryItemIds.add(itemId);
            pantryItemNames.add(name);
            pantryItemCategories.add(category);
            pantryItemQuantities.add(quantity);
            pantryItemUnits.add(unit);
            pantryItemExpiryDates.add(expiryDate);

            PantryItem pantryItem = new PantryItem(
                    itemId,
                    name,
                    category,
                    quantity,
                    unit,
                    expiryDate
            );
            pantryItemList.add(pantryItem);


            pantryItems.add(
                    name +
                            "\nCategory: " + category +
                            " | Quantity: " + quantity + " " + unit +
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