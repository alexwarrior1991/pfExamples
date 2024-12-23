package com.alejandro.webapp.pfexamples.controllers;

import com.alejandro.webapp.pfexamples.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {

    private List<User> users; // Lista de usuarios en el sistema

    @PostConstruct
    public void init() {
        // Inicializamos con datos de usuarios ficticios
        users = new ArrayList<>();
        users.add(new User("Juan Pérez", false));  // Usuario activo
        users.add(new User("Ana Gómez", true));    // Usuario bloqueado
        users.add(new User("Luis Fernández", false)); // Usuario activo
    }

    public List<User> getUsers() {
        return users;
    }

    // Bloquear un usuario
    public void blockUser(User user) {
        if (user != null && !user.isBlocked()) {
            user.setBlocked(true);
            sendAjaxCallback(user.getName(), "ha sido bloqueado exitosamente");
        } else {
            sendAjaxCallback(null, null); // Sin parámetros si ocurre un error
        }
    }

    // Desbloquear un usuario
    public void unblockUser(User user) {
        if (user != null && user.isBlocked()) {
            user.setBlocked(false); // Cambia el estado del usuario
            sendAjaxCallback(user.getName(), "ha sido desbloqueado exitosamente.");
        } else {
            sendAjaxCallback(null, null); // Sin parámetros si ocurre un error
        }
    }

    // Enviar datos AJAX personalizados al cliente
    private void sendAjaxCallback(String userName, String message) {
        PrimeFaces.current().ajax().addCallbackParam("username", userName);
        PrimeFaces.current().ajax().addCallbackParam("actionMessage", message);
    }
}
