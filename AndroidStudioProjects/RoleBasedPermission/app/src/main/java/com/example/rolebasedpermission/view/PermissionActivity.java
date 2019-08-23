package com.example.rolebasedpermission.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.PermissionResponse;
import com.example.rolebasedpermission.model.PermissionResponseDto;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class PermissionActivity extends AppCompatActivity {
    private EditText etpermission;
    private Button btpermission;
    private IAccount iAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        etpermission=findViewById(R.id.et_permission);
        btpermission=findViewById(R.id.bt_permission);

        btpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String permissions;
                   permissions=  etpermission.getText().toString();
                if(etpermission.getText().toString().length()==0){
                    etpermission.setError("Permissions cant be empty!!");
                }
                PermissionResponse permissionResponse= new PermissionResponse();
                permissionResponse.setName(permissions);
                addPermission(permissionResponse);


            }
        });
    }

    private void addPermission(PermissionResponse permissionResponse) {
        WeakHashMap<Object,Object> weakHashMap=new WeakHashMap<>();
        weakHashMap.put("name",permissionResponse.getName());
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        Call<PermissionResponseDto> call =iAccount.addPermissions(weakHashMap);
        call.enqueue(new Callback<PermissionResponseDto>() {
            @Override
            public void onResponse(Call<PermissionResponseDto> call, Response<PermissionResponseDto> response) {
             if(response.body() != null && response.body().getSuccess())
             {
                  Toast.makeText(getApplicationContext(),"permissions added successfully !!",Toast.LENGTH_SHORT).show();
//                 Intent intent=new Intent(PermissionActivity.this,HomeActivity.class);
//                 startActivity(intent);
                 finish();
             }
             else{
                 Toast.makeText(getApplicationContext(), response.body().getMessage(),Toast.LENGTH_SHORT).show();
             }

            }

            @Override
            public void onFailure(Call<PermissionResponseDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"permissions additions failure !!",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}