package co.edu.unbosque.entity;

public class HabitacionDoble extends Habitacion {
    private boolean extra;

    public HabitacionDoble(int numero, int capacidad, double precioNoche, boolean extra) {
        super(numero, "Doble", capacidad, precioNoche);
        this.extra = extra;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
