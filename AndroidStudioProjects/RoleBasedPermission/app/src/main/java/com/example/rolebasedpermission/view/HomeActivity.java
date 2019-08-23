package com.example.rolebasedpermission.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.adapter.UserAdapter;
import java.util.ArrayList;
public class HomeActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    private Button btlogback, btlogout;

    ArrayList<String> permissions_list;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        btlogback = findViewById(R.id.bt_back_to_login);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        //btlogout = findViewById(R.id.log_out);
//        btcreate = findViewById(R.id.bt_create);
//        btShowAllUsers=findViewById(R.id.bt_get_all_users);
//        etShowAllUsers=findViewById(R.id.et_showallusers);
//        btCreatePermissions=findViewById(R.id.)
        Intent intent = getIntent();
        //sp = getSharedPreferences("login",MODE_PRIVATE);
        String permissions = intent.getStringExtra("permissions");
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout_home);
        if (permissions != null) {
           // permissions_list = new ArrayList<>();
            String[] list_permissions = permissions.split("\n");
            Button[] list_buttons=new Button[list_permissions.length];
           // Toast.makeText(HomeActivity.this, list_permissions.length, Toast.LENGTH_SHORT).show();
    //        Log.d("list",list_permissions.length+"");
            int n=list_permissions.length;
            for (int i = 0; i < n; i++) {
                list_permissions[i].trim();
                //Log.d("data", )
                char c=list_permissions[i].charAt(0);
                if(c == ' ' || c == '\t' || c == '\n' || c == '\r')break;

                list_buttons[i] = new Button(this);
                list_buttons[i].setText(list_permissions[i]);
                list_buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button b = (Button)view;
                        String buttonText = b.getText().toString();
                        moveToScreen(buttonText);
                    }
                });

                //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(list_buttons[i]);

            }
        }


//        btCreatePermissions=findViewById(R.id.bt_create_permission);
//        btCreateRole=findViewById(R.id.bt_create_role);


//        btCreatePermissions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, PermissionActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btcreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                //Don't come back here
//            }
//        });

        btlogback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "logout clicked", Toast.LENGTH_SHORT).show();
                sp.edit().putBoolean("logged",false).apply();
                Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



//        btShowAllUsers.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        btCreateRole.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, RolePermissionActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }

//    private void getAllUsers(){
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.show();
//        IAccount iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
//        Call<UserListResponse> call = iAccount.getAll();
//        call.enqueue(new Callback<UserListResponse>() {
//            @Override
//            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
//
//                if (response.body() != null )  {
//                    userList=response.body().getData().getUserList();
//                    if(null != userList){
//                    //user
//                    String s="";
//                    for(int i=0;i<userList.size();i++)
//                    {
//                        s+=userList.get(i).getUserName();
//                    }
//                    Log.d("users",s);}
//                    if (response.body().getData().getUserList()== null || response.body().getData().getUserList().isEmpty()) {
//
//                        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(HomeActivity.this).create();
//                        alertDialog.setTitle("Sorry");
//                        alertDialog.setMessage("No user!!");
//                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                        alertDialog.show();
//                    }
//                    else {
//
//                        Toast.makeText(getApplication(), "User List is", Toast.LENGTH_SHORT).show();
//                        if (null != userAdapter) {
//                            userAdapter.notifyDataSetChanged();
//                            progressDialog.dismiss();
//                            Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
//                            startActivity(intent);
//                            finish();
//
//                        }
//                    }
//                } else {
//                    Toast.makeText(getApplication(), "Invalid", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserListResponse> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(getApplicationContext(), "User Server failure", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//        });
//    }


    }

    public void moveToScreen(String s)
    {
        switch(s)
        {
            case "CREATE USER":
                Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(intent);
//                finish();

                break;
            case "CREATE ROLES":
                Intent intent1 = new Intent(HomeActivity.this, RolePermissionActivity.class);
                startActivity(intent1);
//                finish();

                break;

            case "CREATE PERMISSIONS":
                Intent intent2 = new Intent(HomeActivity.this, PermissionActivity.class);
                startActivity(intent2);
//                finish();
                break;

            case "show update and delete users":
                Intent intent3 = new Intent(HomeActivity.this, UserListActivity.class);
                startActivity(intent3);
//                finish();
                break;

            case "show permissions with roles":
                Intent intent5 = new Intent(HomeActivity.this, RoleListActivity.class);
                startActivity(intent5);
//                finish();

                break;

                default:
                    Intent intent4= new Intent(HomeActivity.this, Permission1Activity.class);
                    intent4.putExtra("other_permissions",s);
                    startActivity(intent4);
//                    finish();


        }
    }
}
