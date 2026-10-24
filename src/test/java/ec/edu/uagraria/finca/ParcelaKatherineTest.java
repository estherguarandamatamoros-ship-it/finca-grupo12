package ec.edu.uagraria.finca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParcelakatherineTest {

    @Test
    void mediaHectareaSonCincoMilMetros() {
        Parcela parcela = new Parcela("P-10", 0.5);

        assertEquals(5_000.0, parcela.areaEnMetrosCuadrados(), 0.001);
    }
}

