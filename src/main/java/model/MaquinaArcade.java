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
    private final int [] mejoresJugadores;

    /**
     * Método para poder activa o desactivar la máquina
     * @param opcion Es el número con el que activa o desactiva una máquina
     */
    public void cambiarEstado (int opcion){
        if (opcion == 0) {
            estadoMaquina = false;
        } else if (opcion == 1){
            estadoMaquina = true;
        }
    }

    /**
     * Método donde se imprime el estado de la máquina
     */
    public void imprimirEstado (){
        System.out.println(estadoMaquina);
    }

    /**
     * Método donde se genera la puntuación de una partida
     * @return Devuelve la puntuación de la partida
     */
    public int nuevaPartida (){
        int puntuacion = Utils.generaNumeroAleatorio(0, 9999);
        contadorPartidasJugadas++;

        if(contadorPartidasJugadas % 100 == 0){
            estadoMaquina = false;
        }

        if(puntuacion > mejoresPuntuaciones[0]){ // Comparo si la puntuacion de la partida es mayor que la puntuacion de la posición 0
            mejoresPuntuaciones[2] = mejoresPuntuaciones[1]; // Paso la puntuacion de la posición 0 a la 1
            mejoresPuntuaciones[1] = mejoresPuntuaciones[0]; // Paso la puntuacion de la posición 1 a la 2
            mejoresPuntuaciones[0] = puntuacion; // La puntuacion 0 ahora pasa a ser la puntuacion de la partida
        } else if (puntuacion > mejoresPuntuaciones[1]){ // Comparo si la puntuacion de la partida es mayor que la puntuacion de la posición 1
            mejoresPuntuaciones[2] = mejoresPuntuaciones[1]; // Paso la puntuacion de la posición 1 a la 2
            mejoresPuntuaciones[1] = puntuacion; // La puntuacion 1 ahora pasa a ser la puntuacion de la partida
        } else if (puntuacion > mejoresPuntuaciones[2]) { // Comparo si la puntuacion de la partida es mayor que la puntuacion de la posición 2
            mejoresPuntuaciones[2] = puntuacion; // La puntuacion 2 ahora pasa a ser la puntuacion de la partida
        }
        return puntuacion;
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
        this.mejoresJugadores = new int[size];
    }

    /**
     * Método con el imprimimos por pantalla los atributos de la clase MaquinaArcade
     * @return Devuelve el texto ya preparado
     */
    public String toString (){
       String texto =  "\n Nombre de la máquina: " + this.nombreMaquina +
                "\n Género de la máquina: " + this.generoMaquina +
                "\n Precio por partida: " + this.precioPorPartida +
                "\n Contador de partidas: " + this.contadorPartidasJugadas +
                "\n Estado de la máquina: " + this.estadoMaquina + "\n";

        for (int i = 0; i < mejoresPuntuaciones.length; i++){
            texto += ( i + 1 ) + "º --> " + mejoresPuntuaciones[i] + "\n";
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

    public int[] getMejoresJugadores() {
        return mejoresJugadores;
    }
}