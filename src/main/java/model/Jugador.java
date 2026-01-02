package model;
public class Jugador {
    private String nombre;
    private final int idUnico;
    private static int idContador = 1;
    private int creditosDisponibles;
    private int numeroPartidasJugadas;

    public Jugador (String nombre,  int creditosDisponibles){
        this.nombre = nombre;
        this.creditosDisponibles = creditosDisponibles;
        this.idUnico = idContador;
        idContador++;
    }

    /**
     * Método con el que sumamos más creditos al jugador
     * @param cantidad El número de creditos que queremos sumar a los créditos totales
     */
    public boolean recargarCreditos(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }

        if (cantidad % 10 != 0 && cantidad % 10 != 5) {
            return false;
        }

        this.creditosDisponibles += cantidad;
        return true;
    }


    /**
     * Método con el que controlamos los créditos que se gastan de un jugador
     * @param cantidad Es el número de créditos que se van a descontar de los créditos disponibles del jugador
     * @return True si se han descontado los créditos y False si no
     */
    public boolean gastarCreditos(int cantidad) {
        if (cantidad <= 0 || cantidad > this.creditosDisponibles) {
            return false;
        }
        this.creditosDisponibles -= cantidad;
        return true;
    }


    /**
     * Método con el incrementamos el número de partidas que un jugador ha jugado
     */
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

    public int getNumeroPartidasJugadas() {
        return numeroPartidasJugadas;
    }

    public int getCreditosDisponibles() {
        return creditosDisponibles;
    }

    public void setCreditosDisponibles(int creditosDisponibles) {
        this.creditosDisponibles = creditosDisponibles;
    }

    public int getIdUnico() {
        return idUnico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}