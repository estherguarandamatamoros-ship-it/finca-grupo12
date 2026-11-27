package ec.edu.uagraria.apicolmena.servicio;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    @Test
    void debeRegistrarProductos() {
        Inventario inventario = new Inventario();

        inventario.registrar(
                new ItemInventario("F001", "Floracion", "Flor de mango", 20)
        );

        assertEquals(1, inventario.registros().size());
    }

    @Test
    void debeObtenerCodigosUnicos() {
        Inventario inventario = crearInventario();

        Set<String> codigos = inventario.codigosUnicos();

        assertEquals(5, codigos.size());
        assertTrue(codigos.contains("C001"));
    }

    @Test
    void debeCalcularTotalPorTipo() {
        Inventario inventario = crearInventario();

        Map<String, Double> totales = inventario.totalPorTipo();

        assertEquals(30.0, totales.get("Floracion"));
        assertEquals(15.0, totales.get("Colmena"));
    }

    @Test
    void debeOrdenarPorCantidadDeMayorAMenor() {
        Inventario inventario = crearInventario();

        List<ItemInventario> ordenados =
                inventario.ordenadosPorCantidad();

        assertEquals(50.0, ordenados.get(0).cantidad());
        assertEquals(5.0, ordenados.get(4).cantidad());
    }

    @Test
    void debeDespacharEnOrdenFifo() {
        Inventario inventario = crearInventario();

        ItemInventario primero = inventario.registros().get(0);
        inventario.encolarDespacho(primero);

        assertEquals(1, inventario.pendientesDespacho());
        assertEquals(primero, inventario.despacharSiguiente().orElseThrow());
        assertEquals(0, inventario.pendientesDespacho());
    }

    @Test
    void debeDevolverUnaListaInmutable() {
        Inventario inventario = crearInventario();

        assertThrows(
                UnsupportedOperationException.class,
                () -> inventario.registros().add(
                        new ItemInventario("X001", "Prueba", "Producto", 1)
                )
        );
    }

    private Inventario crearInventario() {
        Inventario inventario = new Inventario();

        inventario.registrar(
                new ItemInventario("F001", "Floracion", "Flor de mango", 20)
        );
        inventario.registrar(
                new ItemInventario("F002", "Floracion", "Flor de cítricos", 10)
        );
        inventario.registrar(
                new ItemInventario("C001", "Colmena", "Colmena tipo Langstroth", 15)
        );
        inventario.registrar(
                new ItemInventario("I001", "InsumoApicola", "Cera estampada", 50)
        );
        inventario.registrar(
                new ItemInventario("I002", "InsumoApicola", "Alimentador", 5)
        );

        return inventario;
    }
}