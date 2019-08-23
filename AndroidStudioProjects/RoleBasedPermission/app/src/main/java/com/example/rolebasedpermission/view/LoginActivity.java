package com.example.rolebasedpermission.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.Account;
import com.example.rolebasedpermission.model.LoginResponse;

import java.util.List;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class LoginActivity extends AppCompatActivity {

    final String TAG = "Login_page";
    ProgressDialog progressDialog;
    private EditText username;
    private EditText ptpassword;
    private Button btlogin;
    private IAccount iAccount;
    private String email;
    private String password;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressDialog = new ProgressDialog(this);
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        username = findViewById(R.id.et_username);
        ptpassword = findViewById(R.id.et_password);
        btlogin = findViewById(R.id.bt_login);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("logged", false)
        ) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            sp = getSharedPreferences("login", MODE_PRIVATE);
            String permissions = sp.getString("permissions", null);
            intent.putExtra("permissions", permissions);
            startActivity(intent);
            finish();
        }

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0)
                    username.setError("Username cannot be empty !!");
                if (ptpassword.getText().toString().length() == 0)
                    ptpassword.setError("Password cannot be empty !!");
                if (!(TextUtils.isEmpty(username.getText())) || !(TextUtils.isEmpty(ptpassword.getText()))) {
                    Account user = new Account(username.getText().toString(), ptpassword.getText().toString());
                    progressDialog.show();
                    login(user);
                }
            }
        });
    }

    private boolean validate() {
        this.email = username.getText().toString();
        password = this.ptpassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            return false;

        return isValidEmail(email) && isValidPassword(password);
    }

    public boolean isValidEmail(CharSequence email) {
        if (email == null) {
            username.setError("username cannot be empty !!");
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8) {
            ptpassword.setError("Password cannot be empty!!");
            return false;
        }
        return true;
    }

    private void login(final Account account) {
        WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("username", account.getEmail());
        weakHashMap.put("password", account.getPassword());
        Call<LoginResponse> call = iAccount.doLogin(weakHashMap);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (null != response && null != response.body() && response.body().getSuccess()) {
                    String a = "";
                    List<String> permissions = response.body().getData().getPermissions();
                    if (permissions != null) {
                        for (String permission : permissions) {
                            a += permission + "\n";
                        }
                    }
                    String username = response.body().getData().getUsername();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("permissions", a);
                    SharedPreferences.Editor edit1 = sp.edit();
                    edit1.putBoolean("logged", true).apply();
                    edit1.putString("permissions", a).apply();
                    edit1.putString("username", username);
                    edit1.commit();
//                    sp.edit().commit();
//                    sp.edit().apply();
                    startActivity(intent);

                    finish();


//                    if (roleList == null) {
//                        System.out.println("hello");
//                    }
//                    if (roleList != null)
//                    {
//                        boolean flag = false;
//                        for (String role : roleList) {
//                            if (role.equals("admin")) ;
//                            {
//                                flag = true;
//                                break;
//                            }
//                        }
                        /*if (null != response.body().getData() && flag) {
                            Toast.makeText(getApplicationContext(), "Welcome Admin !!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }}
                        else {
                            if(null != response.body().getData()) {
    //                            String[] arr = new String[]{};

                                List<String> permissionList=response.body().getData().getPermissions();
    //                            ArrayList<String> permissionsList = response.body().getData().getPermissions();
                                String listString = "";
                                for (String s : permissionList) {
                                    listString += s + "\n";
                                }
                                Intent intent = new Intent(LoginActivity.this, Permission1Activity.class);
                                intent.putExtra("string", listString);
                                startActivity(intent);
                                finish();
    //                            String rname = response.body().getData().getRoles().iterator().next().getName();
    //                            Toast.makeText(getApplicationContext(), "Welcome  " +rname + "!!", Toast.LENGTH_SHORT).show();

                            }
                        }*/

                } else {
                    Toast.makeText(getApplicationContext(), "Authentication Failure", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong !!!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }

        });
    }


}

