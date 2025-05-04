package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.entity.Habitacion;
import co.edu.unbosque.entity.HabitacionDoble;
import co.edu.unbosque.entity.HabitacionSimple;
import co.edu.unbosque.entity.Reserva;
import co.edu.unbosque.service.ReservaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("reservaMB")
@SessionScoped
public class ReservaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private ReservaService reservaService = new ReservaService();
    private ArrayList<Reserva> listaReservas;
    private Reserva reservaNueva;
    private String tipoHabitacion;
    private int numPersonas;
    private Cliente cliente;

    public ReservaMB() {
        reservaNueva = new Reserva();
    }

    @PostConstruct
    public void init() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Object clienteSesion = context.getExternalContext().getSessionMap().get("cliente");
            if (clienteSesion instanceof Cliente) {
                cliente = (Cliente) clienteSesion;
                if (cliente.getEmail() != null && !cliente.getEmail().isEmpty()) {
                    var reservas = reservaService.consultarReservasPorEmail(cliente.getEmail());
                    listaReservas = (reservas != null)
                        ? new ArrayList<>(reservas)
                        : new ArrayList<>();
                } else {
                    listaReservas = new ArrayList<>();
                }
            } else {
                listaReservas = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            listaReservas = new ArrayList<>();
        }
    }

    public String guardarReservaNueva() {
        // Validación simple de fechas
        if (reservaNueva.getFechaInicio() == null || reservaNueva.getFechaFin() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar ambas fechas"));
            return null;
        }

        // Validación del tipo de habitación seleccionado
        if (tipoHabitacion == null || tipoHabitacion.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar el tipo de habitación"));
            return null;
        }

        // Validación del número de personas
        if (numPersonas <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe indicar al menos una persona"));
            return null;
        }

        // Creación de la habitación según el tipo
        Habitacion habitacion;
        if ("Simple".equals(tipoHabitacion)) {
            habitacion = new HabitacionSimple(0, 0, 0, false);
        } else if ("Doble".equals(tipoHabitacion)) {
            habitacion = new HabitacionDoble(0, 0, 0, false);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Tipo de habitación no válido"));
            return null;
        }

        reservaNueva.setHabitacion(habitacion);
        reservaNueva.setId(generarNuevoId());
        reservaNueva.setCliente(cliente);
        reservaNueva.setNumPersonas(numPersonas);

        boolean resultado = reservaService.insertar(reservaNueva);
        if (resultado) {
            listaReservas = new ArrayList<>(reservaService.consultarReservasPorEmail(cliente.getEmail()));
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reserva registrada correctamente"));
            // Limpiar para próxima reserva
            reservaNueva = new Reserva();
            tipoHabitacion = null;
            numPersonas = 0;
            return "/reservas/listaReservas.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la reserva"));
        return null;
    }

    private int generarNuevoId() {
        if (listaReservas == null || listaReservas.isEmpty()) {
            return 1;
        }
        return listaReservas.stream().mapToInt(Reserva::getId).max().orElse(0) + 1;
    }

    // Getters y setters

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public Reserva getReservaNueva() {
        return reservaNueva;
    }

    public void setReservaNueva(Reserva reservaNueva) {
        this.reservaNueva = reservaNueva;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
