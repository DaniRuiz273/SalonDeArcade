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

    /**
     * Método con el que actualizamos un ranking top 3 de una máquina
     * @param puntuacion La puntuación obtenida al jugar un a partida
     * @param jugador Jugador que obtiene la puntuación de la partida
     */
    public void rankingMaquina(int puntuacion, Jugador jugador) {
        boolean insertado = false;
        for (int i = 0; i < this.mejoresPuntuaciones.length; i++) {
            if (!insertado && puntuacion > this.mejoresPuntuaciones[i]) {
                for (int j = this.mejoresPuntuaciones.length - 1; j > i; j--) {
                    this.mejoresPuntuaciones[j] = this.mejoresPuntuaciones[j - 1]; // Desplaza las puntuaciones una para abajo
                    this.mejoresJugadores[j] = this.mejoresJugadores[j - 1]; // Desplaza los jugadores una para abajo
                }
                this.mejoresPuntuaciones[i] = puntuacion;
                this.mejoresJugadores[i] = jugador;
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

        for (int i = 0; i < this.mejoresPuntuaciones.length; i++) {
            texto  += (i + 1) + ". ";
            if (this.mejoresJugadores[i] != null) {
                texto += this.mejoresJugadores[i].getNombre() + " - " + this.mejoresPuntuaciones[i] + " puntos\n";
            } else {
                texto += "---\n";
            }
        }
        return texto;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public int getPrecioPorPartida() {
        return precioPorPartida;
    }

    public boolean EstadoMaquina() {
        return estadoMaquina;
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

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public void setGeneroMaquina(String generoMaquina) {
        this.generoMaquina = generoMaquina;
    }

    public void setPrecioPorPartida(int precioPorPartida) {
        if(!Utils.precioValido(precioPorPartida)){
            return;
        }
        this.precioPorPartida = precioPorPartida;
    }
}