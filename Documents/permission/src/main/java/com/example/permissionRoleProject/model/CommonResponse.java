package com.example.permissionRoleProject.model;

public class CommonResponse {
   private String message;
   private boolean success;
   private String statusCode;

   public CommonResponse(boolean success,String message) {
        this.success = success;
        this.message=message;
    }

    public CommonResponse(){
        this.success=true;
        this.message="Ok";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
