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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry);
        Button buttonAddItem = findViewById(R.id.buttonAddItem);

        ListView listPantryItems = findViewById(R.id.listPantryItems);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<String> pantryItems = new ArrayList<>();
        ArrayList<Integer> pantryItemIds = new ArrayList<>();


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

            pantryItems.add(
                    name +
                            "\nCategory: " + category +
                            " | Quantity: " + quantity +
                            "\nExpiry: " + expiryDate
            );




        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pantryItems
        );

        listPantryItems.setAdapter(adapter);

        listPantryItems.setOnItemClickListener((parent, view, position, id) -> {
                    int itemId = pantryItemIds.get(position);

                    AlertDialog deleteDialog = new AlertDialog.Builder(PantryActivity.this)
                            .setTitle("Delete Item")
                            .setMessage("Are you sure you want to delete this pantry item?")
                            .setPositiveButton("DELETE", null)
                            .setNegativeButton("CANCEL", null)
                            .create();


                    deleteDialog.setOnShowListener(dialog -> {
                                Button deleteButton =
                                        deleteDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                                Button cancelButton =
                                        deleteDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

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


                                deleteButton.setOnClickListener(v -> {

                                    boolean deleted = databaseHelper.deleteItem(itemId);

                                    if (deleted) {


                                        pantryItems.remove(position);
                                        pantryItemIds.remove(position);
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
}