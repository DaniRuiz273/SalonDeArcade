package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    Jugador jugador = new Jugador("Dani", "0001", 20);

    @Test
    public void recarga_creditos (){
        assertEquals(20, 20);
        jugador.recargarCreditos(30);
        assertEquals(50, 20);
    }
}