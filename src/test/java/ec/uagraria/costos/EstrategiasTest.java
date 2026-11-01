package ec.uagraria.costos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiasTest {

    private Lote crearLote() {
        return new Lote(
                "L-01",
                new Banano("Cavendish"),
                4.0,
                List.of(
                        new Insumo("Urea 50 kg", 12, "saco", 32.50),
                        new Insumo("Fungicida", 8, "litro", 21.75),
                        new Insumo("Semilla certificada", 1, "lote", 180.00)
                )
        );
    }

    @Test
    void probarCostoDirecto() {
        Lote lote = crearLote();

        assertEquals(744.00, Estrategias.costoDirecto(lote), 0.01);
    }

    @Test
    void probarCostoConManoDeObra() {
        Lote lote = crearLote();

        assertEquals(2076.00,
                Estrategias.costoConManoDeObra(lote), 0.01);
    }

    @Test
    void probarCostoPorHectarea() {
        Lote lote = crearLote();

        assertEquals(519.00,
                Estrategias.costoPorHectarea(lote), 0.01);
    }

    @Test
    void probarCostoConSubsidio() {
        Lote lote = crearLote();

        assertEquals(1998.00,
                Estrategias.costoConSubsidioFertilizante(lote, 0.20),
                0.01);
    }

    @Test
    void probarCostoConIva() {
        assertEquals(2387.40,
                Estrategias.conIva(2076.00), 0.01);
    }

    @Test
    void probarOptionalVacio() {
        CalculadoraCostos calculadora =
                new CalculadoraCostos(null);

        Optional<Double> resultado =
                calculadora.calcular(crearLote());

        assertTrue(resultado.isEmpty());
    }

    @Test
    void probarCalculadoraConEstrategia() {
        CalculadoraCostos calculadora =
                new CalculadoraCostos(Estrategias::costoDirecto);

        Optional<Double> resultado =
                calculadora.calcular(crearLote());

        assertTrue(resultado.isPresent());
        assertEquals(744.00, resultado.orElseThrow(), 0.01);
    }
}

