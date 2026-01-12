package model;
import utils.Utils;
public class MaquinaArcade {
    final int size = 3;
    private String nombreMaquina; // Nombre de la máquina
    private String generoMaquina; // Género de la máquina
    private int precioPorPartida; // Precio por partida que va a tener cada máquina
    private boolean estadoMaquina; // Estado de la máquina (activa o desactiva)
    private int contadorPartidasJugadas; // Contador de partidas jugadas de cada máquina
    private final int [] mejoresPuntuaciones; // Array con las mejores puntuaciones
    private final Jugador [] mejoresJugadores; // Array con los jugadores que han hecho las mejores puntuaciones

    /**
     * Método para poder activa o desactivar la máquina
     * @param opcion Es el número con el que activa o desactiva una máquina
     */
    public void cambiarEstado (int opcion){
        if (opcion == 0) { // Si la opción introducida es igual a 0
            this.estadoMaquina = false; // Devolvemos que la máquina está en false, por lo tanto, está desactivada
        } else if (opcion == 1){ // Si la opción introducida es igual a 1
            this.estadoMaquina = true; // Devolvemos que la máquina está true, por lo tanto, está activada
        }
    }

    /**
     * Método donde se genera la puntuación de una partida, comprueba si el numero de la partida no es multiplo de 100 y actualiza el ranking de mejores jugadores con sus respectivos puntos
     * @return Devuelve la puntuación obtenida de la partida
     */
    public int nuevaPartida (Jugador jugador){
        int puntuacion = Utils.generaNumeroAleatorio(0, 9999); // Generamos una puntuación aleatoria entre el 0 y el 9999
        this.contadorPartidasJugadas++; // Incrementamos el contador de las partidas jugadas de la máquina

        if(this.contadorPartidasJugadas % 100 == 0){ // Si el contador de partidas de una máquina llega a las cien partidas
            this.estadoMaquina = false; // Cambiamos el estado de la máquina a False, por lo tanto, está desactiva
        }

        actualizarRankingMaquina(puntuacion, jugador); // Llamamos a actualizarRankingMaquina y le añadimos la puntuación obtenida en la partida y el jugador que la ha logrado
        return puntuacion; // Devolvemos la puntuación obtenida en la partida
    }

    /**
     * Método con el que actualizamos un ranking top 3 de una máquina
     * @param puntuacion La puntuación obtenida al jugar un a partida
     * @param jugador Jugador que obtiene la puntuación de la partida
     */
    public void actualizarRankingMaquina(int puntuacion, Jugador jugador) {
        boolean insertado = false;
        for (int i = 0; i < this.mejoresPuntuaciones.length; i++) { // Recorremos el array de mejores puntuaciones
            if (!insertado && puntuacion > this.mejoresPuntuaciones[i]) { // Si es True y puntuación es mayor que alguna de las tres que ya están dentro del array pasa al siguiente paso
                for (int j = this.mejoresPuntuaciones.length - 1; j > i; j--) { //
                    this.mejoresPuntuaciones[j] = this.mejoresPuntuaciones[j - 1]; // Desplaza las puntuaciones una para abajo
                    this.mejoresJugadores[j] = this.mejoresJugadores[j - 1]; // Desplaza los jugadores una para abajo
                }
                this.mejoresPuntuaciones[i] = puntuacion; // Actualizamos las mejores puntuaciones
                this.mejoresJugadores[i] = jugador; // Actualizamos el jugador que ha hecho la puntuación
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