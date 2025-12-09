package utils;
import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;

public class Vista {
    public static Jugador registraJugador (){
        String nombre = Utils.pideCadena("Introduce el nombre: ", "Error");
        String idUnico = Utils.pideCadena("Introduce el ID del jugador: ", "Error");
        int creditosDisponibles = Utils.pideEntero("Introduce los creditos que tiene el jugador: ", "Error, debes de introducir un entero.");

        return new Jugador(nombre, idUnico, creditosDisponibles);
    }

    public static MaquinaArcade registraMaquina (){
        String nombre = Utils.pideCadena("Introduce el nombre de la máquina: ", "Error, debes de introducir un string");
        String genero = Utils.pideCadena("Introduce el genero de la máquina: ", "Error, debes de introducir un string");
        int precioPorPartida = Utils.pideEntero("Introduce el precio por partida: ", "Error, debes de introducir un entero");

        return new MaquinaArcade(nombre, genero, precioPorPartida);
    }

    public static void recargarCreditos(SalaRecreativa sala) throws Exception {
        String id = Utils.pideCadena("Introduce el ID del jugador: ", "Error, debes de introducir un String");
        Jugador jugador = sala.buscarIDJugador(id);
        if (jugador == null) {
            System.out.println("No existe un jugador con ese ID.");
            return;
        }
        int cantidad = Utils.pideEntero("¿Cuántos créditos quieres recargar?: ", "Error, debes de introducir un entero");
        jugador.recargarCreditos(cantidad);
        System.out.println("Créditos recargados. Ahora tiene: " +
                jugador.getCreditosDisponibles());
    }
}
