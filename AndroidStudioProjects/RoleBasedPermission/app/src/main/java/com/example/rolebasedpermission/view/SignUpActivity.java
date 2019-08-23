package com.example.rolebasedpermission.view;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.AppController;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.RoleResponse;
import com.example.rolebasedpermission.model.SignUpResponse;
import com.example.rolebasedpermission.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.rolebasedpermission.controller.Urls.serverUrl;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private EditText email, userName,password,roleName,confirmPassword;
    private CheckBox checkAdmin,checkManager,checkEmployee,checkIntern;
    private IAccount iAccount;
    private ProgressDialog progressDialog;
    private ArrayAdapter<String> adapter;
    private LinearLayout linearLayout;
    private Spinner dropdown;
    private TextView roleButton;
    private int x=0;
    private Context mcontext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        email = findViewById(R.id.et_email);
        userName= findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        confirmPassword=findViewById(R.id.et_confirmPassword);
        linearLayout=findViewById(R.id.linear_layout_role);
        roleButton=findViewById(R.id.bt_role_assign);

        displayAllRoles();
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.bt_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isValidEmail(email.getText()))
                    email.setError("Enter valid email");
                if(!isValidPassword(password.getText().toString()))
                    password.setError("Password shud not be null");
                if(!confirmPassword.getText().toString().equals(password.getText().toString()))
                    confirmPassword.setError("Passwords not matching");

                if(userName.getText().toString().length() == 0)
                    userName.setError("User Name cannot be empty");

                if (isValidPassword(password.getText().toString())  && confirmPassword.getText().toString().equals(password.getText().toString())) {
                    User newaccount = new User();
                    newaccount.setEmail(email.getText().toString());
                    newaccount.setUserName(userName.getText().toString());
                    newaccount.setPassword(password.getText().toString());
                    List<String> roleList=new ArrayList<>();
                    int childCount=linearLayout.getChildCount();
                    for (int i=0; i < childCount; i++){
                        Object v = linearLayout.getChildAt(i);
                        if(v instanceof CheckBox){
                            if(((CheckBox)v).isChecked()){
                                roleList.add(((CheckBox)v).getText().toString());}

                        }
                    }
                    newaccount.setRoleNames(roleList);
//                    newaccount.setEnabled(enabled.get);
                    if((TextUtils.isEmpty(email.getText())) || (TextUtils.isEmpty(userName.getText())) || (TextUtils.isEmpty(password.getText())) ){
                    Toast.makeText(getApplicationContext(), "Fields cant be empty !!!", Toast.LENGTH_SHORT).show();}
                    else {
                    addAccount(newaccount);}
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
                    List<String>roles=response.body().getData().getRolenames();
                    String[] items = new String[roles.size()];
                    String s="";
                    for(int i=0;i<roles.size();i++){
                        items[i]=roles.get(i);
                    }

                    CheckBox[] checkBoxes = new CheckBox[roles.size()];
                    for (int i = 0; i < roles.size(); i++) {
                        checkBoxes[i] = new CheckBox(SignUpActivity.this);
                        checkBoxes[i].setText(roles.get(i));
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

    public void addAccount(User user) {
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
                        Toast.makeText(getApplicationContext(), "User saved successfully !!!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
//                        startActivity(intent);
                        finish();
                }else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

        public boolean xisValidPassword(String password) {
            return password != null;
        }
}
