package model;
import utils.Utils;
public class MaquinaArcade {
    final int size = 3;
    private String nombreMaquina;
    private String generoMaquina;
    private int precioPorPartida;
    private boolean estadoMaquina;
    private int contadorPartidasJugadas;
    private int [] mejoresPuntuaciones;
    private int [] mejoresJugadores;

    /**
     * Método para poder activa o desactivar la máquina
     * @param opcion Es el número con el que activa o desactiva una máquina
     */
    public void estadoMaquina (int opcion){
        if (opcion == 0) {
            estadoMaquina = false;
        } else if (opcion == 1){
            estadoMaquina = true;
        }
    }

    /**
     * Método para consultar el estado de la máquina actual
     */
    public void estaActiva (){
        System.out.println(estadoMaquina);
    }

    /**
     * Método donde se crean las nuevas partidas
     * @param puntuacion Es la puntuación que obtienes de manera aleatoria de la partida jugada
     * @return Devuelve la puntuación obtenida de la partida jugada
     */
    public int nuevaPartida (int puntuacion){
        puntuacion = Utils.generaNumeroAleatorio(0, 9999);
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
                puntuacion = mejoresPuntuaciones[i];
                top3 = true; // El ranking ya está actualizado con la nueva puntuación
            }
        }
        return puntuacion;
    }

    public MaquinaArcade (String nombreMaquina, String generoMaquina, int precioPorPartida){
        this.generoMaquina = generoMaquina;
        this.precioPorPartida = precioPorPartida;
        this.nombreMaquina = nombreMaquina;
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

    public boolean isEstadoMaquina() {
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