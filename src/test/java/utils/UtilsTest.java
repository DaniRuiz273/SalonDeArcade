package utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UtilsTest {

    @Test
    public void testComprobarRango() {
        // Casos en los que sí está en rango
        assertTrue(Utils.comprobarRango(5, 1, 10));
        assertTrue(Utils.comprobarRango(1, 1, 10));

        // Casos en los que no está en rango
        assertFalse(Utils.comprobarRango(0, 1, 10));
        assertFalse(Utils.comprobarRango(11, 1, 10));
    }
}