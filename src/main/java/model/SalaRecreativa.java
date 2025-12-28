package model;
public class SalaRecreativa {
    private final int size = 5;
    private final Jugador [] jugadores; // Array donde se guardan los jugadores de la sala
    private final MaquinaArcade [] maquinas; // Array donde se guardan las máquinas de la sala
    private int capacidadJugadores; // Son los jugadores que hay actualmente dentro de la sala
    private int capacidadMaquinas; // Son las máquinas que hay actualmente dentro de la sala

    /**
     * Método con el que damos de baja a un jugador
     * @param nombreJugador Jugador que vamos a dar de baja
     * @return True si se ha dado de baja y false si no
     */
    public boolean darDeBajaJugador (String nombreJugador){
        for (int i = 0; i < capacidadJugadores; i++){
            if (jugadores[i].getNombre().equalsIgnoreCase(nombreJugador)){
                for (int j = i; j < capacidadJugadores - 1; j++){
                    jugadores[i] = jugadores[i + 1];
                }
                jugadores[capacidadJugadores -1] = null;
                capacidadJugadores--;
                return true;
            }
        }
        return false;
    }

    /**
     * Método con el que damos de baja a una máquina
     * @param nombreMaquina Nombre de la máquina que vamos a dar de baja
     * @return Devuelve true si se ha dado de baja la máquina y false si no
     */
    public boolean darDeBajaMaquina(String nombreMaquina) {
        for (int i = 0; i < capacidadMaquinas; i++) {
            if (maquinas[i].getNombreMaquina().equalsIgnoreCase(nombreMaquina)) {
                for (int j = i; j < capacidadMaquinas - 1; j++) { // Desplazamos las máquinas
                    maquinas[j] = maquinas[j + 1];
                }
                maquinas[capacidadMaquinas - 1] = null;
                capacidadMaquinas--;
                return true;
            }
        }
        return false;
    }


    /**
     * Método con el que gestionamos un partida
     *
     * @param idJugador     Id único del jugador que va a jugar la partida
     * @param nombreMaquina Es la máquina donde se va a jugar la partida
     */
    public int gestionarPartida(int idJugador, String nombreMaquina) {
        Jugador jugador = buscarIDJugador(idJugador);
        if (jugador == null) {
            return -1;
        }

        MaquinaArcade maquina = buscarNombreMaquina(nombreMaquina);
        if (maquina == null) {
            return -1;
        }

        if (!maquina.EstadoMaquina()) {
            return -1;
        }

        if (jugador.getCreditosDisponibles() < maquina.getPrecioPorPartida()) {
            return -1;
        } else {
            jugador.gastarCreditos(maquina.getPrecioPorPartida());
        }

        int puntuacion = maquina.nuevaPartida(jugador);
        jugador.incrementarNumeroPartidas();
        return puntuacion;
    }

    /**
     * Método con el que comprobamos cual es la máquina más utilizada en la sala
     * @return Devuelve la máquina más utilizada de la sala
     */
    public MaquinaArcade maquinaConMasPartidasJugadas (){
        MaquinaArcade maquinaMasJugada = this.maquinas[0]; // Pongo como la máquina más utilizada a la primera del array
        for(int i = 1; i < this.capacidadMaquinas; i++) {
            if (this.maquinas[i].getContadorPartidasJugadas() > maquinaMasJugada.getContadorPartidasJugadas()) { // Ahora comparo la primera máquina con la siguiente, si la segunda máquina no tiene más partidas sigue en el bucle hasta encontrar una máquina con más partidas o hasta que no haya más máquinas
                maquinaMasJugada = this.maquinas[i]; // Si hay una máquina con más partidas se cambia
            }
        }
        return maquinaMasJugada;
    }

