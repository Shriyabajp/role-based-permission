package com.example.permissionRoleProject.model;

public class ResponseBean<T> extends CommonResponse {

    private T data;

    public ResponseBean(T data) {
        super();
        this.data = data;
    }

    public ResponseBean(){

    }

    public ResponseBean(T data,boolean success,String message){
        super(success,message);
        this.data=data;
    }

    public ResponseBean(boolean success,String message){
        super(success,message);

    }

    public T getData() {
        return data;
    }



    public void setData(T data) {
        this.data = data;
    }
}
