package co.edu.unbosque.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Habitacion {
    protected int numero;
    protected String tipo;
    protected int capacidad;
    protected double precioNoche;
    protected List<Reserva> reservas;

    public Habitacion(int numero, String tipo, int capacidad, double precioNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.reservas = new ArrayList<>();
    }

    public boolean esDisponible(Date fechaInicio, Date fechaFin) {
        for (Reserva r : reservas) {
            Date inicioReservado = r.getFechaInicio();
            Date finReservado = r.getFechaFin();

            // Verifica si hay superposición de fechas
            if (!(fechaFin.before(inicioReservado) || fechaInicio.after(finReservado))) {
                return false; // Hay conflicto de fechas
            }
        }
        return true; // No hay conflictos, está disponible
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
