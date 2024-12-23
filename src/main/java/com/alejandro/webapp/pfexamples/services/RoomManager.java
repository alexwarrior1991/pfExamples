package com.alejandro.webapp.pfexamples.services;

import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class RoomManager {

    // Estructura para almacenar las reservas: Mapa (Sala -> Lista de Reservas)
    private final Map<String, List<Reservation>> reservations = new HashMap<>();

    // Constructor: Inicializa las salas disponibles
    public RoomManager() {
        reservations.put("Sala A", new ArrayList<>());
        reservations.put("Sala B", new ArrayList<>());
        reservations.put("Sala C", new ArrayList<>());
    }

    // Consultar la disponibilidad de todas las salas
    public String checkRoomAvailability() {
        StringBuilder availability = new StringBuilder("Disponibilidad de Salas:\n");
        reservations.forEach((room, schedules) -> {
            if (schedules.isEmpty()) {
                availability.append(room).append(": Disponible todo el día\n");
            } else {
                availability.append(room).append(": Reservas existentes:\n");
                for (Reservation r : schedules) {
                    availability.append("   - Desde: ").append(r.getStartTime())
                            .append(" por ").append(r.getDuration().toHours())
                            .append(" horas.\n");

                }
            }
        });
        return availability.toString();
    }

    // Confirmar una reserva (verifica conflictos)
    public String confirmReservation(String roomName, LocalDateTime startTime, Duration duration) {
        if (!reservations.containsKey(roomName)) {
            return "La sala " + roomName + " no existe. Selecciona otra sala.";
        }

        List<Reservation> currentReservations = reservations.get(roomName);

        // Verificar conflictos con reservas existentes
        for (Reservation r : currentReservations) {
            if (conflictsWith(r, startTime, duration)) {
                return "Conflicto de horarios con una reserva existente (" +
                        r.getStartTime() + " por " + r.getDuration().toHours() + " horas).";
            }
        }

        // No hay conflictos, añadir nueva reserva
        currentReservations.add(new Reservation(startTime, duration));

        return "Reserva confirmada para " + roomName +
                " el " + startTime + " por " + duration.toHours() + " horas.";
    }

    // Método auxiliar para verificar conflictos de horarios
    private boolean conflictsWith(Reservation existing, LocalDateTime newStart, Duration newDuration) {
        LocalDateTime existingEnd = existing.getStartTime().plus(existing.getDuration());
        LocalDateTime newEnd = newStart.plus(newDuration);
        return newStart.isBefore(existingEnd) && existing.getStartTime().isBefore(newEnd);
    }

    private static class Reservation {
        private final LocalDateTime startTime;
        private final Duration duration;

        public Reservation(LocalDateTime startTime, Duration duration) {
            this.startTime = startTime;
            this.duration = duration;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public Duration getDuration() {
            return duration;
        }
    }
}
