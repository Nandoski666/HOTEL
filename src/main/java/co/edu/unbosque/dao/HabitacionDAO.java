package co.edu.unbosque.dao;

import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.entity.Habitacion;

public class HabitacionDAO {

    // Lista que simula la "tabla" de habitaciones
    private List<Habitacion> habitaciones = new ArrayList<>();

    /**
     * Inserta una nueva habitación.
     * @param habitacion la habitación a insertar.
     * @return true si se agregó correctamente.
     */
    public boolean insertar(Habitacion habitacion) {
        return habitaciones.add(habitacion);
    }

    /**
     * Retorna una copia de la lista de habitaciones.
     * @return lista de habitaciones.
     */
    public List<Habitacion> consultarTodos() {
        return new ArrayList<>(habitaciones);
    }

    /**
     * Busca una habitación por su número.
     * @param numero el número de habitación.
     * @return la habitación encontrada o null si no existe.
     */
    public Habitacion buscarPorNumero(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    /**
     * Elimina una habitación por su número.
     * @param numero el número de la habitación a eliminar.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean eliminar(int numero) {
        Habitacion habitacion = buscarPorNumero(numero);
        if (habitacion != null) {
            return habitaciones.remove(habitacion);
        }
        return false;
    }
}
