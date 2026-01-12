package model;
public class Jugador {
    private String nombre; // Nombre del jugador
    private final int idUnico; // Identificador único que va a tener cada jugador
    private static int idContador = 1; // Contador de identificadores únicos, inicializado en 1 para que el primer jugador no tenga el ID 0
    private int creditosDisponibles; // Los créditos disponibles que va a tener cada jugador
    private int numeroPartidasJugadas; // Contador del número de partidas jugadas de cada jugador

    /**
     * Constructor con los valores ya introducidos
     * @param nombre Nombre que va a recibir el jugador
     * @param creditosDisponibles Créditos que va a tener cada jugador establecido por el usuario
     */
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
        if (cantidad <= 0) { // Si la cantidad introducida es menor o igual a 0
            return false; // Devolvemos false, por lo que no vamos a poder recargar más créditos
        }

        if (cantidad % 10 != 0 && cantidad % 10 != 5) { // Comprobamos que los créditos siempre acaben en 0 o en 5
            return false; // Devolvemos False si no se ha introducido una cantidad acabada en 0 o en 5
        }

        this.creditosDisponibles += cantidad; // Añadimos la cantidad introducida a los créditos disponibles del jugador
        return true; // Devolvemos true si se han añadido correctamente
    }

    /**
     * Método con el que controlamos los créditos que se gastan de un jugador
     * @param cantidad Es el número de créditos que se van a descontar de los créditos disponibles del jugador
     * @return True si se han descontado los créditos y False si no
     */
    public boolean gastarCreditos(int cantidad) {
        if (cantidad <= 0 || cantidad > this.creditosDisponibles) { // Comprobamos que la cantidad introducida no sea menor o igual a 0, o la cantidad introducida sea mayor que los créditos disponibles
            return false; // Devolvemos false si uno de los dos parámetro se hayan cumplido
        }
        this.creditosDisponibles -= cantidad; // Restamos la cantidad introducida a los créditos disponibles del jugador
        return true; // Devolvemos true si se han restado los créditos correctamente
    }

    /**
     * Método con el incrementamos el número de partidas que un jugador ha jugado
     */
    public void incrementarNumeroPartidas () {
            this.numeroPartidasJugadas++; // Incrementamos el número de partidas del jugador
    }

    /**
     * ToString que utilizo para cuando se imprime por pantalla los datos del jugador
     * @return Devuelve el texto que queremos que se vea por pantalla
     */
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

    public int getIdUnico() {
        return idUnico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}