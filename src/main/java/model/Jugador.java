package model;
public class Jugador {
    private String nombre;
    private int idUnico;
    private int creditosDisponibles;
    private int numeroPartidasJugadas;

    public Jugador (int idUnico){
        this.idUnico = idUnico;
    }

    public Jugador (String nombre,  int creditosDisponibles){
        this.nombre = nombre;
        this.creditosDisponibles = creditosDisponibles;
    }

    public void generaId (){
        idUnico++;
    }

    /**
     * Método con el que sumamos más creditos al jugador
     * @param sumaCreditos El número de creditos que queremos sumar a los créditos totales
     */
    public void recargarCreditos (int sumaCreditos) {
        this.creditosDisponibles += sumaCreditos; // Sumamos los creditos introducidos por el usuario al total de creditos del jugador
    }

    /**
     * Método con el que restamos los créditos de un jugador
     * @param precioPorPartida Son los créditos que cuesta una partida dependiendo de la máquina escogida
     */
    public void gastarCreditos (int precioPorPartida){
        if(this.creditosDisponibles > precioPorPartida) { // Comprobamos que los creditos de un jugador son suficientes para poder jugar a la máquina elegida
            this.creditosDisponibles -= precioPorPartida; // Restamos los creditos de la partida a los creditos totales del jugador
        }
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

    public int getIdUnico() {
        return idUnico;
    }

}
