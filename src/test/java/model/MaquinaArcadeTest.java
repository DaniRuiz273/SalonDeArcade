package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class MaquinaArcadeTest {

    @Test
    void jugarPartida_incrementaContador() {
        MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);
        Jugador j = new Jugador("Juan", 5);

        m.nuevaPartida(j);

        assertEquals(1, m.getContadorPartidasJugadas());
    }

    @Test
    void ranking_conCuatroPartidas() {
        MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);
        Jugador j = new Jugador("Juan", 50);

        int p1 = m.nuevaPartida(j);
        int p2 = m.nuevaPartida(j);
        int p3 = m.nuevaPartida(j);
        int p4 = m.nuevaPartida(j);

        int[] ranking = m.rankingMaquina(j);

        int mejor = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        assertEquals(mejor, ranking[0]);

        int peor = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        assertFalse(ranking[0] == peor || ranking[1] == peor || ranking[2] == peor);
    }


    @Test
    void alLlegarACienPartidas_laMaquinaSeDesactiva() {
        MaquinaArcade m = new MaquinaArcade("Pacman", "Arcade", 5);
        Jugador j = new Jugador("Juan", 5);

        for (int i = 0; i < 100; i++) {
            m.nuevaPartida(j);
        }
        assertFalse(m.EstadoMaquina());
    }
}