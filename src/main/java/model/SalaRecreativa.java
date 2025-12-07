package model;
import utils.Utils;
public class SalaRecreativa {
    private final int size = 5;
    private final Jugador [] jugadores;
    private final MaquinaArcade [] maquinas;
    private int capacidadJugadores;
    private int capacidadMaquinas;

    public void gestionarPartida (){

    }

    /**
     * Método con el que comprobamos cual es la máquina más utilizada en la sala
     * @return Devuelve la máquina más utilizada de la sala
     */
    public MaquinaArcade maquinaConMasPartidasJugadas (){
        MaquinaArcade maquinaMasJugada = maquinas[0]; // Pongo como la máquina más utilizada a la primera del array
        for(int i = 1; i < capacidadMaquinas; i++) {
            if (maquinas[i].getContadorPartidasJugadas() > maquinaMasJugada.getContadorPartidasJugadas()) { // Ahora comparo la primera máquina con la siguiente, si la segunda máquina no tiene más partidas sigue en el bucle hasta encontrar una máquina con más partidas o hasta que no haya más máquinas
                maquinaMasJugada = maquinas[i]; // Si hay una máquina con más partidas se cambia
            }
        }
        return maquinaMasJugada;
    }

    /**
     * Método con el que encontramos cual es el jugador más activo de la sala
     * @return Devuelve el jugador más activo de la sala
     */
    public Jugador jugadorMasActivo() {
        Jugador masActivo = jugadores[0];   // Empiezo poniendo al primer jugador como que es el más activo
        for (int i = 1; i < capacidadJugadores; i++) {
            if (jugadores[i].getNumeroPartidasJugadas() > masActivo.getNumeroPartidasJugadas()) { // Ahora comparo de uno en uno para saber si tienen más partidas jugadas que el anterior
                masActivo = jugadores[i];   // Si hay otro jugador con más partidas se cambia
            }
        }
        return masActivo;
    }


    /**
     * Método con el que solo imprimimos por pantalla las máquinas que están activas dentro de la sala
     */
    public void listarMaquinasActivas (){
        boolean estaActiva = false;
        for(int i = 0; i < capacidadMaquinas; i++){
            if(maquinas[i].EstadoMaquina()){
                System.out.println(maquinas[i]);
                estaActiva = true;
            }
        }
    }

    /**
     * Método con el que buscamos una máquina de sala por su nombre
     * @param nombre Es el nombre de la máquina que queremos buscar
     * @return Devuelve los atributos de la máquina si coincide con su nombre
     * @throws Exception Si no existe una máquina con ese nombre lanzará la excepción
     */
    public MaquinaArcade buscarNombreMaquina (String nombre) throws Exception {
        for (MaquinaArcade maquina : maquinas){ // Recorre cada máquina del array y lo llamamos máquina
            if (maquina != null && maquina.getNombreMaquina().trim().equalsIgnoreCase(nombre.trim())){ // El nombre de la máquina lo compara con el nombre de la máquina que estamos buscando
                return maquina; // Devuelve la máquina si coincide con su nombre
            }
        }
        throw new Exception ("No existe ninguna máquina con ese nombre"); // Si buscamos una máquina con un nombre que no existe lanzará esta excepción
    }

    /**
     * Método con el que podemos buscar a un jugador de la sala con su idUnico
     * @param idUnico Es el identificador único de cada jugador
     * @return Devuelve el jugador si coincide con el identificador único
     * @throws Exception Si no hay un jugador con ese idUnico lanzará esa excepción
     */
    public Jugador buscarIDJugador (String idUnico) throws Exception{
        for (Jugador jugador : jugadores){ // Recorre cada jugador del array y lo llamamos jugador
            if(jugador.getIdUnico().equals(idUnico)){ // El idUnico del jugador lo compara con el idUnico que estamos buscando
                return jugador; // Devuelve el jugador si coincide con el idUnico
            } else {
                throw new Exception ("No existe ningún jugador con esa ID"); // Si buscamos un idUnico que no existe lanzará esta excepción
            }
        }
        return null; // Si no encuentra a ningún jugador con ese ID devuelve null
    }

    /**
     * Método con el que añadimos un jugador en la sala
     * @param jugadorAdd Es el jugador que queremos añadir a la sala
     * @throws Exception Si no hay más hueco para jugadores en la sala lanzará esa excepción
     */
    public void addJugador (Jugador jugadorAdd) throws Exception {
        if(this.capacidadJugadores < size){ // Comprobamos que haya capacidad en la sala
            this.jugadores[this.capacidadJugadores] = jugadorAdd; // Si hay espacio en la sala entonces añadimos un jugador
            capacidadJugadores++; // Aumenta la capacidadActual, es decir, que ahora en la sala hay un jugador más
        }else {
            throw new Exception ("No se pueden registrar más jugadores porque no hay más espacio"); // Cuando no hay más sitio para jugadores en la sala e intentamos añadir uno lanzará la excepción
        }
    }

    /**
     * Método con el que añadimos una máquina a una sala
     * @param maquinaAdd Es la máquina que queremos añadir a la sala
     * @throws Exception Si bo hay sitio para más máquinas en la sala lanza esa excepción
     */
    public void addMaquina (MaquinaArcade maquinaAdd) throws Exception {
        if(this.capacidadMaquinas < size){ // Comprobamos que haya capacidad en la sala
            this.maquinas[this.capacidadMaquinas] = maquinaAdd; // Si hay espacio en la sala entonces añadimos un jugador
            capacidadMaquinas++; // Aumenta la capacidadActual, es decir, que ahora en la sala hay un jugador más
        }else {
            throw new Exception ("No se pueden registrar más jugadores porque no hay más espacio"); // Cuando no hay más sitio para jugadores en la sala e intentamos añadir uno lanzará la excepción
        }
    }

    public String toString() {
        String texto = "Jugadores en la sala: \n"; // Esto lista los jugadores que hay dentro de la sala por pantalla
        if(capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += jugadores[i] + "\n";
            }
        }

        texto += "Maquinas en la sala : \n"; // Esto lista a las máquinas que hay en la sala por pantalla
        if(capacidadMaquinas == 0){
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < capacidadMaquinas; i++){ // Recorre el array hasta la capacidad actual
                texto += maquinas[i] + "\n";
            }
        }
        return texto;
    }


    public SalaRecreativa (){
        this.jugadores = new Jugador[size];
        this.maquinas = new MaquinaArcade[size];

        this.capacidadJugadores = 0;
        this.capacidadMaquinas = 0;
    }

    public MaquinaArcade[] getMaquinasArcade() {
        return maquinas;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
}