package co.edu.unbosque.dao;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.entity.Reserva;
import co.edu.unbosque.persistence.BaseDatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ReservaDAO {

	public ReservaDAO() {
		
	}
	public List<Reserva> obtenerReservasPorEmail(String email) {
	    List<Reserva> resultado = new ArrayList<>();
	    for (Reserva r : BaseDatos.tablaReserva) {
	        if (r.getCliente() != null && r.getCliente().getEmail().equalsIgnoreCase(email)) {
	            resultado.add(r);
	        }
	    }
	    return resultado;
	}

	
	public boolean insertar(Reserva reservaNueva) {
		return BaseDatos.tablaReserva.add(reservaNueva);
	}
	
	public boolean eliminar(Integer reservaBorrar) {
	    for (int i = 0; i < BaseDatos.tablaReserva.size(); i++) {
	        Reserva est = BaseDatos.tablaReserva.get(i);
	        if (est.getId() == reservaBorrar) {
	            BaseDatos.tablaReserva.remove(i);
	            return true;
	        }
	    }
	    return false;
	}
		public ArrayList<Reserva> consultarTodos(){
		return BaseDatos.tablaReserva;
		
		
	}
}
