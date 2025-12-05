import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;
import utils.Utils;
public class Principal {
    public static void main(String[] args) throws Exception {
        SalaRecreativa sala1 = new SalaRecreativa();
        MaquinaArcade pingball = new MaquinaArcade("Pingball", "Juegos de salón", 20);
        MaquinaArcade DonkeyKong = new MaquinaArcade("Donkey Kong", "Plataforma", 15);
        Jugador j1 = new Jugador("Dani", "0001", 30);
        Jugador j2 = new Jugador("Juan", "0002", 15);

        pingball.cambiarEstado(1);
        pingball.imprimirEstado();

        DonkeyKong.cambiarEstado(0);
        DonkeyKong.imprimirEstado();

        System.out.println(pingball.nuevaPartida());
        System.out.println(pingball.nuevaPartida());
        System.out.println(pingball.nuevaPartida());

        System.out.println(pingball);

        j1.recargarCreditos(300);
        System.out.println(j1.getCreditosDisponibles());

        j1.gastarCreditos(pingball.getPrecioPorPartida());
        System.out.println(j1.getCreditosDisponibles());

        j1.incrementarNumeroPartidas();
        j1.incrementarNumeroPartidas();
        j1.incrementarNumeroPartidas();
        j1.incrementarNumeroPartidas();

        System.out.println(j1);

        sala1.addJugador(j2);
        sala1.addJugador(j1);

        sala1.addJugador(j1);
        System.out.println(sala1);
    }
}