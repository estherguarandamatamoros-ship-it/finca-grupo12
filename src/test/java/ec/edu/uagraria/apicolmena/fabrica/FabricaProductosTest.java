package ec.edu.uagraria.apicolmena.fabrica;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.dominio.Floracion;
import ec.edu.uagraria.apicolmena.dominio.InsumoApicola;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FabricaProductosTest {

    @Test
    void debeCrearFloracion() {
        Floracion floracion = FabricaProductos.crearFloracion(
                "Floracion de girasol",
                "Girasol",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 30),
                85
        );

        assertNotNull(floracion);
        assertEquals("Floracion de girasol", floracion.nombre());
        assertEquals(85, floracion.nivelNectar());
    }

    @Test
    void debeCrearColmena() {
        Colmena colmena = FabricaProductos.crearColmena(
                "COL-001",
                "Sector norte",
                2.5,
                LocalDate.of(2026, 11, 5),
                25,
                30
        );

        assertNotNull(colmena);
        assertEquals("COL-001", colmena.codigo());
        assertEquals(2.5, colmena.porcentajeVarroa());
    }

    @Test
    void debeCrearInsumoApicola() {
        InsumoApicola insumo = FabricaProductos.crearInsumoApicola(
                "INS-001",
                "Alimento para abejas",
                10,
                "kg",
                2.50
        );

        assertNotNull(insumo);
        assertEquals("INS-001", insumo.codigo());
        assertEquals(25.0, insumo.calcularCosto(), 0.001);
    }

@Test
void noDebeCrearColmenaConVarroaSuperiorAl100() {
    assertThrows(IllegalArgumentException.class, () ->
            FabricaProductos.crearColmena(
                    "COL-002",
                    "Sector sur",
                    105,
                    LocalDate.of(2026, 11, 5),
                    25,
                    30
            )
    );
}

@Test
void noDebeCrearFloracionConFechasIncorrectas() {
    assertThrows(IllegalArgumentException.class, () ->
            FabricaProductos.crearFloracion(
                    "Floracion de mango",
                    "Mango",
                    LocalDate.of(2026, 11, 30),
                    LocalDate.of(2026, 11, 1),
                    80
            )
    );
}

@Test
void noDebeCrearInsumoConPrecioNegativo() {
    assertThrows(IllegalArgumentException.class, () ->
            FabricaProductos.crearInsumoApicola(
                    "INS-002",
                    "Alimento",
                    10,
                    "kg",
                    -2.50
            )
    );
}

}
