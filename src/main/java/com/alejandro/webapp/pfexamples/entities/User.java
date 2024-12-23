package com.alejandro.webapp.pfexamples.entities;

public class User {

    private String name;
    private boolean blocked;

    public User(String name, boolean blocked) {
        this.name = name;
        this.blocked = blocked;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
