package model;
public class Jugador {
    private String nombre;
    private String idUnico;
    private int creditosDisponibles;
    private int numeroPartidasJugadas;

    public Jugador (String nombre, String idUnico){
        this.nombre = nombre;
        this.idUnico = idUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPartidasJugadas() {
        return numeroPartidasJugadas;
    }

    public void setNumeroPartidasJugadas(int numeroPartidasJugadas) {
        this.numeroPartidasJugadas = numeroPartidasJugadas;
    }

    public int getCreditosDisponibles() {
        return creditosDisponibles;
    }

    public void setCreditosDisponibles(int creditosDisponibles) {
        this.creditosDisponibles = creditosDisponibles;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(String idUnico) {
        this.idUnico = idUnico;
    }
}
