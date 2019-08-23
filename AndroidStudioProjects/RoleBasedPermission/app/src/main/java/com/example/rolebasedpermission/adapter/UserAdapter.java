package com.example.rolebasedpermission.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.UserDto;
import com.example.rolebasedpermission.view.UpdateActivity;

import java.io.Serializable;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private IAccount iAccount;
    private com.example.rolebasedpermission.model.Callback callback;
    private List<UserDto> mProduct;
    private Context context;
    private SharedPreferences sharedPreferences;

    public UserAdapter(List<UserDto> products, Context context, com.example.rolebasedpermission.model.Callback callback) {
        mProduct = products;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_view, parent, false);
        return new UserAdapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final UserDto userList = mProduct.get(position);

        holder.userName.setText("UserName :" + userList.getUserName());
        if (holder.userName.getText().toString().equals(holder.username)) {
            holder.delete.setVisibility(View.INVISIBLE);
        }
        else{

            holder.userName.setText("UserName :" + userList.getUserName());
        holder.email.setText("email:" + userList.getEmail());
        holder.roleName.setText("Role name :" + userList.getRoleNames());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "clicked", Toast.LENGTH_LONG).show();
                callback.delete(holder.getAdapterPosition());
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("user", "userList");
                intent.putExtra("user", (Serializable) userList);
                intent.putExtra("position", holder.getAdapterPosition());
                context.startActivity(intent);

            }
        });
//        holder.update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"clicked",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(context, UpdateActivity.class);
//
//                intent.putExtra("user",  userList);
//                intent.putExtra("position",holder.getAdapterPosition());
//                context.startActivity(intent);
//
//            }
//        });
//    }
    }}

    @Override
    public int getItemCount() {
        // Set the list count
        return mProduct.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView email;
        private Button delete;
        private Button update;
        private TextView roleName;
        private String username;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
            userName = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.user_email);
            roleName = itemView.findViewById(R.id.user_role_name);
            delete = itemView.findViewById(R.id.bt_delete);
            imageView = itemView.findViewById(R.id.cart_View);
            update = itemView.findViewById(R.id.bt_update);
            username = sharedPreferences.getString("username", null);

        }
    }


}