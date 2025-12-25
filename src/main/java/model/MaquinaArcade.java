package model;
import utils.Utils;
public class MaquinaArcade {
    final int size = 3;
    private String nombreMaquina;
    private String generoMaquina;
    private int precioPorPartida;
    private boolean estadoMaquina;
    private int contadorPartidasJugadas;
    private final int [] mejoresPuntuaciones;
    private final Jugador [] mejoresJugadores;

    /**
     * Método para poder activa o desactivar la máquina
     * @param opcion Es el número con el que activa o desactiva una máquina
     */
    public void cambiarEstado (int opcion){
        if (opcion == 0) {
            this.estadoMaquina = false;
        } else if (opcion == 1){
            this.estadoMaquina = true;
        }
    }

    /**
     * Método donde se imprime el estado de la máquina
     */
    public void imprimirEstado (){
        System.out.println(this.estadoMaquina);
    }

    /**
     * Método donde se genera la puntuación de una partida, comprueba si el numero de la partida no es multiplo de 100 y actualiza el ranking de mejores jugadores con sus respectivos puntos
     * @return Devuelve la puntuación obtenida de la partida
     */
    public int nuevaPartida (Jugador jugador){
        int puntuacion = Utils.generaNumeroAleatorio(0, 9999);
        this.contadorPartidasJugadas++;

        if(this.contadorPartidasJugadas % 100 == 0){
            this.estadoMaquina = false;
        }

        rankingMaquina(puntuacion, jugador);
        return puntuacion;
    }

    public void rankingMaquina(int puntuacion, Jugador jugador) {
        boolean insertado = false;
        for (int i = 0; i < mejoresPuntuaciones.length; i++) {
            if (!insertado && puntuacion > mejoresPuntuaciones[i]) {
                for (int j = mejoresPuntuaciones.length - 1; j > i; j--) {
                    mejoresPuntuaciones[j] = mejoresPuntuaciones[j - 1]; // Desplaza las puntuaciones una para abajo
                    mejoresJugadores[j] = mejoresJugadores[j - 1]; // Desplaza los jugadores una para abajo
                }
                mejoresPuntuaciones[i] = puntuacion;
                mejoresJugadores[i] = jugador;
                insertado = true;
            }
        }
    }

    /**
     * Constructor con los valores ya introducidos
     * @param nombreMaquina El nombre de la máquina
     * @param generoMaquina El género de la máquina
     * @param precioPorPartida El precio por partida de cada máquina
     */
    public MaquinaArcade (String nombreMaquina, String generoMaquina, int precioPorPartida){
        this.generoMaquina = generoMaquina;
        this.precioPorPartida = precioPorPartida;
        this.nombreMaquina = nombreMaquina;

        this.mejoresPuntuaciones = new int[size];
        this.mejoresJugadores = new Jugador[size];
    }

    /**
     * Método con el imprimimos por pantalla los atributos de la clase MaquinaArcade
     * @return Devuelve el texto ya preparado
     */
    public String toString (){
        String texto = "Nombre de la máquina: " + this.nombreMaquina +
                    "\nGénero de la máquina: " + this.generoMaquina +
                    "\nPrecio por partida: " + this.precioPorPartida +
                    "\nContador de partidas: " + this.contadorPartidasJugadas +
                    "\nEstado de la máquina: " + this.estadoMaquina +
                    "\nRanking de mejores jugadores:\n";

        for (int i = 0; i < mejoresPuntuaciones.length; i++) {  // Si el jugador o la puntuación no existen, lo saltamos
            if (mejoresJugadores[i] != null && mejoresPuntuaciones[i] > 0) {
                texto += (i + 1) + ". " + mejoresJugadores[i] + " --> " + mejoresPuntuaciones[i] + "\n";
            }
        }

        return texto;
    }


    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public int getSize() {
        return size;
    }

    public String getGenero() {
        return generoMaquina;
    }

    public void setGenero(String genero) {
        this.generoMaquina = genero;
    }

    public int getPrecioPorPartida() {
        return precioPorPartida;
    }

    public void setPrecioPorPartida(int precioPorPartida) {
        this.precioPorPartida = precioPorPartida;
    }

    public boolean EstadoMaquina() {
        return estadoMaquina;
    }

    public void setEstadoMaquina(boolean estadoMaquina) {
        this.estadoMaquina = estadoMaquina;
    }

    public int getContadorPartidasJugadas() {
        return contadorPartidasJugadas;
    }

    public int[] getMejoresPuntuaciones() {
        return mejoresPuntuaciones;
    }

    public Jugador[] getMejoresJugadores() {
        return mejoresJugadores;
    }



}