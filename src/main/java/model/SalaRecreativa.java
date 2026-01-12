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
        for(int i = 0; i < this.capacidadMaquinas; i++){ // Cogemos los nombres de las máquinas que ya están en la sala
            if(this.maquinas[i].getNombreMaquina().equalsIgnoreCase(nombre)){ // Comprobamos que el nombre de la máquina es igual que el introducido por consola
                return true; // Devolvemos true si el nombre ya existe en la sala
            }
        }
        return false; // False si el nombre no está en la sala
    }

    /**
     * Método con el que comprobamos que el nombre que introducimos por consola no esté repetido en la sala
     * @param nombre Nombre del jugador que comprobamos si está dentro de la sala
     * @return True si el nombre ya está dentro de la sala y False si no lo está
     */
    public boolean existeNombreJugador (String nombre){
        for(int i = 0; i < this.capacidadJugadores; i++){ // Cogemos los nombres de los jugadores que están dentro de la sala
           if(this.jugadores[i].getNombre().equalsIgnoreCase(nombre)){ // Comprobamos que el nombre del jugador es igual que el introducido por el usuario
               return true; // Devolvemos true si el nombre ya existe en la sala
           }
        }
        return false; // False si el nombre no está en la sala
    }

    /**
     * Método con el que podemos editar el nombre de un jugador ya existente
     * @param idUnico El ID para buscar el jugador que queremos editar
     * @param nombreNuevo El nuevo nombre del jugador introducido por el usuario
     * @return Devuelve True si se ha cambiado el nombre del jugador y False si no existe el jugador
     */
    public boolean editarJugador (int idUnico, String nombreNuevo){
        Jugador jugador = buscarIDJugador(idUnico);
        if(jugador == null){ // Comprobamos que el jugador introducido no sea null
            return false;
        }
        jugador.setNombre(nombreNuevo); // El usuario cambia el nombre
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
        if(maquina == null){ // Comprobamos que la máquina introducida no sea null
            return false;
        }

        int precio = maquina.getPrecioPorPartida();
        if (precio <= 0 || (precio % 10 != 0 && precio % 10 != 5)) { // Comprobamos que el precio introducido por el usuario siempre acabe en 0 o en 5
            return false;
        }

        maquina.setNombreMaquina(nombreNuevo); // El usuario cambia el nombre
        maquina.setGeneroMaquina(generoNuevo); // El usuario cambia el género
        maquina.setPrecioPorPartida(precioPorParidaNuevo); // El usuario cambia el precio por partida
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
        return puntuacion; // Devolvemos la puntuación obtenida en la partida
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
        return maquinaMasJugada; // Devolvemos la máquina con más partidas jugadas dentro de la sala
    }

    /**
     * Método con el que encontramos cual es el jugador más activo de la sala
     * @return Devuelve el jugador más activo de la sala
     */
    public Jugador jugadorMasActivo() {
        Jugador masActivo = this.jugadores[0];   // Empiezo poniendo al primer jugador como que es el más activo
        for (int i = 1; i < this.capacidadJugadores; i++) { // Empezamos el bucle en 1 y si 1 es menor que la capacidad de jugadores entonces pasa al siguiente bucle y asi hasta que un número sea mayor que la capacidad de jugadores
            if (this.jugadores[i].getNumeroPartidasJugadas() > masActivo.getNumeroPartidasJugadas()) { // Ahora comparo de uno en uno para saber si tienen más partidas jugadas que el anterior
                masActivo = this.jugadores[i];// Si hay otro jugador con más partidas se cambia
            }
        }
        return masActivo; // Devolvemos el jugador más activo dentro de la sala
    }

    /**
     * Método con el que solo imprimimos por pantalla las máquinas que están activas dentro de la sala
     */
    public String listarMaquinasActivas (){
        String texto = "Máquinas activas: \n";
        for(int i = 0; i < this.capacidadMaquinas; i++){ // Recorremos las máquinas que hay dentro de la sala
            if(this.maquinas[i].EstadoMaquina()){ // Si las máquinas están activadas pasan al siguiente paso, si no lo están no entran en el siguiente paso
                texto += this.maquinas[i] + "\n"; // Imprimimos por pantalla lass máquinas que están activadas
            }
        }
        return texto; // Devolvemos el texto más las máquinas
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
        return null; // Devolvemos null si no hay ninguna máquina con el nombre introducido por el usuario
    }

    /**
     * Método con el que buscamos a un jugador buscando por su ID
     * @param idUnico El ID único de un jugador
     * @return Devuelve el jugador que estamos buscando
     */
    public Jugador buscarIDJugador(int idUnico){
        for (Jugador jugador : this.jugadores){ // Recorre cada jugador del array y llamamos jugador a cada elemento del array
            if(jugador != null && jugador.getIdUnico() == idUnico){ // Ahora comparámos el jugador del array con el nombre del jugador que ha escrito el usuario
                return jugador; // Devuelve el jugador si coincide con el nombre
            }
        }
        return null; // Devolvemos null si el ID del jugador no coincide con el introducido por el usuario
    }

    /**
     * Método con el que añadimos un jugador en la sala
     * @param jugadorAdd Es el jugador que queremos añadir a la sala
     */
    public boolean addJugador (Jugador jugadorAdd) {
        if (jugadorAdd == null) { // Comprobamos que el jugador que queremos registrar no sea null
            return false;
        }
        if (this.capacidadJugadores >= this.jugadores.length) { // Comprobamos que haya capacidad en la sala
            return false;
        }
        this.jugadores[this.capacidadJugadores] = jugadorAdd; // Añadimos el jugador a la sala
        this.capacidadJugadores++; // Aumentamos el número de jugadores que están en la sala
        return true;
    }

    /**
     * Método con el que añadimos una máquina a una sala
     * @param maquinaAdd Es la máquina que queremos añadir a la sala
     */
    public boolean addMaquina (MaquinaArcade maquinaAdd){
        if(maquinaAdd == null){ // Comprobamos que la máquina registrada no sea null
            return false;
        }

        int precio = maquinaAdd.getPrecioPorPartida(); // Obtenemos el precio por partida de una máquina y lo guardamos en un entero
        if (precio <= 0 || (precio % 10 != 0 && precio % 10 != 5)) { // Comprobamos que el precio introducido acabe en 0 o en 5
            return false;
        }

        if(this.capacidadMaquinas >= this.maquinas.length){ // Comprobamos que halla espacio en la sala
           return false;
        }
        this.maquinas[this.capacidadMaquinas] = maquinaAdd; // Añadimos la máquina
        this.capacidadMaquinas++; // Incrementamos el número de máquinas en la sala
        return true;
    }

    /**
     * Método con el que listamos por pantalla los jugadores de la sala
     * @return Devuelve un String con la información de los jugadores
     */
    public String listarJugadores (){
        String texto = "--- JUGADORES --- ";
        if(this.capacidadJugadores == 0){ // Si la capacidad de jugadores es igual a 0, imprimimos por pantalla el siguiente texto
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < this.capacidadJugadores; i++) { // Recorre el array hasta la capacidad actual
                texto += this.jugadores[i] + "\n"; // Imprimimos por pantalla los jugadores que estén dentro de la sala
            }
        }
        return texto; // Devolvemos el texto que queremos que aparezca por pantalla
    }

    /**
     * Método con el que listamos las máquinas de la sala
     * @return Devuelve un String con la información de las máquinas
     */
    public String listarMaquinas (){
        String texto = "--- MÁQUINAS --- \n";
        if(this.capacidadMaquinas == 0){ // Si la capacidad de máquinas es igual a 0, imprimimos esto por pantalla el siguiente texto
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < this.capacidadMaquinas; i++){ // Recorre el array hasta la capacidad actual
                texto += this.maquinas[i] + "\n"; // Imprimimos por pantalla las máquinas de la sala
            }
        }
        return texto; // Devolvemos el texto que queremos que aparezca por pantalla
    }

    /**
     * ToString que utilizo para cuando quiero listar las máquinas y jugadores de la sala
     * @return Devolvemos los datos de listarJugadores y listarMaquina, se imprime unos de los dos, dependiendo de lo que quieras imprimir
     */
    public String toString() {
        return listarJugadores() +
                "\n" + listarMaquinas();
    }

    /**
     * Constructor con los valores ya establecidos
     */
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