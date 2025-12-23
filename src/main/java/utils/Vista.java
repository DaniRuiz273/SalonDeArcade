package utils;
import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;

import java.util.Scanner;

public class Vista {
    public static void llamarMenu (){
        Scanner sc = new Scanner(System.in);
        int opciones = 0;
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
            opciones = Utils.pideEnteroEntreValores("Introduce una opción entre 0 y 11: ", "Error, debes introducir un entero entre 0 y 11", 0, 11);
            switch (opciones){
                case 1:
                    registrarJugador(ElTemploDelArcade);
                    break;

                case 2:
                    registraMaquina(ElTemploDelArcade);
                    break;

                case 3: ;
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
                    System.out.println(ElTemploDelArcade.jugadorMasActivo());
                    break;

                case 10:
                    System.out.println(ElTemploDelArcade.maquinaConMasPartidasJugadas());
                    break;

                case 11:
                    Vista.mostrarRanking(ElTemploDelArcade);
                    break;
            }
        }while (opciones != 0);
    }

    public static void mostrarRanking (SalaRecreativa sala){
        String nombre  = Utils.pideCadena("Introduce el nombre de la máquina para ver su ranking:", "Error, debes de introducir el nombre de una máquina que esté en la sala");
        MaquinaArcade maquina = sala.buscarNombreMaquina(nombre);
        if (maquina == null) {
            System.out.println("No existe ninguna máquina con ese nombre.");
        }
        System.out.println(sala.listarMaquinasActivas());
    }

    /**
     * Método con el que podemos jugar una partida a través del ID del jugador y la máquina a la que queremos jugar
     * @param sala Donde están los jugadores y máquinas
     */
    public static void jugarUnaPartida (SalaRecreativa sala) {
        Jugador jugador = sala.buscarIDJugador(Utils.pideEntero("Introduce la ID del jugador que quiere jugar: ", "Error, esa ID no existe"));
        MaquinaArcade maquina = sala.buscarNombreMaquina(Utils.pideCadena("Introduce el nombre de la máquina a la que quieres jugar:", "Error, esa máquina no existe en la sala"));

        if (jugador.getCreditosDisponibles() < maquina.getPrecioPorPartida()){
            System.out.println("No puedes jugar ya que no tienes créditos disponibles");
        }
        sala.gestionarPartida(jugador, maquina);
    }

    /**
     * Método con el que reactivamos una máquina elegida por el usuario
     * @param sala Sala donde se encuentran las máquinas
     */
    public static void reactivarMaquina(SalaRecreativa sala) {
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina que quieres reactivar:", "Error, debes de introducir un String");
        MaquinaArcade maquina = sala.buscarNombreMaquina(nombre);
        if (maquina == null) {
            System.out.println("No existe ninguna máquina con ese nombre.");
        } else {
            maquina.cambiarEstado(1); // Activar
            System.out.println("La máquina ha sido reactivada correctamente.");
        }
    }

    /**
     * Método con el que el usuario introduce por consola un nuevo jugador
     */
    public static void registrarJugador (SalaRecreativa sala) {
        String nombre = Utils.pideCadena("Introduce el nombre del jugador: ", "Error, debes introducir un nombre").trim();
        int creditosDisponibles = Utils.pideEntero("Introduce los créditos que tiene el jugador: ", "Error, debes de introducir un entero.");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
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
     * Método con el que el usuario introduce por consola una nueva máquina
     * @return Devuelve una nueva máquina de la clase Máquina
     */
    public static void registraMaquina (SalaRecreativa sala){
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina: ", "Error, debes introducir un nombre").trim();
        String genero = Utils.pideCadena("Introduce el genero de la máquina: ", "Error, debes introducir un genero").trim();
        int precioPorPartida = Utils.pideEntero("Introduce los créditos que va a costar jugar una partida: ", "Error, debes de introducir un entero.");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        if(genero.isEmpty()){
            System.out.println("El género no puede estar vació");
        }
        MaquinaArcade nuevaMaquina = new MaquinaArcade(nombre, genero, precioPorPartida);
        boolean add = sala.addMaquina(nuevaMaquina);
        if (add) {
            System.out.println("La máquina ha sido añadida correctamente.");
        } else {
            System.out.println("No se ha añadido la máquina porque no se pueden añadir más máquinas");
        }
    }

    /**
     * Método con el que recargamos los creditos de un jugador, donde el usuario elige al jugador que quiere recargarle los créditos y cuantos va a añadir
     * @param sala Sala donde está el jugador al que queremos recargarle los créditos
     */
    public static void recargarCreditos(SalaRecreativa sala){
        int id = Utils.pideEntero("Introduce el ID del jugador: ", "Error, debes de introducir un String");
        Jugador jugador = sala.buscarIDJugador(id);
        if (jugador == null) {
            System.out.println("No existe un jugador con ese ID.");
            return;
        }
        int cantidad = Utils.pideEntero("¿Cuántos créditos quieres recargar?: ", "Error, debes de introducir un entero");
        jugador.recargarCreditos(cantidad);
        System.out.println("Créditos recargados. Ahora tiene: " + jugador.getCreditosDisponibles());
    }
}