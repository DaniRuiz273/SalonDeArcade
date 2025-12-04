package model;
public class Jugador {
    private String nombre;
    private String idUnico;
    private int creditosDisponibles;
    private int numeroPartidasJugadas;

    public Jugador (String nombre, String idUnico, int creditosDisponibles){
        this.nombre = nombre;
        this.idUnico = idUnico;
        this.creditosDisponibles = creditosDisponibles;
    }

    public void recargarCreditos (int sumaCreditos) {
        this.creditosDisponibles += sumaCreditos;
    }

    public void gastarCreditos (int precioPorPartida){
        if(this.creditosDisponibles > precioPorPartida) {
            this.creditosDisponibles -= precioPorPartida;
        }
    }

    public void incrementarNumeroPartidas () {
            this.numeroPartidasJugadas++;
    }

    public String toString() {
        return "\n Nombre: " + this.nombre +
                "\n ID: " + this.idUnico +
                "\n Créditos disponibles: " + this.creditosDisponibles +
                "\n Numero de partidas jugadas: " + this.numeroPartidasJugadas;
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
