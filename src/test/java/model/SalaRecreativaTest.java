package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

public class SalaRecreativaTest {

    SalaRecreativa sala;
    Jugador jugador;
    MaquinaArcade maquina;

    @BeforeEach
    public void setup() throws Exception {
        sala = new SalaRecreativa();
        jugador = new Jugador("Mi Rey", "ID123", 50);
        maquina = new MaquinaArcade("PacMan", "laberintos", 20); // Asumiendo constructor con nombre y precio

        sala.addJugador(jugador);
        sala.addMaquina(maquina);
    }

    @Test
    public void testNoJugarSiSinCreditos() {
        jugador.setCreditosDisponibles(0);
        Exception exception = assertThrows(Exception.class, () -> {
            sala.gestionarPartida(jugador, maquina);
        });
        assertEquals("No tiene suficientes créditos para jugar una partida", exception.getMessage());
    }

    @Test
    public void testNoJugarSiMaquinaInactiva() {
        maquina.cambiarEstado(1); // Asumiendo que existe setActiva()
        Exception exception = assertThrows(Exception.class, () -> {
            sala.gestionarPartida(jugador, maquina);
        });
        assertEquals("La máquina esta desactivada, no se puede jugar", exception.getMessage());
    }

    @Test
    public void testCreditoDisminuyeAlJugar() throws Exception {
        int saldoAntes = jugador.getCreditosDisponibles();
        sala.gestionarPartida(jugador, maquina);
        assertEquals(saldoAntes - maquina.getPrecioPorPartida(), jugador.getCreditosDisponibles());
    }

    @Test
    public void testIncrementaNumeroPartidasJugador() throws Exception {
        int partidasAntes = jugador.getNumeroPartidasJugadas();
        sala.gestionarPartida(jugador, maquina);
        assertEquals( 2, jugador.getNumeroPartidasJugadas());
    }
}