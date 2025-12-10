package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Jugador;

public class JugadorTest {

    Jugador jugador;

    @BeforeEach
    public void setup() {
        jugador = new Jugador("Luis" , "0001", 50);
    }

    @Test
    public void testRecargarCreditos() {
        jugador.recargarCreditos(30);
        assertEquals(80, jugador.getCreditosDisponibles());
    }

    @Test
    public void testGastarCreditos() {
        jugador.gastarCreditos(20);
        assertEquals(30, jugador.getCreditosDisponibles());
    }

    @Test
    public void testNoGastarMasCreditosDeLosQueTiene() {
        jugador.gastarCreditos(100); // su saldo actual es 50
        assertEquals(50, jugador.getCreditosDisponibles(),
                "No debería gastar más créditos de los disponibles");
    }

    @Test
    public void testIncrementarNumeroPartidas() {
        int partidasAntes = jugador.getNumeroPartidasJugadas();
        jugador.incrementarNumeroPartidas();
        assertEquals(partidasAntes + 1, jugador.getNumeroPartidasJugadas());
    }
}