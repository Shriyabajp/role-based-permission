package com.example.permissionRoleProject.response;

public class DeleteResponse {
    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DeleteResponse(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
