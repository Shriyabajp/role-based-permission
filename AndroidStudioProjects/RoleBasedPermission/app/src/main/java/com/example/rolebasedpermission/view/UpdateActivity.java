package com.example.rolebasedpermission.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.adapter.UserAdapter;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.controller.Urls;
import com.example.rolebasedpermission.model.BaseResponse;
import com.example.rolebasedpermission.model.Role;
import com.example.rolebasedpermission.model.RoleResponse;
import com.example.rolebasedpermission.model.SignUpResponse;
import com.example.rolebasedpermission.model.User;
import com.example.rolebasedpermission.model.UserDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private EditText email, password,roleName,confirmPassword;
    private TextView userName;
    private IAccount iAccount;
    private ProgressDialog progressDialog;
    private ArrayAdapter<String> adapter;
    private Spinner dropdown;
    private int x=0;
    private LinearLayout linearLayout;
    List<String> role_list;
    UserDto user;
    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

            email = findViewById(R.id.et_email);
            userName= findViewById(R.id.et_username);
            password = findViewById(R.id.et_password);
            linearLayout=findViewById(R.id.linear_layout_role);
//        roleName = findViewById(R.id.et_roleName);
            confirmPassword=findViewById(R.id.et_confirmPassword);
//            dropdown = findViewById(R.id.spinner1);

            Retrofit retrofit = AppController.getClientAuthentication(Urls.serverUrl);
            iAccount = retrofit.create(IAccount.class);
            Intent i=getIntent();
            user= (UserDto) i.getSerializableExtra("user");
            final int position=i.getIntExtra("position",-1);
            if(user != null){

                email.setText(user.getEmail());
                userName.setText(user.getUserName());
                password.setText(user.getPassword());
                confirmPassword.setText(user.getPassword());
                role_list=user.getRoleNames();
                displayAllRoles();
//                int x= displayAllRoles(user.getRoleNames());

            }

            progressDialog = new ProgressDialog(this);

            findViewById(R.id.bt_signup).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     if(position != -1){
                         delete(user.getUserName());
                     }

                }
            });
        }

    public void displayAllRoles() {
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        Call<RoleResponse> roles=iAccount.getAllRoles();
        roles.enqueue(new Callback<RoleResponse>() {
            @Override
            public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {
                if(response.body() != null && response.body().getSuccess()){
                    List<String> roles=response.body().getData().getRolenames();
                    String[] items = new String[roles.size()];
                    String s="";
                    for(int i=0;i<roles.size();i++){
                        items[i]=roles.get(i);
                    }

                    checkBoxes = new CheckBox[roles.size()];
                    for (int i = 0; i < roles.size(); i++) {
                        checkBoxes[i] = new CheckBox(UpdateActivity.this);
                        checkBoxes[i].setText(roles.get(i));
                        if(role_list.contains(roles.get(i)))
                            checkBoxes[i].setChecked(true);
//                        checkBoxes[i].setId(i);
                        linearLayout.addView(checkBoxes[i], i);


                    }
//                    adapter = new ArrayAdapter<>(SignUpActivity.this, android.R.layout.simple_spinner_dropdown_item, items);
//                  //set the spinners adapter to the previously created one.
//                    dropdown.setAdapter(adapter);


                }
                else{
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RoleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });
    }
        private int displayAllRoles(String s){
            Call<RoleResponse> roles=iAccount.getAllRoles();
            roles.enqueue(new Callback<RoleResponse>() {
                @Override
                public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {

                    if(response.body() != null && response.body().getSuccess()){
                        List<String> roles=response.body().getData().getRolenames();
                        String[] items = new String[roles.size()];
                        String s="";
                        for(int i=0;i<roles.size();i++){
                            items[i]=roles.get(i);
                            if(items[i].equals(s)){
                                x=i;
                            }
                        }

                        adapter = new ArrayAdapter<>(UpdateActivity.this, android.R.layout.simple_spinner_dropdown_item, items);
                        //set the spinners adapter to the previously created one.
                        dropdown.setAdapter(adapter);
                        dropdown.setSelection(x);
                        Log.d("roles",s);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RoleResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

            return x;
        }

        private void addAccount(User user) {
            WeakHashMap<Object,Object> weakHashMap=new WeakHashMap<>();
            weakHashMap.put("userName",user.getUserName());
            weakHashMap.put("password",user.getPassword());
            weakHashMap.put("email",user.getEmail());
            weakHashMap.put("roleNames",user.getRoleNames());
            iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
            Call<SignUpResponse> call = iAccount.add(weakHashMap);
            call.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    progressDialog.dismiss();
                    if(response.body()!=null && response.body().getSuccess()) {
                        Toast.makeText(getApplicationContext(), "User updated successfully !!!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(UpdateActivity.this, HomeActivity.class);
//                        startActivity(intent);
                        finish();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }

        public boolean isValidEmail(CharSequence email) {
            if (email == null) {
                return false;
            }
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        public boolean isValidPassword(String password) {
            return password != null;
        }

    private void delete(final String username) {
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
//        WeakHashMap<Object,Object> weakHashMap=new WeakHashMap<>();
//        weakHashMap.put("username",username);
        Call<BaseResponse> call = iAccount.deleteUser(username);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null && response.body().getSuccess()) {
//                    Log.d("s", username);
                    //Toast.makeText(UpdateActivity.this,"deleted",Toast.LENGTH_SHORT).show();
                    if (!isValidEmail(email.getText()))
                        email.setError("Enter valid email");
                    if (!isValidPassword(password.getText().toString()))
                        password.setError("Password shud not be null");
                    if (!confirmPassword.getText().toString().equals(password.getText().toString()))
                        confirmPassword.setError("Passwords not matching");

                    if (userName.getText().toString().length() == 0)
                        userName.setError("User Name cannot be empty");

                    if (isValidPassword(password.getText().toString()) && confirmPassword.getText().toString().equals(password.getText().toString())) {

                        User newaccount = new User();
                        newaccount.setEmail(email.getText().toString());
                        newaccount.setUserName(userName.getText().toString());
                        newaccount.setPassword(password.getText().toString());
                        List<String> selected_roles=new ArrayList<>();
                        for (int i = 0; i < checkBoxes.length; i++) {

                            if(checkBoxes[i].isChecked()){selected_roles.add(checkBoxes[i].getText().toString());}
                        }
                        if(selected_roles==null)
                            Toast.makeText(UpdateActivity.this, "null", Toast.LENGTH_SHORT).show();
                        newaccount.setRoleNames(selected_roles);
//                        newaccount.setRoleNames(dropdown.getSelectedItem().toString());
//                    newaccount.setEnabled(enabled.get);
                        if ((TextUtils.isEmpty(email.getText())) || (TextUtils.isEmpty(userName.getText())) || (TextUtils.isEmpty(password.getText()))) {
                            Toast.makeText(getApplicationContext(), "Fields cant be empty !!!", Toast.LENGTH_SHORT).show();
                        } else {
                            addAccount(newaccount);
                        }


                    }
                    Log.d("ssss", new Gson().toJson(response.body()));


                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Failed to Delete", Toast.LENGTH_LONG).show();
            }
        });
        }
    }




