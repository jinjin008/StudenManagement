package com.example.he.studenmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.he.studenmanagement.R;

public class admin_bank_activity extends Activity {
    private Button select;
    private Button add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.admin_bank_layout);

        select = (Button) findViewById(R.id.admin_bank_select);
        add = (Button) findViewById(R.id.admin_bank_add);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_bank_activity.this, bankInfo_activity.class);

                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_bank_activity.this, addBankActivity.class);
                intent.putExtra("haveData","false");
                startActivity(intent);
            }
        });

    }
}
