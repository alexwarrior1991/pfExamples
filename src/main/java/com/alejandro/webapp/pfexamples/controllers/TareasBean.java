package com.alejandro.webapp.pfexamples.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TareasBean implements Serializable {

    private String tarea;
    private String descripcion;
    private List<Tarea> tareas;

    public TareasBean() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea() {
        if(tarea != null && !tarea.isEmpty()) {
            tareas.add(new Tarea(tarea, descripcion));
            tarea = "";
            descripcion = "";
        }
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public static class Tarea {
        private String nombre;
        private String descripcion;

        public Tarea(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }
}
