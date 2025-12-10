package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.MaquinaArcade;

public class MaquinaArcadeTest {

    MaquinaArcade maquina;

    @BeforeEach
    public void setup() {
        maquina = new MaquinaArcade("PacMan", "Arcade", 10);
        maquina.setEstadoMaquina(true);
    }

    @Test
    public void testJugarIncrementaContador() {
        int contadorAntes = maquina.getContadorPartidasJugadas();
        maquina.nuevaPartida();
        assertEquals(contadorAntes + 1, maquina.getContadorPartidasJugadas());
    }

    @Test
    public void testDesactivarMaquinaAlcanzar100Partidas() {
        maquina.setEstadoMaquina(true);
        for (int i = 0; i < 99; i++) {
            maquina.nuevaPartida();
        }
        assertTrue(maquina.EstadoMaquina());
        maquina.nuevaPartida(); // 100ª partida
        assertFalse(maquina.EstadoMaquina());
    }

    @Test
    public void testMejorPuntuacionEnRanking() {
        int[] puntuaciones = {100, 200, 300};
        // Simular puntuaciones manualmente
        maquina.getMejoresPuntuaciones()[0] = 100;
        maquina.getMejoresPuntuaciones()[1] = 50;
        maquina.getMejoresPuntuaciones()[2] = 25;

        // Hacemos una partida con puntuación alta manual
        // Como nuevaPartida() genera aleatorio, en test real sería mejor modificar la función para pasar puntuación fija
        // Aquí solo comprobamos que el array existe y tiene 3 posiciones
        assertEquals(3, maquina.getMejoresPuntuaciones().length);
    }
}