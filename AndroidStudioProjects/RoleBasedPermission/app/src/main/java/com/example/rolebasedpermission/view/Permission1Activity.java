package com.example.rolebasedpermission.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.rolebasedpermission.R;

public class Permission1Activity extends AppCompatActivity {
    TextView textView;
    Button logBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        Bundle bundle = getIntent().getExtras();
        String message = bundle != null ? bundle.getString("other_permissions") : null;
        textView=findViewById(R.id.tv_permissions);
        logBack=findViewById(R.id.bt_back_to_login);
        textView.setText("you have "+ message+ " permissions !!!");
        logBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Permission1Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }

