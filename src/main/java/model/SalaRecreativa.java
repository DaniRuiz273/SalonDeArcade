package model;
import utils.Utils;
public class SalaRecreativa {
    private final int size = 5;
    private final Jugador [] jugadores; // Array donde se guardan los jugadores de la sala
    private final MaquinaArcade [] maquinas; // Array donde se guardan las máquinas de la sala
    private int capacidadJugadores; // Son los jugadores que hay actualmente dentro de la sala
    private int capacidadMaquinas; // Son las máquinas que hay actualmente dentro de la sala

    /**
     * Método donde podemos gestionar una partida dentro de la sala
     * @param jugador El jugador que va a jugar la partida
     * @param maquina La máquina donde el jugador va a jugar
     * @throws Exception Si la máquina no está activada y el jugador no tiene créditos suficientes para jugar una partida lanzará esta excepción
     */
    public void gestionarPartida (Jugador jugador, MaquinaArcade maquina) throws Exception {
        if(maquina.EstadoMaquina()){ // Primero comprobamos que la máquina esté activada
            if(jugador.getCreditosDisponibles() >= maquina.getPrecioPorPartida()){ // Ahora comprobamos que el jugador tengo créditos suficientes para poder jugar una partida
                jugador.gastarCreditos(maquina.getPrecioPorPartida()); // Descontamos los créditos que cuesta jugar a la máquina al jugador

                int puntuacion = maquina.nuevaPartida();
                jugador.incrementarNumeroPartidas();
                System.out.println("La puntuación de la partida ha sido: " + puntuacion);
            } else {
                throw new Exception ("El jugador no tiene suficientes créditos para jugar una partida");
            }
        } else {
            throw new Exception ("La máquina esta desactivada, no se puede jugar");
        }
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
    public String listarMaquinasActivas (){
        String texto = "Máquinas activas: ";
        boolean estaActiva = false;
        for(int i = 0; i < capacidadMaquinas; i++){
            if(maquinas[i].EstadoMaquina()){
                texto += maquinas[i] + "\n";
                estaActiva = true;
            }
        }
        return texto;
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

    public void recargarCreditos(String idJugador, int cantidad) throws Exception {
        Jugador jugador = buscarIDJugador(idJugador);

        if (jugador != null) {
            jugador.recargarCreditos(cantidad);
        } else {
            throw new Exception("No existe un jugador con ese ID");
        }
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

    public String listarJugadores (){
        String texto = "Jugadores en la sala: ";
        if(capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += jugadores[i] + "\n";
            }
        }
        return texto;
    }

    public String listarMaquinas (){
        String texto = "Maquinas en la sala : \n";
        if(capacidadMaquinas == 0){
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < capacidadMaquinas; i++){ // Recorre el array hasta la capacidad actual
                texto += maquinas[i] + "\n";
            }
        }
        return texto;
    }

    public String toString() {
        String texto = "Jugadores en la sala: ";
        if(capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += jugadores[i] + "\n";
            }
        }

        texto += "Maquinas en la sala : \n";
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