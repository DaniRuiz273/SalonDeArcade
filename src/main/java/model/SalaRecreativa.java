package model;
public class SalaRecreativa {
    private final int size = 5;
    private Jugador [] jugadores;
    private int [] maquinasArcade;
    private int capacidadActual;

    public void addJugador (Jugador jugadorAdd) throws Exception {
        if(capacidadActual < size){ // Comprobamos que haya capacidad en la guarida
            jugadores[capacidadActual] = jugadorAdd; // Si hay espacio en la guarida, añadimos un dragon
            capacidadActual++; // Aumenta la capacidadActual, esto quiere decir que ahora en la guarida hay un dragon más
        }else {
            throw new Exception ("No se pueden registrar más jugadores porque no hay más espacio"); // Cuando no haya más espacio en la guarida e intentas añadir un dragon saltará esta excepción
        }
    }

    public int[] getMaquinasArcade() {
        return maquinasArcade;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
}
