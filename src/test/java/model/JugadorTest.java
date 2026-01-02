package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class JugadorTest {

    @Test
    void recargarCreditos_aumentaSaldo() {
        Jugador j = new Jugador("Juan", 0);
        j.recargarCreditos(10);

        assertEquals(10, j.getCreditosDisponibles());
    }

    @Test
    void gastarCreditos_disminuyeSaldo() {
        Jugador j = new Jugador("Juan", 0);
        j.recargarCreditos(20);

        j.gastarCreditos(5);

        assertEquals(15, j.getCreditosDisponibles());
    }

    @Test
    void noSePuedenGastarMasCreditosDeLosDisponibles() {
        Jugador j = new Jugador("Juan", 0);
        j.recargarCreditos(5);

        boolean resultado = j.gastarCreditos(10);

        assertFalse(resultado);
        assertEquals(5, j.getCreditosDisponibles());
    }

    @Test
    void jugarPartida_incrementaNumeroDePartidas() {
        Jugador j = new Jugador("Juan", 50);

        j.incrementarNumeroPartidas();

        assertEquals(1, j.getNumeroPartidasJugadas());
    }
}
