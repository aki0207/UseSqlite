package com.example.usesqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    Button delete_button;
    Button move_button;
    EditText drink_name_form;
    String delete_target;
    private TestOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);



        delete_button = (Button)findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drink_name_form = (EditText)findViewById(R.id.drink_name);
                delete_target = drink_name_form.getText().toString();

  /*              //ContentValuesに値を入れる
                ContentValues val = new ContentValues();
                val.put("drink", delete_target);*/


                if(helper == null){
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                //データベースを更新
                db.delete("testdb", "drink='" + delete_target + "'",null);
                Context context = getApplicationContext();
                Toast.makeText(context, "削除が完了しました", Toast.LENGTH_LONG).show();
            }
        });

        Button move_button = (Button)findViewById(R.id.move_button);
        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
