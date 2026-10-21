package ec.edu.uagraria.finca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParcelaTest {

    @Test
    void convierteHectareasEnMetrosCuadrados() {
        Parcela parcela = new Parcela("P-01", 2.5);

        assertEquals(25_000.0, parcela.areaEnMetrosCuadrados(), 0.001);
    }

    @Test
    void rechazaHectareasNoPositivas() {
        assertThrows(IllegalArgumentException.class,
                () -> new Parcela("P-02", -1));
    }
}

