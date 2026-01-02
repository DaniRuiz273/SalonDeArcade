package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class SalaRecreativaTest {

    @Test
    void noSePuedeJugarSinCreditos() {
        SalaRecreativa sala = new SalaRecreativa();
        Jugador j = new Jugador("Juan", 0);
        MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);

        sala.addJugador(j);
        sala.addMaquina(m);

        sala.gestionarPartida(j.getIdUnico(), m.getNombreMaquina());

        assertEquals(0, j.getNumeroPartidasJugadas());
        }

        @Test
        void noSePuedeJugarSiLaMaquinaEstaInactiva() {
            SalaRecreativa sala = new SalaRecreativa();
            Jugador j = new Jugador("Juan", 20);
            MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);

            sala.darDeBajaMaquina(String.valueOf(m));

            sala.addJugador(j);
            sala.addMaquina(m);

            sala.gestionarPartida(j.getIdUnico(), m.getNombreMaquina());
            assertEquals(0, j.getNumeroPartidasJugadas());
        }

        @Test
        void alJugarPartida_elCreditoDisminuye() {
            SalaRecreativa sala = new SalaRecreativa();
            Jugador j = new Jugador("Juan", 20);
            MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);

            sala.addJugador(j);
            sala.addMaquina(m);

            sala.gestionarPartida(j.getIdUnico(), m.getNombreMaquina());

            assertEquals(15, j.getCreditosDisponibles());
        }

}

