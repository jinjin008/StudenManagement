package com.example.he.studenmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.he.studenmanagement.R;

/**
 * 管理员的管理界面
 * Created by he on 2016/9/30.
 */
public class admin_activity extends Activity {

    private Button bank_select;//
    private Button bank_add;//
    private Button paper_select;//
    private Button paper_add;//
    private Button stu_select;//
    private Button stu_add;//
    private Button stu_order;//
    private Button out;//
    private TextView forceOffline;//强制下线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.admin_layout);

        bank_select = (Button) findViewById(R.id.admin_bank_select);
        bank_add = (Button) findViewById(R.id.admin_bank_add);
        paper_select = (Button) findViewById(R.id.admin_paper_select);
        paper_add = (Button) findViewById(R.id.admin_paper_add);
        stu_select = (Button) findViewById(R.id.admin_manage_select);
        stu_add = (Button) findViewById(R.id.admin_manage_add);
        stu_order = (Button) findViewById(R.id.admin_manage_order);
        out  = (Button) findViewById(R.id.out);
        forceOffline = (TextView) findViewById(R.id.admin_activity_forceOffline);

        bank_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_activity.this, bankInfo_activity.class);

                startActivity(intent);

            }
        });

        bank_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_activity.this, addBankActivity.class);
                intent.putExtra("haveData","false");
                startActivity(intent);

            }
        });

        paper_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_activity.this, paperInfo_activity.class);

                startActivity(intent);
            }
        });

        paper_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_activity.this, addPaper_activity.class);
                intent.putExtra("haveData","false");
                startActivity(intent);
            }
        });

        stu_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_activity.this, studentInfo_activity.class);

                startActivity(intent);
            }
        });

        stu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_activity.this, addStudent_info_activity.class);
                intent.putExtra("haveData","false");
                startActivity(intent);
            }
        });

        stu_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_activity.this, student_total_score.class);
                startActivity(intent);
            }
        });


        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.he.example.OfflineBradcast");
                sendBroadcast(intent);
            }
        });


    }
}
