package com.example.he.studenmanagement.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.he.studenmanagement.R;
import com.example.he.studenmanagement.tools.Paper;
import com.example.he.studenmanagement.tools.PaperAdapter;
import com.example.he.studenmanagement.tools.myDatabaseHelper;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjy on 2018/5/31.
 */

public class paperInfo_activity extends Activity{
    private List<Paper> paperList = new ArrayList<>();
    private myDatabaseHelper dbHelper;
    private ListView listView;
    private PaperAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paperinfo_activity);
        dbHelper = myDatabaseHelper.getInstance(this);

        initPaper();

        adapter = new PaperAdapter(paperInfo_activity.this, R.layout.paper_item, paperList);
        listView = (ListView) findViewById(R.id.list_paper_view);
        listView.setAdapter(adapter);


        //listView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final Paper paper = paperList.get(position);//捕获学生实例
                AlertDialog.Builder builder = new AlertDialog.Builder(paperInfo_activity.this);
                LayoutInflater factory = LayoutInflater.from(paperInfo_activity.this);
                final View textEntryView = factory.inflate(R.layout.paper_info_layout, null);//加载AlertDialog自定义布局
                builder.setView(textEntryView);
                builder.setTitle("请选择相关操作");

                Button selectInfo = (Button) textEntryView.findViewById(R.id.paper_info_select);//查看学生详细信息按钮
                selectInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(paperInfo_activity.this,paper_item_activity.class);
                        startActivity(intent);
                    }
                });


                //删除学生信息
                Button delete_info = (Button) textEntryView.findViewById(R.id.paper_info_delete);
                delete_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder delete_builder = new AlertDialog.Builder(paperInfo_activity.this);
                        delete_builder.setTitle("警告！！！！");
                        delete_builder.setMessage("您将删除该试卷信息，此操作不可逆，请谨慎操作！");

                        delete_builder.setNegativeButton("取消", null);
                        delete_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from paper ", null);
                                paperList.remove(position);//移除
                                adapter.notifyDataSetChanged();//刷新列表

                            }
                        });
                        delete_builder.create().show();

                    }
                });

                //修改学生信息,通过intent传递旧学生信息
                Button update_info = (Button) textEntryView.findViewById(R.id.paper_info_update);
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到添加学生信息的界面,通过intent传递数据
                        Intent intent = new Intent(paperInfo_activity.this, addPaper_activity.class);
                        intent.putExtra("haveData", "true");
                        intent.putExtra("id", paper.getId());
                        intent.putExtra("name", paper.getName());

                        startActivity(intent);
                    }
                });

                builder.create().show();
            }
        });


    }

    private void initPaper(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from paper order by id",null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
//            String date = cursor.getString(cursor.getColumnIndex("date"));


            paperList.add(new Paper(name,id));
        }
        cursor.close();


    }
}
