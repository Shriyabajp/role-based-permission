package com.example.rolebasedpermission.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rolebasedpermission.R;
import com.example.rolebasedpermission.controller.IAccount;
import com.example.rolebasedpermission.model.RolePermissionDto;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.ViewHolder>{

    private IAccount iAccount;
    private com.example.rolebasedpermission.model.Callback callback;
    private List<RolePermissionDto> mProduct;
    private Context context;
    public RoleAdapter(List<RolePermissionDto> products, Context context, com.example.rolebasedpermission.model.Callback callback) {
        mProduct = products;
        this.context=context;
        this.callback=callback;
    }

    @NonNull
    @Override
    public RoleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.role_list_item, parent, false);
        return new RoleAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RolePermissionDto rolePermissionlist = mProduct.get(position);
        holder.roleName.setText("Role Name :" + rolePermissionlist.getRoleName());
       // holder.email.setText("email:" + userList.getEmail());
        holder.permissionName.setText("Permission names :" + rolePermissionlist.getPermissions());
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"clicked",Toast.LENGTH_LONG).show();
//                callback.delete(holder.getAdapterPosition());
//            }
//        });
//        holder.update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, UpdateActivity.class);
//
//                intent.putExtra("user",  "userList");
//                intent.putExtra("user", (Serializable)userList);
//                intent.putExtra("position",holder.getAdapterPosition());
//                context.startActivity(intent);
//
//            }
//        });
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
    }

    @Override
    public int getItemCount() {
        // Set the list count
        return mProduct.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView roleName;


        private TextView permissionName;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
                roleName= itemView.findViewById(R.id.user_name);
                permissionName = itemView.findViewById(R.id.user_permission_name);
                imageView = itemView.findViewById(R.id.cart_View);
        }
    }




}