package model;

import utils.Utils;

public class SalaRecreativa {
    private final int size = 5;
    private final Jugador [] jugadores;
    private final MaquinaArcade [] maquinas;
    private int capacidadJugadores;
    private int capacidadMaquinas;

    public MaquinaArcade buscarNombreMaquina (String nombre) throws Exception {
        for (MaquinaArcade maquina : maquinas){ // Recorre cada máquina del array y lo llamamos máquina
            if (maquina != null && maquina.getNombreMaquina().trim().equalsIgnoreCase(nombre.trim())){ // El nombre de la máquina lo compara con el nombre de la máquina que estamos buscando
                return maquina; // Devuelve la máquina si coincide con su nombre
            }
        }
        throw new Exception ("No existe ninguna máquina con ese nombre"); // Si buscamos una máquina con un nombre que no existe lanzará esta excepción
    }

    public Jugador buscarIDJugador (String idUnico) throws Exception{
        for (Jugador jugador : jugadores){ // Recorre cada jugador del array y lo llamamos jugador
            if(jugador.getIdUnico().equals(idUnico)){ // El idUnico del jugador lo compara con el idUnico que estamos buscando
                return jugador; // Devuelve el jugador si coincide con el idUnico
            } else {
                throw new Exception ("No existe ningún jugador con esa ID"); // Si buscamos un idUnico que no existe lanzará esta excepción
            }
        }
        return null; // Si no encuentra a ningún jugador con ese id devuelve null
    }

    public void addJugador (Jugador jugadorAdd) throws Exception {
        if(this.capacidadJugadores < size){ // Comprobamos que haya capacidad en la sala
            this.jugadores[this.capacidadJugadores] = jugadorAdd; // Si hay espacio en la sala entonces añadimos un jugador
            capacidadJugadores++; // Aumenta la capacidadActual, es decir, que ahora en la sala hay un jugador más
        }else {
            throw new Exception ("No se pueden registrar más jugadores porque no hay más espacio"); // Cuando no hay más sitio para jugadores en la sala e intentamos añadir uno lanzará la excepción
        }
    }

    public void addMaquina (MaquinaArcade maquinaAdd) throws Exception {
        if(this.capacidadMaquinas < size){ // Comprobamos que haya capacidad en la sala
            this.maquinas[this.capacidadMaquinas] = maquinaAdd; // Si hay espacio en la sala entonces añadimos un jugador
            capacidadMaquinas++; // Aumenta la capacidadActual, es decir, que ahora en la sala hay un jugador más
        }else {
            throw new Exception ("No se pueden registrar más jugadores porque no hay más espacio"); // Cuando no hay más sitio para jugadores en la sala e intentamos añadir uno lanzará la excepción
        }
    }

    public String toString() {
        String texto = "Jugadores en la sala:\n";
        if(capacidadJugadores == 0){
            texto += "No hay jugadores en la sala";
        } else {
            for (int i = 0; i < capacidadJugadores; i++) {   // Recorre el array hasta la capacidad actual
                texto += jugadores[i] + "\n";
            }
        }

        texto += "Maquinas en la sala : \n";
        if(capacidadMaquinas == 0){
            texto += "No hay máquinas en la sala";
        } else {
            for(int i = 0; i < capacidadMaquinas; i++){
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
