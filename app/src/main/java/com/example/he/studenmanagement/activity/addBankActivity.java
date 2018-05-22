package com.example.he.studenmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.he.studenmanagement.R;
import com.example.he.studenmanagement.tools.myDatabaseHelper;

/**
 * Created by wjy on 2018/5/21.
 */

public class addBankActivity extends Activity {

    private EditText id;
    private EditText title;
    private EditText idA;
    private EditText idB;
    private EditText idC;
    private EditText idD;
    private EditText trueOption;


    private String oldID;//用于防治修改信息时将ID也修改了，而原始的有该ID的学生信息还保存在数据库中


    private Button sure;//确定按钮
    private myDatabaseHelper dbHelper;
    Intent oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_bank_info_layout);

        title = (EditText) findViewById(R.id.add_bank_layout_title);
        id = (EditText) findViewById(R.id.add_bank_layout_id);
        idA = (EditText) findViewById(R.id.add_bank_layout_idA);
        idB = (EditText) findViewById(R.id.add_bank_layout_idB);
        idC = (EditText) findViewById(R.id.add_bank_layout_idC);
        idD = (EditText) findViewById(R.id.add_bank_layout_idD);
        trueOption =(EditText)findViewById(R.id.add_bank_layout_trueOption);
        sure = (Button)findViewById(R.id.add_bank_layout_sure);

        dbHelper = myDatabaseHelper.getInstance(this);

        oldData = getIntent();
        if (oldData.getStringExtra("haveData").equals("true")) {
            initInfo();//恢复旧数据
        }



        //将数据插入数据库
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_ = id.getText().toString();
                String title_ = title.getText().toString();
                String idA_ = idA.getText().toString();
                String idB_ = idB.getText().toString();
                String idC_ = idC.getText().toString();
                String idD_ = idD.getText().toString();
                String trueOption_ = trueOption.getText().toString();

                if (!TextUtils.isEmpty(id_) && !TextUtils.isEmpty(title_) ) {

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.beginTransaction();//开启事务
                    db.execSQL("delete from bank where id=?", new String[]{oldID});//删除旧数据

                    //判断学号是否重复
                    Cursor cursor = db.rawQuery("select * from bank where id=?", new String[]{id_});
                    if (cursor.moveToNext()) {
                        Toast.makeText(addBankActivity.this, "已有题目使用该题号,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        db.execSQL("insert into bank(id,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?)", new String[]{id_, title_, idA_, idB_, idC_, idD_,trueOption_});
                        db.setTransactionSuccessful();//事务执行成功
                        db.endTransaction();//结束事务
                        Intent intent = new Intent(addBankActivity.this, admin_bank_activity.class);
                        startActivity(intent);
                    }



                } else {
                    Toast.makeText(addBankActivity.this, "题号，题目均不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //恢复旧数据
    private void initInfo() {
        String oldId = oldData.getStringExtra("id");
        oldID = oldId;
        String oldTitle = oldData.getStringExtra("title");
        title.setText(oldTitle);
        String oldIdA = oldData.getStringExtra("idA");
        idA.setText(oldIdA);

        String oldIdB = oldData.getStringExtra("idB");
        idB.setText(oldIdB);
        String oldIdC = oldData.getStringExtra("idC");
        idC.setText(oldIdC);
        String oldIdD = oldData.getStringExtra("idD");
        idD.setText(oldIdD);
        String oldTrueOption = oldData.getStringExtra("trueOption");
        trueOption.setText(oldTrueOption);


    }


}
