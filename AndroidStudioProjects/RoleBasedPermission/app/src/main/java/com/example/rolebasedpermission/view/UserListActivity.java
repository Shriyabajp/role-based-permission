package com.example.rolebasedpermission.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.adapter.UserAdapter;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.BaseResponse;
import com.example.rolebasedpermission.model.UserDto;
import com.example.rolebasedpermission.model.UserListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class UserListActivity extends AppCompatActivity implements com.example.rolebasedpermission.model.Callback {

    List<UserDto> userList=new ArrayList<>();
    UserAdapter userAdapter;
    IAccount iAccount;
    private String email;
    private String productId;
    private String merchantId;
    private SharedPreferences sharedPreferences;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_user_list);
        IAccount iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        getAllUsers();
        findViewById(R.id.bt_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(UserListActivity.this, HomeActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        recyclerView = findViewById(R.id.rv_cart_product);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));   //getting the recyclerview from xml
    }

    private void getAllUsers(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        Call<UserListResponse> call = iAccount.getAll();
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {

                if (response.body() != null && response.body().getData() != null)  {
                    userList=response.body().getData().getUserList();
                    if(null != userList){
                        //user
                        String s="";
                        for(int i=0;i<userList.size();i++)
                        {
                            s+=userList.get(i).getUserName();
                        }
                        Log.d("users",s);}
                    if (response.body().getData().getUserList()== null || response.body().getData().getUserList().isEmpty()) {
                        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(UserListActivity.this).create();
                        alertDialog.setTitle("Sorry");
                        alertDialog.setMessage("No user!!");
                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                    else {
                        progressDialog.dismiss();
//                        userList.contains(sharedPreferences.getString("username",u));
                        userAdapter = new UserAdapter(userList,UserListActivity.this,UserListActivity.this);
                        Toast.makeText(getApplication(), "User List is", Toast.LENGTH_SHORT).show();
                        if (null != userAdapter) {
                            recyclerView.setAdapter(userAdapter);
                        }
                    }
                } else {
                    Toast.makeText(getApplication(), "Invalid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "User Server failure", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }



    @Override
    public void onPointerCaptureChanged ( boolean hasCapture){

    }

    @Override
    public void delete(int id) {
    Log.d("TAG","inside delte");
     deleteUser(userList.get(id).getUserName(),id);
        /*userList.remove(id);
        userAdapter=new UserAdapter(userList,UserListActivity.this,UserListActivity.this);
        recyclerView.setAdapter(userAdapter);*/
    }

    private void deleteUser(final String username, final int position) {
        Call<BaseResponse> call = iAccount.deleteUser(username);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body()!=null && response.body().getSuccess()) {
                    Log.d("s",username);
                    userList.remove(position);
                    userAdapter=new UserAdapter(userList,UserListActivity.this,UserListActivity.this);
                    recyclerView.setAdapter(userAdapter);

                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(UserListActivity.this, "Failed to Delete", Toast.LENGTH_LONG).show();
            }
        });
    }
}