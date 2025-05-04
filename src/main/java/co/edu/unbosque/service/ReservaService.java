package co.edu.unbosque.service;

import java.util.List;
import co.edu.unbosque.dao.ReservaDAO;
import co.edu.unbosque.entity.Reserva;

public class ReservaService {
    
    private ReservaDAO reservaDAO = new ReservaDAO();
    
    public boolean insertar(Reserva reservaNueva) {
        return reservaDAO.insertar(reservaNueva);
    }
    
    public boolean eliminar(Integer idReserva) {
        return reservaDAO.eliminar(idReserva);
    }
    
    public List<Reserva> consultarTodos() {
        return reservaDAO.consultarTodos();
    }
    

    public List<Reserva> consultarReservasPorEmail(String email) {
        return reservaDAO.obtenerReservasPorEmail(email);
    }


}
