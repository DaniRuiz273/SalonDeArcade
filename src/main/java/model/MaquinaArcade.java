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
     * Método para consultar el estado de la máquina actual
     */
    public void imprimirEstado (){
        System.out.println(estadoMaquina);
    }

    /**
     * Método donde se crean las nuevas partidas
     * @return Devuelve la puntuación obtenida de la partida jugada
     */
    public int nuevaPartida (){
        int puntuacion = Utils.generaNumeroAleatorio(0, 9999);
        contadorPartidasJugadas++;
        if(contadorPartidasJugadas % 100 == 0){
            estadoMaquina = false;
        }

        boolean top3 = false;
        for (int i = 0; i < mejoresPuntuaciones.length; i++) {
            if (!top3 && puntuacion > mejoresPuntuaciones[i]) {
                for (int j = mejoresPuntuaciones.length - 1; j > i; j--) { // Desplaza una posición hacia abajo
                    mejoresPuntuaciones[j] = mejoresPuntuaciones[j - 1];
                }
                mejoresPuntuaciones[i] = puntuacion;
                top3 = true; // El ranking ya está actualizado con la nueva puntuación
            }
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

    public String toString (){
        return "\n Nombre de la máquina: " + this.nombreMaquina +
                "\n Género de la máquina: " + this.generoMaquina +
                "\n Precio por partida: " + this.precioPorPartida;
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