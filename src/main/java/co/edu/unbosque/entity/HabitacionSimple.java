package co.edu.unbosque.entity;

public class HabitacionSimple extends Habitacion {
    private boolean extra;

    // Constructor sin parámetro "tipo"; se fija como "Simple"
    public HabitacionSimple(int numero, int capacidad, double precioNoche, boolean extra) {
        super(numero, "Simple", capacidad, precioNoche);
        this.extra = extra;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
