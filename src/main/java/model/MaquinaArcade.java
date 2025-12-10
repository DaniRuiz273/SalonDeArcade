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
    private Jugador nombreJugador;

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
    public int nuevaPartida (){
        int puntuacion = Utils.generaNumeroAleatorio(0, 9999);
        this.contadorPartidasJugadas++;

        if(this.contadorPartidasJugadas % 100 == 0){
            this.estadoMaquina = false;
        }
        if(puntuacion > mejoresPuntuaciones[0]){
            mejoresPuntuaciones[2] = mejoresPuntuaciones[1];
            mejoresPuntuaciones[1] = mejoresPuntuaciones[0];
            mejoresPuntuaciones[0] = puntuacion;
        } else if (puntuacion> mejoresPuntuaciones[1]) {
            mejoresPuntuaciones[1] = mejoresPuntuaciones[0];
            mejoresPuntuaciones[1] = puntuacion;
        } else if (puntuacion > mejoresPuntuaciones[2]) {
            mejoresPuntuaciones[2] = puntuacion;
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
        this.mejoresJugadores = new Jugador[size];
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

        for (int i = 0; i < this.mejoresPuntuaciones.length; i++){
            texto += (i + 1) + "º --> " + this.mejoresPuntuaciones[i] + "\n";
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