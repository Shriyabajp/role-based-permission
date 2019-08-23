package com.example.rolebasedpermission.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.PermissionListResponse;
import com.example.rolebasedpermission.model.RolePermissionDto;
import com.example.rolebasedpermission.model.RolePermissionResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class RolePermissionActivity extends AppCompatActivity {
    private EditText etpermission;
    private Button btpermission;
    private IAccount iAccount;
    private LinearLayout linearLayout;
    private String permissiontext= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_permission);
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        etpermission= findViewById(R.id.et_role_permission);
        btpermission=findViewById(R.id.bt_role_permission);
        linearLayout=findViewById(R.id.linear_layout_role_permission);
        displayAllPermissions();
        btpermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etpermission.getText() != null){
                permissiontext=etpermission.getText().toString();}
                RolePermissionDto rolePermissionDto = new RolePermissionDto();
                rolePermissionDto.setRoleName(permissiontext);
                List<String> permissionList=new ArrayList<>();
                int childCount=0;
                childCount=linearLayout.getChildCount();
                for (int i=0; i < childCount; i++){
                    View v = linearLayout.getChildAt(i);
                    if(v instanceof CheckBox){
                        if(((CheckBox)v).isChecked()){
                            permissionList.add(((CheckBox)v).getText().toString());}
                    }
                  }
                   rolePermissionDto.setPermissions(permissionList);
                   if(permissiontext.length() == 0){
                      etpermission.setError("Role name cannot be empty !!");}
                    if((TextUtils.isEmpty(permissiontext))){
                     Toast.makeText(getApplicationContext(), "Fields cant be empty !!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                     addRolePermission(rolePermissionDto);
                    }
            }
        });

    }

    public void displayAllPermissions() {
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        Call<PermissionListResponse> roles=iAccount.getAllPermissions();
        roles.enqueue(new Callback<PermissionListResponse>() {
            @Override
            public void onResponse(Call<PermissionListResponse> call, Response<PermissionListResponse> response) {

                if(response.body() != null && response.body().getSuccess()){
                    List<String> permissions=response.body().getData().getPermissions();
                    String[] items = new String[permissions.size()];
                    String s="";
                    for(int i=0;i<permissions.size();i++){
                        items[i]=permissions.get(i);
                    }

                    CheckBox[] checkBoxes = new CheckBox[permissions.size()];
                    for (int i = 0; i < permissions.size(); i++) {
                        checkBoxes[i] = new CheckBox(RolePermissionActivity.this);
                        checkBoxes[i].setText(permissions.get(i));
//                        checkBoxes[i].setId(i);
                        linearLayout.addView(checkBoxes[i], i);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PermissionListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });
    }

    private void addRolePermission(RolePermissionDto rolePermissionDto){
            WeakHashMap<Object,Object> weakHashMap=new WeakHashMap<>();
            weakHashMap.put("roleName",rolePermissionDto.getRoleName());
            weakHashMap.put("permissions",rolePermissionDto.getPermissions());
            iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
            Call<RolePermissionResponse> call = iAccount.addRolePermissions(weakHashMap);
            call.enqueue(new Callback<RolePermissionResponse>() {
                @Override
                public void onResponse(Call<RolePermissionResponse> call, Response<RolePermissionResponse> response) {
                    if(response.body() !=null && response.body().getSuccess()) {
                        Toast.makeText(getApplicationContext(), "Roles saved successfully !!!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RolePermissionActivity.this, HomeActivity.class);
//                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Role already exists!!!", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<RolePermissionResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }
}