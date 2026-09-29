package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        Cursor cursor = databaseHelper.getAllItems();
        while (cursor.moveToNext()){
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