    /**
     * Método con el que encontramos cual es el jugador más activo de la sala
     * @return Devuelve el jugador más activo de la sala
     */
    public Jugador jugadorMasActivo() {
        Jugador masActivo = this.jugadores[0];   // Empiezo poniendo al primer jugador como que es el más activo
        for (int i = 1; i < this.capacidadJugadores; i++) {
            if (this.jugadores[i].getNumeroPartidasJugadas() > masActivo.getNumeroPartidasJugadas()) { // Ahora comparo de uno en uno para saber si tienen más partidas jugadas que el anterior
                masActivo = this.jugadores[i];   // Si hay otro jugador con más partidas se cambia
            }
        }
        return masActivo;
    }

    /**
     * Método con el que solo imprimimos por pantalla las máquinas que están activas dentro de la sala
     */
    public String listarMaquinasActivas (){
        String texto = "Máquinas activas: ";
        for(int i = 0; i < this.capacidadMaquinas; i++){
            if(this.maquinas[i].EstadoMaquina()){
                texto += this.maquinas[i] + "\n";
            }
        }
        return texto;
    }

    /**
     * Método con el que buscamos una máquina de sala por su nombre
     * @param nombre Es el nombre de la máquina que queremos buscar
     * @return Devuelve los atributos de la máquina si coincide con su nombre
     */
    public MaquinaArcade buscarNombreMaquina (String nombre) {
        for (MaquinaArcade maquina : this.maquinas){ // Recorre cada máquina del array y llamamos máquina a cada elemento del array
            if (maquina != null && maquina.getNombreMaquina().trim().equalsIgnoreCase(nombre.trim())){ // Ahora comparamos la máquina del array con el nombre de la máquina que ha escrito el usuario por pantalla, si no es igual pasa a la siguiente máquina
                return maquina; // Devuelve la máquina si coincide con su nombre
            }
        }
        return null;
    }

    /**
     * Método con el que buscamos a un jugador buscando por su ID
     * @param idUnico El ID único de un jugador
     * @return Devuelve el jugador que estamos buscando
     */
    public Jugador buscarIDJugador(int idUnico){
        for (Jugador jugador : this.jugadores){
            if(jugador.getIdUnico() == idUnico){
                return jugador;
            }
        }
        return null;
    }


    /**
     * Método con el que añadimos un jugador en la sala
     * @param jugadorAdd Es el jugador que queremos añadir a la sala
     */
    public boolean addJugador (Jugador jugadorAdd) {
        if (jugadorAdd == null) {
            return false;
        }
        if (this.capacidadJugadores >= this.jugadores.length) {
            return false;
        }
        this.jugadores[this.capacidadJugadores] = jugadorAdd;
        this.capacidadJugadores++;
        return true;
    }

    /**
     * Método con el que añadimos una máquina a una sala
     * @param maquinaAdd Es la máquina que queremos añadir a la sala
     */
    public boolean addMaquina (MaquinaArcade maquinaAdd){
        if(maquinaAdd == null){
            return false;
        }
        if(this.capacidadMaquinas >= this.maquinas.length){
           return false;
        }
        this.maquinas[this.capacidadMaquinas] = maquinaAdd;
        this.capacidadMaquinas++;
        return true;
    }

    public String listarJugadores (){
        String texto = "Jugadores en la sala: ";
        if(this.capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < this.capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += this.jugadores[i] + "\n";
            }
        }
        return texto;
    }

    public String listarMaquinas (){
        String texto = "Maquinas en la sala : \n";
        if(this.capacidadMaquinas == 0){
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < this.capacidadMaquinas; i++){ // Recorre el array hasta la capacidad actual
                texto += this.maquinas[i] + "\n";
            }
        }
        return texto;
    }

    public String toString() {
        String texto = "--- JUGADORES ---";
        if(this.capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < this.capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += this.jugadores[i] + "\n";
            }
        }

        texto += "--- MÁQUINAS ---\n";
        if(this.capacidadMaquinas == 0){
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < this.capacidadMaquinas; i++){ // Recorre el array hasta la capacidad actual
                texto += this.maquinas[i] + "\n";
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