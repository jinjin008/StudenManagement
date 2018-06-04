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

import java.util.Random;
import java.util.Vector;

import static com.example.he.studenmanagement.R.layout;

/**
 * Created by wjy on 2018/5/7.
 */

public class addPaper_activity extends Activity {
    private EditText name;

    private EditText id;



    private String oldID;//用于防治修改信息时将ID也修改了，而原始的有该ID的学生信息还保存在数据库中


    private Button sure;//确定按钮
    private myDatabaseHelper dbHelper;
    Intent oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout.add_paper_info_layout);

        name = (EditText) findViewById(R.id.add_paper_layout_name);

        id = (EditText) findViewById(R.id.add_paper_layout_id);


        dbHelper = myDatabaseHelper.getInstance(this);

        oldData = getIntent();
        if (oldData.getStringExtra("haveData").equals("true")) {
            initInfo();//恢复旧数据
        }


        sure = (Button) findViewById(R.id.add_paper_layout_sure);
        //将数据插入数据库
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sex不能为空否则程序崩溃，因为在StudentAdapter中有一个ImageView要设置图片
                //我这里要求id,name,sex都不能为空
                String id_ = id.getText().toString();
                String name_ = name.getText().toString();

                Random r = new Random();

                //创建一个存储随机数的集合
                Vector<Integer> vt = new Vector<Integer>();

                //定义一个统计变量
                int count = 0;

                while(count < 10){
                    int number = r.nextInt(10) + 1;

                    //判断number是否在集合中存在
                    if(!vt.contains(number)){
                        //不在集合中，就添加
                        vt.add(number);
                        count++;
                    }
                }
                String bankId = "1,2,3,4,5";




                if (!TextUtils.isEmpty(id_) && !TextUtils.isEmpty(name_) ) {


                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.beginTransaction();//开启事务

                    db.execSQL("delete from paper where id=?", new String[]{oldID});//删除旧数据

                    //判断学号是否重复
                    Cursor cursor = db.rawQuery("select * from paper where id=?", new String[]{id_});
                    if (cursor.moveToNext()) {
                        Toast.makeText(addPaper_activity.this, "已有试卷使用该编号,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        db.execSQL("insert into paper(id,name) values(?,?)", new String[]{id_, name_ });

                        db.setTransactionSuccessful();//事务执行成功
                        db.endTransaction();//结束事务
                        Intent intent = new Intent(addPaper_activity.this, admin_activity.class);
                        startActivity(intent);
                        Toast.makeText(addPaper_activity.this, "试卷信息添加成功", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(addPaper_activity.this, "编号、名称均不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //恢复旧数据
    private void initInfo() {
        String oldName = oldData.getStringExtra("name");
        name.setText(oldName);

        String oldId = oldData.getStringExtra("id");
        oldID = oldId;
        id.setText(oldId);

    }


}