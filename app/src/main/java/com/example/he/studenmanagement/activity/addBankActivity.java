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
    private EditText type;
    private EditText difficult;
    private EditText title;
    private EditText idA;
    private EditText idB;
    private EditText idC;
    private EditText idD;
    private EditText trueOption;



    private Button sure;//确定按钮
    private myDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_bank_info_layout);

        id= (EditText) findViewById(R.id.add_bank_layout_id);
        type = (EditText) findViewById(R.id.add_bank_layout_type);
        difficult = (EditText) findViewById(R.id.add_bank_layout_difficult);
        title = (EditText) findViewById(R.id.add_bank_layout_title);
        idA = (EditText) findViewById(R.id.add_bank_layout_idA);
        idB = (EditText) findViewById(R.id.add_bank_layout_idB);
        idC = (EditText) findViewById(R.id.add_bank_layout_idC);
        idD = (EditText) findViewById(R.id.add_bank_layout_idD);
        trueOption =(EditText)findViewById(R.id.add_bank_layout_trueOption);
        sure = (Button)findViewById(R.id.add_bank_layout_sure);

        dbHelper = myDatabaseHelper.getInstance(this);




        //将数据插入数据库
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_ = id.getText().toString();
                String type_ = type.getText().toString();
                String difficult_=difficult.getText().toString();
                String title_ = title.getText().toString();
                String idA_ = idA.getText().toString();
                String idB_ = idB.getText().toString();
                String idC_ = idC.getText().toString();
                String idD_ = idD.getText().toString();
                String trueOption_ = trueOption.getText().toString();

                if (!TextUtils.isEmpty(id_)&&!TextUtils.isEmpty(type_)&&!TextUtils.isEmpty(difficult_) && !TextUtils.isEmpty(title_) ) {


                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.beginTransaction();//开启事务


                    //判断学号是否重复
                    Cursor cursor = db.rawQuery("select * from bank where id=?", new String[]{id_});
                    if (cursor.moveToNext()) {
                        Toast.makeText(addBankActivity.this, "已有题目使用该题号,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {

                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)", new String[]{id_,type_,difficult_, title_, idA_, idB_, idC_, idD_,trueOption_});


//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"12","2","1","Java语言的源程序不是编译型的，而是编译解释型的。","R","W","0","0","1"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"13","2","1","System类中的println()方法分行显示信息，而print()方法不分行显示信息。","R","W","0","0","1"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"14","2","1","当前路径的标识是“.”。","R","W","0","0","1"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"15","2","1","java命令不区分大小写，而javac命令区分大小写。","R","W","0","0","2"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"16","2","1","在运行字节码文件时，使用java命令，一定要给出字节码文件的扩展名.class。","R","W","0","0","2"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"17","2","1","Java语言使用的是Unicode字符集，每个字符在内存中占8位。","R","W","0","0","2"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"18","2","1","Java语言中不同数据类型的长度是固定的，不随机器硬件不同而改变。","R","W","0","0","1"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"19","2","1","所有的变量在使用前都必须进行初始化。","R","W","0","0","2"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"20","2","1","已知byte i = (byte)127;  i = i +1;这两个语句能被成功编译。","R","W","0","0","1"});
//                        db.execSQL("insert into bank(id,type,difficult,title,idA,idB,idC,idD,trueOption) values(?,?,?,?,?,?,?,?,?)",new String[]{"21","2","1","String str=\"abcdefghi\"; char chr=str.charAt(9);","R","W","0","0","2"});

                        db.setTransactionSuccessful();//事务执行成功
                        db.endTransaction();//结束事务
                        Intent intent = new Intent(addBankActivity.this, admin_activity.class);
                        startActivity(intent);
                        Toast.makeText(addBankActivity.this, "题目添加成功", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(addBankActivity.this, "题号，题目，题型，难度均不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




}
