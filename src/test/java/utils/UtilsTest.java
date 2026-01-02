package utils;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    void comprobarRango_dentroDelRango() {
        assertTrue(Utils.comprobarRango(5, 1, 10));
        assertTrue(Utils.comprobarRango(1, 1, 10));
    }

    @Test
    void comprobarRango_fueraDelRango() {
        assertFalse(Utils.comprobarRango(0, 1, 10));
        assertFalse(Utils.comprobarRango(11, 1, 10));
    }
}