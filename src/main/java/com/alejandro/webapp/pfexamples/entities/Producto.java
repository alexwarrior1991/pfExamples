package com.alejandro.webapp.pfexamples.entities;

import java.util.Objects;

public class Producto {

    private Integer id;
    private String name;

    public Producto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
