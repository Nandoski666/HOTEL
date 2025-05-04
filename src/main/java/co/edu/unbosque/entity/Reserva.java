package co.edu.unbosque.entity;

import java.util.Date;

public class Reserva {

	private int id;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private Cliente cliente;
	private Habitacion habitacion;
	private int numPersonas;
	
	// Constructor existente con argumentos
	public Reserva(int id, Date fechaInicio, Date fechaFin, Cliente cliente, Habitacion habitacion, int numPersonas) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = "Pendiente";
		this.cliente = cliente;
		this.habitacion = habitacion;
	}
	
    public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	// Constructor por defecto (sin argumentos)
    public Reserva() {
    }

	// Getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
}
