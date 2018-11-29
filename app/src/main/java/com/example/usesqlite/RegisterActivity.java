package com.example.usesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText input_drink_name;
    private EditText input_price;
    private TestOpenHelper helper;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        input_drink_name = findViewById(R.id.drink_name);
        input_price = findViewById(R.id.price);


        Button insert_button = (Button)findViewById(R.id.insert_button);
        insert_button.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {

                if(helper == null){
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                String entered_data = input_drink_name.getText().toString();
                String entered_price = input_price.getText().toString();

                insertData(db, entered_data, Integer.parseInt(entered_price));
            }
        });

        Button move_button = (Button)findViewById(R.id.move_button);
        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void insertData(SQLiteDatabase db, String drink_val, int price_num){

        ContentValues values = new ContentValues();
        values.put("drink", drink_val);
        values.put("price", price_num);
    
        db.insert("testdb", null, values);
        Context context = getApplication();
        Toast.makeText(context, "登録が完了しました", Toast.LENGTH_LONG).show();
    }
}
