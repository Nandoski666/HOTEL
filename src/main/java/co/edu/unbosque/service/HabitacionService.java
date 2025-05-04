package co.edu.unbosque.service;

import java.util.List;
import co.edu.unbosque.dao.HabitacionDAO;
import co.edu.unbosque.entity.Habitacion;

public class HabitacionService {

    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    /**
     * Inserta una nueva habitación.
     * @param habitacion La habitación a insertar.
     * @return true si fue insertada exitosamente.
     */
    public boolean insertar(Habitacion habitacion) {
        return habitacionDAO.insertar(habitacion);
    }

    /**
     * Consulta todas las habitaciones registradas.
     * @return Lista de habitaciones.
     */
    public List<Habitacion> consultarTodos() {
        return habitacionDAO.consultarTodos();
    }

    /**
     * Busca una habitación por su número.
     * @param numero Número de la habitación.
     * @return La habitación encontrada, o null si no existe.
     */
    public Habitacion buscarPorNumero(int numero) {
        return habitacionDAO.buscarPorNumero(numero);
    }

    /**
     * Elimina una habitación por su número.
     * @param numero Número de la habitación.
     * @return true si fue eliminada correctamente.
     */
    public boolean eliminar(int numero) {
        return habitacionDAO.eliminar(numero);
    }
}
