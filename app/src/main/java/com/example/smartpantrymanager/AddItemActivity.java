
package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;



public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);
        EditText editItemName = findViewById(R.id.editItemName);
        EditText editCategory = findViewById(R.id.editCategory);
        EditText editQuantity = findViewById(R.id.editQuantity);
        EditText editExpiryDate = findViewById(R.id.editExpiryDate);



        Button buttonSaveItem = findViewById(R.id.buttonSaveItem);


        Intent intent = getIntent();
        int itemId = intent.getIntExtra("ITEM_ID", -1);

        if (itemId != -1){
            String itemName = intent.getStringExtra("ITEM_NAME");
            String itemCategory = intent.getStringExtra("ITEM_CATEGORY");
            int itemQuantity = intent.getIntExtra("ITEM_QUANTITY", 0);
            String itemExpiryDate = intent.getStringExtra("ITEM_EXPIRY_DATE");

            editItemName.setText(itemName);
            editCategory.setText(itemCategory);
            editQuantity.setText(String.valueOf(itemQuantity));
            editExpiryDate.setText(itemExpiryDate);
            buttonSaveItem.setText("UPDATE ITEM");


        }



        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        buttonSaveItem.setOnClickListener(v -> {
            String name = editItemName.getText().toString().trim();
            String category = editCategory.getText().toString().trim();
            String quantityText = editQuantity.getText().toString().trim();
            String expiryDate = editExpiryDate.getText().toString().trim();

            if (name.isEmpty() || category.isEmpty() || quantityText.isEmpty() || expiryDate.isEmpty()){
                Toast.makeText(AddItemActivity.this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                return;


            }
            int quantity = Integer.parseInt(quantityText);
            boolean success;

            if(itemId == -1) {
                success = databaseHelper.addItem(
                        name,
                        category,
                        quantity,
                        expiryDate
                );

            } else {
                success = databaseHelper.updateItem(
                        itemId,
                        name,
                        category,
                        quantity,
                        expiryDate
                );

            }


            if (success){
                if(itemId == -1){
                    Toast.makeText(
                            AddItemActivity.this,
                            "Item saved successfully",
                            Toast.LENGTH_SHORT
                    ).show();
                }else {

                    Toast.makeText(
                            AddItemActivity.this,
                            "Item updated successfully",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                editItemName.setText("");
                editCategory.setText("");
                editQuantity.setText("");
                editExpiryDate.setText("");

                editItemName.requestFocus();

            }else{
                Toast.makeText(AddItemActivity.this,
                        "Item could not be saved", Toast.LENGTH_SHORT).show();
            }

        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}