
package com.example.rolebasedpermission.view;

        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.Toast;

        import com.example.rolebasedpermission.R;
        import com.example.rolebasedpermission.adapter.RoleAdapter;
        import com.example.rolebasedpermission.controller.AppController;
        import com.example.rolebasedpermission.controller.IAccount;
        import com.example.rolebasedpermission.model.RolePermissionDto;
        import com.example.rolebasedpermission.model.RolePermissionResponse;

        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import static com.example.rolebasedpermission.controller.Urls.serverUrl;


public class RoleListActivity extends AppCompatActivity  implements com.example.rolebasedpermission.model.Callback {

    List<RolePermissionDto> roleListPermissionDtos=new ArrayList<>();
    RoleAdapter roleAdapter;
    IAccount iAccount;
    private String email;
    private String productId;
    private String merchantId;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_list);
        IAccount iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        getAllRoles();

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

    private void getAllRoles(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        iAccount = AppController.getClientAuthentication(serverUrl).create(IAccount.class);
        Call<RolePermissionResponse> call = iAccount.getAllRolesWithPermission();
        call.enqueue(new Callback<RolePermissionResponse>() {
            @Override
            public void onResponse(Call<RolePermissionResponse> call, Response<RolePermissionResponse> response) {

                if (response.body() != null && response.body().getData() != null)  {
                    roleListPermissionDtos=response.body().getData().getRolePermissionDtoList();
                    if(null != roleListPermissionDtos){
                        //user
                        String s="";
                        for(int i=0;i<roleListPermissionDtos.size();i++)
                        {
                            s+=roleListPermissionDtos.get(i).getPermissions();
                        }
                        Log.d("users",s);}
                    if (response.body().getData().getRolePermissionDtoList()== null ) {

                        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RoleListActivity.this).create();
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
                        roleAdapter = new RoleAdapter(roleListPermissionDtos,RoleListActivity.this,RoleListActivity.this);
                        Toast.makeText(getApplication(), "User List is", Toast.LENGTH_SHORT).show();
                        if (null != roleAdapter) {
                            recyclerView.setAdapter(roleAdapter);
                        }
                    }
                } else {
                    Toast.makeText(getApplication(), "Invalid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RolePermissionResponse> call, Throwable t) {
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

    }
}