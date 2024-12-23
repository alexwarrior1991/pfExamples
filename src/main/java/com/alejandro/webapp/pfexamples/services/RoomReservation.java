package com.alejandro.webapp.pfexamples.services;

import jakarta.ejb.Stateful;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Stateful
public class RoomReservation  {

    private String roomName;          // Nombre de la sala
    private LocalDateTime startTime;  // Fecha y hora de inicio de la reserva
    private Duration duration;        // Duración de la reserva

    // Configurar los detalles de la reserva
    public void setReservationDetails(String roomName, LocalDateTime startTime, Duration duration) {
        this.roomName = roomName;
        this.startTime = startTime;
        this.duration = duration;
    }

    // Obtener detalles de la reserva para usuarios
    public String getReservationDetails() {
        if (roomName == null || startTime == null || duration == null) {
            return "Reserva no configurada.";
        }
        return "Sala: " + roomName +
                ", Inicio: " + startTime +
                ", Duración: " + duration.toHours() + " horas.";
    }

    // Limpiar los datos de la reserva
    public void clearReservation() {
        this.roomName = null;
        this.startTime = null;
        this.duration = null;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
