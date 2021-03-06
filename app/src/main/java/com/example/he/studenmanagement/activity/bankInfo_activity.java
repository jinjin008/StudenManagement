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
import com.example.he.studenmanagement.tools.Bank;
import com.example.he.studenmanagement.tools.BankAdapter;
import com.example.he.studenmanagement.tools.myDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

//import com.example.he.studenmanagement.tools.BankAdapter;

/**
 * Created by wjy on 2018/5/21.
 */

public class bankInfo_activity extends Activity {
    private List<Bank> bankList = new ArrayList<>();
    private myDatabaseHelper dbHelper;
    private ListView listView;
    private BankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bankinfo_activity_layout);
        dbHelper = myDatabaseHelper.getInstance(this);

        initBank();


        adapter = new BankAdapter(bankInfo_activity.this, R.layout.bank_item, bankList);
        listView = (ListView) findViewById(R.id.list_bank_view);
        listView.setAdapter(adapter);


        //listView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final Bank bank = bankList.get(position);//捕获学生实例
                AlertDialog.Builder builder = new AlertDialog.Builder(bankInfo_activity.this);
                LayoutInflater factory = LayoutInflater.from(bankInfo_activity.this);
                final View textEntryView = factory.inflate(R.layout.bank_info_layout, null);//加载AlertDialog自定义布局
                builder.setView(textEntryView);
                builder.setTitle("请选择相关操作");

                Button selectInfo = (Button) textEntryView.findViewById(R.id.bank_info_select);//查看学生详细信息按钮
                selectInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //再次弹出一个alertDialog，用于显示详细学生信息
                        AlertDialog.Builder select_builder = new AlertDialog.Builder(bankInfo_activity.this);
                        select_builder.setTitle("题目详细信息");


                        StringBuilder sb = new StringBuilder();
                        sb.append("题号：" + bank.getId() + "\n");
                        sb.append("题型：" + bank.getType() + "\n");
                        sb.append("难度：" + bank.getDifficult() + "\n");
                        sb.append("题目：" + bank.getTitle() + "\n");
                        sb.append("选项A：" + bank.getIdA() + "\n");
                        sb.append("选项B：" + bank.getIdB() + "\n");
                        sb.append("选项C：" + bank.getIdC() + "\n");
                        sb.append("选项D：" + bank.getIdD() + "\n");
                        sb.append("正确选项：" + bank.getTrueOption() + "\n");
                        select_builder.setMessage(sb.toString());
                        select_builder.create().show();

                    }
                });


                //删除学生信息
                Button delete_info = (Button) textEntryView.findViewById(R.id.bank_info_delete);
                delete_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder delete_builder = new AlertDialog.Builder(bankInfo_activity.this);
                        delete_builder.setTitle("警告！！！！");
                        delete_builder.setMessage("您将删除该题目信息，此操作不可逆，请谨慎操作！");

                        delete_builder.setNegativeButton("取消", null);
                        delete_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from bank where id=?", new String[]{String.valueOf(bank.getId())});
                                bankList.remove(position);//移除
                                adapter.notifyDataSetChanged();//刷新列表

                            }
                        });
                        delete_builder.create().show();

                    }
                });

                //修改学生信息,通过intent传递旧学生信息
                Button update_info = (Button) textEntryView.findViewById(R.id.bank_info_update);
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到添加学生信息的界面,通过intent传递数据
                        Intent intent = new Intent(bankInfo_activity.this, addBankActivity.class);
                        intent.putExtra("haveData", "true");
                        intent.putExtra("id", bank.getClass());
                        intent.putExtra("title", bank.getTitle());
                        intent.putExtra("idA", bank.getIdA());
                        intent.putExtra("idB", bank.getIdB());
                        intent.putExtra("idC", bank.getIdC());
                        intent.putExtra("idD", bank.getIdD());
                        intent.putExtra("trueOption",bank.getTrueOption());
                        startActivity(intent);
                    }
                });

                builder.create().show();
            }
        });


    }

    private void initBank() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from bank order by id",null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));

            int type = cursor.getInt(cursor.getColumnIndex("type"));

            int difficult = cursor.getInt(cursor.getColumnIndex("difficult"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String idA=cursor.getString(cursor.getColumnIndex("idA"));
            String idB=cursor.getString(cursor.getColumnIndex("idB"));
            String idC=cursor.getString(cursor.getColumnIndex("idC"));
            String idD=cursor.getString(cursor.getColumnIndex("idD"));
            String trueOption=cursor.getString(cursor.getColumnIndex("trueOption"));
            bankList.add(new Bank(id,type,difficult,title,idA,idB,idC,idD,trueOption));
        }
        cursor.close();


    }

}
