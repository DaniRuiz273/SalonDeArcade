package utils;
import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;
public class Vista {
    public static void llamarMenu (){
        int opciones;
        SalaRecreativa ElTemploDelArcade = new SalaRecreativa();

        Jugador j1 = new Jugador("Dani",  15);
        Jugador j2 = new Jugador("Juan", 45);
        Jugador j3 = new Jugador("Lucía", 30);
        ElTemploDelArcade.addJugador(j1);
        ElTemploDelArcade.addJugador(j2);
        ElTemploDelArcade.addJugador(j3);

        MaquinaArcade pinball = new MaquinaArcade("Pinball", "Juego de salón", 10);
        MaquinaArcade donkeykong = new MaquinaArcade("DonkeyKong", "Plataformas", 10);
        ElTemploDelArcade.addMaquina(pinball);
        ElTemploDelArcade.addMaquina(donkeykong);

        pinball.cambiarEstado(1);
        donkeykong.cambiarEstado(1);

        do {
            System.out.println("----- EL TEMPLO DEL ARCADE -----");
            System.out.println("0. SALIR");
            System.out.println("1. Registrar un nuevo jugador");
            System.out.println("2. Registrar una nueva máquina arcade");
            System.out.println("3. Recargar créditos de un jugador");
            System.out.println("4. Listar jugadores");
            System.out.println("5. Listar máquinas");
            System.out.println("6. Listar máquinas activas");
            System.out.println("7. Realizar mantenimiento a una máquina (reactivarla)");
            System.out.println("8. Jugar una partida (Introduciendo ID y máquina)");
            System.out.println("9. Mostrar el jugador más activo");
            System.out.println("10. Mostrar la máquina más usada");
            System.out.println("11. Mostra el ranking de una máquina concreta");
            System.out.println("12. Dar de baja una máquina");
            System.out.println("13. Dar de baja a un jugador");
            System.out.println("14. Editar una máquina");
            System.out.println("15. Editar el nombre de un jugador");
            opciones = Utils.pideEnteroEntreValores("Introduce una opción entre 0 y 11: ", "Error, debes introducir un entero entre 0 y 11", 0, 15);
            switch (opciones){
                case 0:
                    System.out.println("SALIENDO...");
                    break;
                case 1:
                    registrarJugador(ElTemploDelArcade);
                    break;

                case 2:
                    registraMaquina(ElTemploDelArcade);
                    break;

                case 3:
                    Vista.recargarCreditos(ElTemploDelArcade);
                    break;

                case 4:
                    System.out.println(ElTemploDelArcade.listarJugadores());
                    break;

                case 5:
                    System.out.println(ElTemploDelArcade.listarMaquinas());
                    break;

                case 6:
                    System.out.println(ElTemploDelArcade.listarMaquinasActivas());
                    break;

                case 7:
                    Vista.reactivarMaquina(ElTemploDelArcade);
                    break;

                case 8:
                    Vista.jugarUnaPartida(ElTemploDelArcade);
                    break;

                case 9:
                    jugadorConMasPartidas(ElTemploDelArcade);
                    break;

                case 10:
                    System.out.println(ElTemploDelArcade.maquinaConMasPartidasJugadas());
                    break;

                case 11:
                    mostrarRanking(ElTemploDelArcade);
                    break;

                case 12:
                    darDeBajaMaquina(ElTemploDelArcade);
                    break;

                case 13:
                    darDeBajaJugador(ElTemploDelArcade);
                    break;

                case 14:
                    editarMaquina(ElTemploDelArcade);
                    break;

                case 15:
                    editarJugador(ElTemploDelArcade);
                    break;
            }
        }while (opciones != 0);
    }
    // TODO: Hacer que al registrar un nuevo jugador o maquina no pueda poner el mismo nombre si ya existe en la sala
    /**
     * Método con el que imprimimos por pantalla el jugador más activo de la sala
     * @param sala Lugar donde se encuentran los jugadores
     */
    public static void jugadorConMasPartidas (SalaRecreativa sala){
        System.out.println("El jugador más activo es: ");
        System.out.println(sala.jugadorMasActivo());
    }
    /**
     * Método con el que imprimimos por pantalla la manera de editar el nombre de un jugador que ya estaba en la sala
     * @param sala Donde se encuentran los jugadores que podemos editar
     */
    public static void editarJugador (SalaRecreativa sala){
        System.out.println("Jugadores en la sala: ");
        mostrarJugadores(sala);

        int idUnico = Utils.pideEntero("Introduce el ID del jugador que vas a editar: ", "Error, debes de introducir el ID de uno de los jugadores anteriores");
        Jugador jugador = sala.buscarIDJugador(idUnico);
        if(jugador == null){
            System.out.println("El jugador que has introducido no existe");
        }

        String nombreNuevo = Utils.pideCadena("Introduce el nuevo nombre del jugador: ", "Error");
        if(!Utils.cadenaValida(nombreNuevo)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }

        if(sala.editarJugador(idUnico, nombreNuevo)){
            System.out.println("El jugador ha sido editado correctamente");
        } else {
            System.out.println("No se ha podido editar el jugador, inténtelo de nuevo");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como editar a una máquina
     * @param sala Donde se encuentran las máquinas que podemos editar
     */
    public static void editarMaquina (SalaRecreativa sala){
        System.out.println("Máquinas en la sala: ");
        mostrarMaquinas(sala);

        String nombre = Utils.pideCadena("Introduce el nombre de la máquina que quieres editar: ", "Error, debes de introducir el nombre de una de las máquinas anteriores");
        if(!Utils.cadenaValida(nombre)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }

        MaquinaArcade maquina = sala.buscarNombreMaquina(nombre);
        if(maquina == null){
            System.out.println("La máquina no existe, prueba con otro nombre");
            return;
        }

        String nombreNuevo = Utils.pideCadena("¿Cuál es el nuevo nombre de la máquina?: ", "Error");
        if(!Utils.cadenaValida(nombreNuevo)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }

        String generoNuevo = Utils.pideCadena("¿Cuál es el nuevo género de la máquina?: ", "Error");
        if(!Utils.cadenaValida(generoNuevo)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }

        int precioPorPartidaNuevo = Utils.pideEntero("¿Cuál va a ser el nuevo precio de la partida de la máquina?: ", "Error");
        if(precioPorPartidaNuevo <= 0 || (precioPorPartidaNuevo % 10 != 0 && precioPorPartidaNuevo % 10 != 5)){
            System.out.println("El precio de la máquina debe de acabar en 0 o en 5");
            return;
        }

        if(sala.editarMaquina(nombre, nombreNuevo, generoNuevo, precioPorPartidaNuevo)){
            System.out.println("La máquina " + nombre + " ha sido editada correctamente");
        } else {
            System.out.println("No ha sido posible editar la máquina");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como dar de baja a un jugador
     * @param sala Donde se encuentran los jugadores que queremos dar de baja
     */
    public static void darDeBajaJugador (SalaRecreativa sala){
        System.out.println("Jugadores en la sala: ");
        mostrarJugadores(sala);
        String nombre = Utils.pideCadena("Introduce el nombre del jugador que quieres dar de baja: ", "Error, ese jugador no existe");
        if(!Utils.cadenaValida(nombre)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }

        if(sala.darDeBajaJugador(nombre)){
            System.out.println("El jugador " + nombre + " ha sido dado de baja correctamente");
        } else {
            System.out.println("El jugador no ha podido darse de baja");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como dar de baja una máquina
     * @param sala Sala donde se encuentran las máquinas que queremos dar de baja
     */
    public static void darDeBajaMaquina (SalaRecreativa sala){
        System.out.println("Máquinas en la sala");
        mostrarMaquinas(sala);
        String nombre = Utils.pideCadena("¿Qué máquina quieres dar de baja?: ", "Error, esa máquina no existe");
        if(!Utils.cadenaValida(nombre)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }
        if (sala.darDeBajaMaquina(nombre)){
            System.out.println("La máquina " + nombre + " ha sido dada de baja correctamente");
        } else {
            System.out.println("La máquina no ha podido darse de baja");
        }
    }

    /**
     * Método con el que solo mostramos solo el nombre de una máquina que está dentro de la sala
     * @param sala Donde están guardadas las máquinas
     */
    public static void mostrarMaquinas (SalaRecreativa sala){
        MaquinaArcade [] maquinas = sala.getMaquinasArcade(); // Pido a la sala que me dé todas sus máquinas para poder utilizarlas
        for(MaquinaArcade m : maquinas){ // Recorre cada máquina del array y llama m a cada máquina
            if(m != null){ // Si m es distinto de null es que está dentro de la sala
                System.out.println("Nombre: " + m.getNombreMaquina());
            }
        }
    }

    /**
     * Método con el que solo mostramos el nombre y la ID de los jugadores que están dentro de la sala
     * @param sala Donde están guardadas los jugadores
     */
    public static void mostrarJugadores(SalaRecreativa sala) {
        Jugador[] jugadores = sala.getJugadores(); // Pido a la sala que me dé todas sus jugadores para poder utilizarlas
        for (Jugador j : jugadores) { //
            if (j != null) {
                System.out.println("ID: " + j.getIdUnico() +
                        "\nNombre: " + j.getNombre());
            }
        }
    }

    /**
     * Método con el que el usuario elige una máquina para ver su ranking
     * @param sala Donde están guardadas las máquinas con sus rankings
     */
    public static void mostrarRanking (SalaRecreativa sala){
        System.out.println("¿De qué máquina quieres ver su ranking?");
        mostrarMaquinas(sala);
        String nombre  = Utils.pideCadena("Introduce el nombre de la máquina para ver su ranking: ", "Error, debes de introducir el nombre de una máquina que esté en la sala");
        if(!Utils.cadenaValida(nombre)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }
        MaquinaArcade maquina = sala.buscarNombreMaquina(nombre);

        System.out.println("=== RANKING " + maquina.getNombreMaquina() + " ===");
        boolean hayDatos = false;
        for (int i = 0; i < maquina.getMejoresPuntuaciones().length; i++) {
            if (maquina.getMejoresJugadores()[i] != null) {
                System.out.println((i + 1) + ". " + maquina.getMejoresJugadores()[i].getNombre() +  " - " + maquina.getMejoresPuntuaciones()[i] + " puntos");
                hayDatos = true;
            }
        }
        if (!hayDatos) {
            System.out.println("Aun no se ha jugador ninguna partida en esta máquina");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como poder jugar una partida
     * @param sala Donde están los jugadores y máquinas
     */
    public static void jugarUnaPartida (SalaRecreativa sala) {
        System.out.println("Jugadores en la sala: ");
        mostrarJugadores(sala);
        Jugador jugador  = sala.buscarIDJugador(Utils.pideEntero("Introduce la ID del jugador que quiere jugar: ", "Error, esa ID no existe"));
        if(jugador == null){
            System.out.println("No existe ningún jugador con esa ID");
            return;
        }

        System.out.println("Maquinas en la sala: ");
        mostrarMaquinas(sala);
        MaquinaArcade maquina = sala.buscarNombreMaquina(Utils.pideCadena("Introduce el nombre de la máquina a la que quieres jugar:", "Error, esa máquina no existe en la sala"));

        int puntuacion = sala.gestionarPartida(jugador.getIdUnico(), maquina.getNombreMaquina());
        if(puntuacion == -1){
            System.out.println("La partida no ha podido jugarse");
            return;
        }
        if(puntuacion < 1000){
            System.out.println(puntuacion + " --> Puntuación muy baja, haz el favor de jugar mejor la próxima partida");
        } else if (puntuacion > 1000 && puntuacion <= 5000){
            System.out.println(puntuacion + " --> Buena puntuación pero es mejorable");
        } else if (puntuacion > 5000 && puntuacion <= 8000){
            System.out.println(puntuacion + " --> Uffff que buena puntuación");
        } else if (puntuacion > 8000 && puntuacion < 10000){
            System.out.println(puntuacion + " --> Muy buena puntuación, estas hecho un máquina");
        }
    }

    /**
     * Método con el que reactivamos una máquina elegida por el usuario
     * @param sala Sala donde se encuentran las máquinas
     */
    public static void reactivarMaquina(SalaRecreativa sala) {
        System.out.println("Máquinas en la sala: ");
        mostrarMaquinas(sala);
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina que quieres reactivar:", "Error, debes de introducir el nombre de una máquina de las anteriores");
        if(!Utils.cadenaValida(nombre)){
            System.out.println("El nombre no es válido, solo puedes poner letras");
            return;
        }
        MaquinaArcade maquina = sala.buscarNombreMaquina(nombre);
        if(maquina.EstadoMaquina()){
            System.out.println("La máquina ya estaba activada de antes por lo que no puedes reactivarla");
        } else {
            maquina.cambiarEstado(1);
            System.out.println("La máquina ha sido reactivada correctamente");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como poder registrar a un nuevo jugador
     */
    public static void registrarJugador (SalaRecreativa sala) {
        String nombre = Utils.pideCadena("Introduce el nombre del jugador: ", "Error, debes introducir un nombre de una persona").trim();
        if(!Utils.cadenaValida(nombre)){
            System.out.println("Este nombre no es válido, solo puedes poner letras");
            return;
        }
        int creditosDisponibles = Utils.pideEntero("Introduce los créditos que va a tener el jugador: ", "Error, debes de introducir un numero.");
        if (!Utils.precioValido(creditosDisponibles)) {
            System.out.println("No puedes poner esa cantidad de créditos, los créditos deben de acabar en 0 o en 5");
            return;
        }
        Jugador nuevoJugador = new Jugador(nombre, creditosDisponibles);
        boolean add = sala.addJugador(nuevoJugador);
        if (add) {
            System.out.println("Jugador añadido correctamente.");
        } else {
            System.out.println("No se ha podido añadir el jugador porque la sala está llena, inténtalo más tarde.");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como registrar una nueva máquina
     * @param sala Donde se guarda las máquinas nuevas que registremos
     */
    public static void registraMaquina (SalaRecreativa sala){
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina: ", "Error, debes introducir un nombre").trim();
        if (!Utils.cadenaValida(nombre)) {
            System.out.println("Este nombre no es válido, solo puedes poner letras");
            return;
        }

        String genero = Utils.pideCadena("Introduce el genero de la máquina: ", "Error, debes introducir un genero").trim();
        if(!Utils.cadenaValida(genero)){
            System.out.println("Este género no es válido, solo puedes poner letras");
            return;
        }

        int precioPorPartida = Utils.pideEntero("Introduce los créditos que va a costar jugar una partida: ", "Error, debes de introducir un entero.");
        if(precioPorPartida <= 0 || (precioPorPartida % 10 != 0 && precioPorPartida % 10 != 5)){
            System.out.println("Debes de introducir un precio acabado en 0 o en 5");
            return;
        }
        MaquinaArcade nuevaMaquina = new MaquinaArcade(nombre, genero, precioPorPartida);
        boolean registrado = sala.addMaquina(nuevaMaquina);
        if (registrado) {
            System.out.println("La máquina ha sido añadida correctamente.");
        } else {
            System.out.println("No se ha añadido la máquina");
        }
    }

    /**
     * Método con el que imprimimos por pantalla como poder recargar los créditos de un jugador
     * @param sala Sala donde está el jugador al que queremos recargarle los créditos
     */
    public static void recargarCreditos(SalaRecreativa sala){
        System.out.println("Jugadores en la sala: ");
        mostrarJugadores(sala);
        int id = Utils.pideEntero("Introduce el ID del jugador al que vas a recargar créditos: ", "Error, eso no es un ID");
        Jugador jugador = sala.buscarIDJugador(id);
        if (jugador == null) {
            System.out.println("No existe un jugador con ese ID.");
            return;
        }
        int cantidad = Utils.pideEntero("¿Cuántos créditos quieres recargar?: ", "Error, debes de introducir un numero que acabe en 0 o en 5");
        if(jugador.recargarCreditos(cantidad)){
            System.out.println("Créditos recargados correctamente, ahora tiene: " + jugador.getCreditosDisponibles());
        } else {
            System.out.println("Cantidad inválida, debes de introducir un número acabado en 0 o 5");
        }
    }
}