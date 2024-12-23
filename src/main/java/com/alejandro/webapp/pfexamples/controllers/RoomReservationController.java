package com.alejandro.webapp.pfexamples.controllers;

import com.alejandro.webapp.pfexamples.services.RoomManager;
import com.alejandro.webapp.pfexamples.services.RoomReservation;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Named
@SessionScoped
public class RoomReservationController implements Serializable {

    @EJB
    private RoomReservation reservation;

    @EJB
    private RoomManager manager;

    private String selectedRoom;
    private String reservationDate;  // Fecha (cadena recibida del formulario)
    private String reservationTime;  // Hora (cadena recibida del formulario)
    private int reservationDuration; // Duración en horas
    private String availabilityResult;

    // Consultar disponibilidad de las salas
    public void checkAvailability() {
        availabilityResult = manager.checkRoomAvailability();
    }

    // Configurar la reserva
    public void setupReservation() {
        try {
            // Validar campos requeridos
            if (selectedRoom == null || selectedRoom.isEmpty() ||
                    reservationDate == null || reservationDate.isEmpty() ||
                    reservationTime == null || reservationTime.isEmpty()) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Todos los campos son obligatorios."));
                return;
            }

            // Convertir fecha y hora a LocalDateTime
            LocalDate date = LocalDate.parse(reservationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime time = LocalTime.parse(reservationTime, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime startTime = LocalDateTime.of(date, time);

            Duration duration = Duration.ofHours(reservationDuration);

            // Configurar los detalles en el bean `RoomReservation`
            reservation.setReservationDetails(selectedRoom, startTime, duration);

        } catch (DateTimeParseException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Formato de fecha u hora inválido."));
        }
    }

    // Confirmar y registrar la reserva
    public void confirmReservation(){
        if (reservation == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El EJB 'reservation' no está inyectado correctamente."));
            return;
        }

        String result = manager.confirmReservation(
                reservation.getRoomName(),
                reservation.getStartTime(),
                reservation.getDuration()
        );

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación de Reserva", result));
    }

    // Getters y setters
    public String getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getReservationDuration() {
        return reservationDuration;
    }

    public void setReservationDuration(int reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    public String getAvailabilityResult() {
        return availabilityResult;
    }

    public void setAvailabilityResult(String availabilityResult) {
        this.availabilityResult = availabilityResult;
    }

    public RoomReservation getReservation() {
        return reservation;
    }

    public String getReservationDetails() {
        if (reservation == null) {
            return "No se encontraron detalles de la reserva. Verifique la inyección del EJB.";
        }
        return reservation.getReservationDetails();
    }
}
