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

public class EditActivity extends AppCompatActivity {

    private EditText input_drink_name;
    private EditText input_price;
    private EditText target_data;
    private TestOpenHelper helper;
    private SQLiteDatabase db;
    private Button update_button;
    private Button move_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Button update_button = (Button)findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(helper == null){
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                input_drink_name = (EditText)findViewById(R.id.drink_name);
                input_price = (EditText)findViewById(R.id.price);
                target_data = (EditText)findViewById(R.id.target_drink_name);


                String entered_data = input_drink_name.getText().toString();
                String entered_price = input_price.getText().toString();
                String entered_target_drink = target_data.getText().toString();

                ContentValues values = new ContentValues();
                values.put("drink", entered_data);
                values.put("price", entered_price);

                db.beginTransaction();
                db.update("testdb", values, "drink = '" + entered_target_drink + "'" ,null);
                //db.update("testdb", values, "drink = '" + target_data + "'",  null);
                db.setTransactionSuccessful();
                db.endTransaction();
                Context context = getApplicationContext();
                Toast.makeText(context, "更新が完了しました", Toast.LENGTH_SHORT).show();


            }
        });

        Button move_button = (Button)findViewById(R.id.move_button);
        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
