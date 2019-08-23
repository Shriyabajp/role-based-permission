package com.example.rolebasedpermission.controller;

import com.example.rolebasedpermission.model.BaseResponse;
import com.example.rolebasedpermission.model.LoginResponse;
import com.example.rolebasedpermission.model.PermissionListResponse;
import com.example.rolebasedpermission.model.PermissionResponseDto;
import com.example.rolebasedpermission.model.RolePermissionResponse;
import com.example.rolebasedpermission.model.RoleResponse;
import com.example.rolebasedpermission.model.SignUpResponse;
import com.example.rolebasedpermission.model.UserListResponse;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IAccount {
    @POST(Urls.login)
    Call<LoginResponse> doLogin(@Body WeakHashMap<Object,Object> weakHashMap);

    @POST(Urls.signUp)
    Call<SignUpResponse> add(@Body WeakHashMap<Object,Object> weakHashMap);

    @GET(Urls.getAllUsers)
    Call<UserListResponse> getAll();

    @DELETE(Urls.deleteUser)
    Call<BaseResponse> deleteUser(@Query("username") String username);

    @GET(Urls.getAllRoles)
    Call<RoleResponse> getAllRoles();

    @PUT(Urls.updateUser)
    Call<SignUpResponse> updateUser(@Body WeakHashMap<Object,Object> weakHashMap);

    @POST(Urls.addPermissions)
    Call<PermissionResponseDto> addPermissions(@Body WeakHashMap<Object,Object> weakHashMap);

    @POST(Urls.addRolesPermissions)
    Call<RolePermissionResponse> addRolePermissions(@Body WeakHashMap<Object,Object> weakHashMap);

    @GET(Urls.getAllPermissions)
    Call<PermissionListResponse> getAllPermissions();

    @GET(Urls.getAllRolesPermissions)
    Call<RolePermissionResponse> getAllRolesWithPermission();

}
