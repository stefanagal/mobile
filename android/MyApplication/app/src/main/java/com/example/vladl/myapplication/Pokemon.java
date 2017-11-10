package com.example.vladl.myapplication;

/**
 * Created by vladl on 09/11/2017.
 */

public class Pokemon {
    String name;
    String type;
    String role;

    public Pokemon(String name, String type, String role){
        this.name = name;
        this.role = role;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
