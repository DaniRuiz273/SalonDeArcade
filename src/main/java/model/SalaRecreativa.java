package model;
public class SalaRecreativa {
    private final int size = 5;
    private final Jugador [] jugadores; // Array donde se guardan los jugadores de la sala
    private final MaquinaArcade [] maquinas; // Array donde se guardan las máquinas de la sala
    private int capacidadJugadores; // Son los jugadores que hay actualmente dentro de la sala
    private int capacidadMaquinas; // Son las máquinas que hay actualmente dentro de la sala

    /**
     * Método con el que comprobamos que el nombre que introducimos por consola no esté repetido en la sala
     * @param nombre Nombre de la máquina que vamos a comprobar si está o no repetido
     * @return True si el nombre está repetido y False si no lo está
     */
    public boolean existeNombreMaquina (String nombre){
        for(int i = 0; i < this.capacidadMaquinas; i++){
            if(this.maquinas[i].getNombreMaquina().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método con el que comprobamos que el nombre que introducimos por consola no esté repetido en la sala
     * @param nombre Nombre del jugador que comprobamos si está dentro de la sala
     * @return True si el nombre ya está dentro de la sala y False si no lo está
     */
    public boolean existeNombreJugador (String nombre){
        for(int i = 0; i < this.capacidadJugadores; i++){
           if(this.jugadores[i].getNombre().equalsIgnoreCase(nombre)){
               return true;
           }
        }
        return false;
    }

    /**
     * Método con el que podemos editar el nombre de un jugador ya existente
     * @param idUnico El ID para buscar el jugador que queremos editar
     * @param nombreNuevo El nuevo nombre del jugador introducido por el usuario
     * @return Devuelve True si se ha cambiado el nombre del jugador y False si no existe el jugador
     */
    public boolean editarJugador (int idUnico, String nombreNuevo){
        Jugador jugador = buscarIDJugador(idUnico);
        if(jugador == null){
            return false;
        }
        jugador.setNombre(nombreNuevo);
        return true;
    }
    /**
     * Método con el que podemos editar el nombre, genero y precio por partida de una máquina
     * @param nombreActual El nombre que tiene la máquina actualmente
     * @param nombreNuevo El nuevo nombre de la máquina introducido por el usuario
     * @param generoNuevo El nuevo género de la máquina introducido por el usuario
     * @param precioPorParidaNuevo El nuevo precio por partida de la máquina introducido por el usuario
     * @return Devuelve True si se ha cambiado los valores y False si no existe máquina
     */
    public boolean editarMaquina (String nombreActual, String nombreNuevo, String generoNuevo, int precioPorParidaNuevo){
        MaquinaArcade maquina = buscarNombreMaquina(nombreActual);
        if(maquina == null){
            return false;
        }

        int precio = maquina.getPrecioPorPartida();
        if (precio <= 0 || (precio % 10 != 0 && precio % 10 != 5)) { // Comprobamos que el precio introducido por el usuario siempre acabe en 0 o en 5
            return false;
        }

        maquina.setNombreMaquina(nombreNuevo);
        maquina.setGeneroMaquina(generoNuevo);
        maquina.setPrecioPorPartida(precioPorParidaNuevo);
        return true;
    }

    /**
     * Método con el que damos de baja a un jugador
     * @param nombreJugador Jugador que vamos a dar de baja
     * @return True si se ha dado de baja y false si no
     */
    public boolean darDeBajaJugador (String nombreJugador){
        for (int i = 0; i < this.capacidadJugadores; i++){ // Recorremos solo las posiciones que están ocupadas
            if (this.jugadores[i].getNombre().equalsIgnoreCase(nombreJugador)){ // Comparamos los nombres de los jugadores que están dentro del array con el nombre que introduce el usuario
                for (int j = i; j < this.capacidadJugadores - 1; j++){ // Movemos los jugadores
                    this.jugadores[j] = this.jugadores[j + 1]; // Desplazamos los jugadores una posición a la izquierda
                }
                this.jugadores[this.capacidadJugadores -1] = null; // Ahora la última posición queda duplicada por lo que hacemos que se quede en null directamente
                this.capacidadJugadores--; // Ponemos que ahora hay un jugador menos en el array, pero se queda con el mismo tamaño
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
        for (int i = 0; i < this.capacidadMaquinas; i++) { // Recorremos solo las posiciones que están ocupadas
            if (this.maquinas[i].getNombreMaquina().equalsIgnoreCase(nombreMaquina)) { // Comparamos los nombres de las máquinas que están dentro del array con el nombre introducido por el usuario
                for (int j = i; j < this.capacidadMaquinas - 1; j++) { // Movemos las máquinas
                    this.maquinas[j] = this.maquinas[j + 1]; // Movemos a las máquinas una posición a la izquierda
                }
                this.maquinas[this.capacidadMaquinas - 1] = null; // Ahora como la última posición queda duplicada hacemos que esté en null directamente
                this.capacidadMaquinas--; // Ahora hay una máquina menos en al array, pero su tamaño sigue siendo el mismo
                return true;
            }
        }
        return false;
    }


    /**
     * Método con el que gestionamos un partida
     * @param idJugador Id único del jugador que va a jugar la partida
     * @param nombreMaquina Es la máquina donde se va a jugar la partida
     */
    public int gestionarPartida(int idJugador, String nombreMaquina) {
        Jugador jugador = buscarIDJugador(idJugador); // Buscamos el ID del jugador que va a jugar la partida
        if (jugador == null) {
            return -1;
        }

        MaquinaArcade maquina = buscarNombreMaquina(nombreMaquina); // Comprobamos que el nombre de la máquina existe
        if (maquina == null) {
            return -1;
        }

        if (!maquina.EstadoMaquina()) { // Comprobamos que la máquia esté activada
            return -1;
        }

        if (jugador.getCreditosDisponibles() < maquina.getPrecioPorPartida()) { // Comprobamos que el jugador tenga créditos suficientes para jugar una partida
            return -1;
        } else {
            jugador.gastarCreditos(maquina.getPrecioPorPartida()); // Restamos los créditos de la partida al jugador
        }

        int puntuacion = maquina.nuevaPartida(jugador); // Obtenemos la puntuación de la partida
        jugador.incrementarNumeroPartidas();// Incrementamos el número de partidas del jugador
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

        int precio = maquinaAdd.getPrecioPorPartida();
        if (precio <= 0 || (precio % 10 != 0 && precio % 10 != 5)) {
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

    public int getCapacidadJugadores() {
        return capacidadJugadores;
    }
}