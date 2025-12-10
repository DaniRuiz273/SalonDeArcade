package utils;
import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;
public class Vista {

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
     * @throws Exception Si la máquina no está activada o el jugador no tiene créditos disponibles, lanzará esta excepción
     */
    public static void jugarUnaPartida (SalaRecreativa sala) throws Exception {
        Jugador jugador = sala.buscarIDJugador(Utils.pideCadena("Introduce la ID del jugador que va a jugar la partida:", "Error, esa ID no existe en la sala"));
        MaquinaArcade maquina = sala.buscarNombreMaquina(Utils.pideCadena("Introduce el nombre de la máquina a la que quieres jugar:", "Error, esa máquina no existe en la sala"));

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
     * @return Devuelve a un nuevo jugador de la clase Jugador
     */
    public static Jugador registraJugador (){
        String nombre = Utils.pideCadena("Introduce el nombre: ", "Error");
        String idUnico = Utils.pideCadena("Introduce el ID del jugador: ", "Error");
        int creditosDisponibles = Utils.pideEntero("Introduce los créditos que tiene el jugador: ", "Error, debes de introducir un entero.");

        return new Jugador(nombre, idUnico, creditosDisponibles);
    }

    /**
     * Método con el que el usuario introduce por consola una nueva máquina
     * @return Devuelve una nueva máquina de la clase Máquina
     */
    public static MaquinaArcade registraMaquina (){
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina: ", "Error, debes de introducir un string");
        String genero = Utils.pideCadena("Introduce el genero de la máquina: ", "Error, debes de introducir un string");
        int precioPorPartida = Utils.pideEntero("Introduce el precio por partida: ", "Error, debes de introducir un entero");

        return new MaquinaArcade(nombre, genero, precioPorPartida);
    }

    /**
     * Método con el que recargamos los creditos de un jugador, donde el usuario elige al jugador que quiere recargarle los créditos y cuantos va a añadir
     * @param sala Sala donde está el jugador al que queremos recargarle los créditos
     */
    public static void recargarCreditos(SalaRecreativa sala){
        String id = Utils.pideCadena("Introduce el ID del jugador: ", "Error, debes de introducir un String").trim();
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