package com.example.permissionRoleProject.model;

public enum EnumRoles {
    Admin("admin"),Manager("manager"),Employee("employee"),Intern("intern");

    private String name;
    EnumRoles(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public static EnumRoles getEnumFromString(String value){

        for( EnumRoles enumRoles:EnumRoles.values()){
            if(enumRoles.getName().equalsIgnoreCase(value)){
                return enumRoles;
            }
        }
        return null;
    }
}
