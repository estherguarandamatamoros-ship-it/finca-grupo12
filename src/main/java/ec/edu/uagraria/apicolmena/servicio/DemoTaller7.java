package ec.edu.uagraria.apicolmena.servicio;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.dominio.Floracion;
import ec.edu.uagraria.apicolmena.dominio.InsumoApicola;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DemoTaller7 {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   DEMOSTRACION JAVA COLLECTIONS");
        System.out.println("       SISTEMA APICOLA");
        System.out.println("======================================");

        // 1. Crear objetos del dominio

        Floracion floracion = new Floracion(
                "Floracion de girasol",
                "Helianthus annuus",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 30),
                80.0
        );

        Colmena colmena1 = new Colmena(
                "COL-001",
                "Sector Norte",
                2.5,
                LocalDate.of(2026, 11, 20),
                25.0,
                22.0
        );

        Colmena colmena2 = new Colmena(
                "COL-002",
                "Sector Sur",
                1.8,
                LocalDate.of(2026, 11, 22),
                30.0,
                27.0
        );

        InsumoApicola insumo1 = new InsumoApicola(
                "INS-001",
                "Alimento para abejas",
                10.0,
                "kg",
                2.50
        );

        InsumoApicola insumo2 = new InsumoApicola(
                "INS-001",
                "Alimento para abejas - segundo lote",
                5.0,
                "kg",
                2.50
        );

        // 2. Crear el inventario y registrar cinco elementos

        Inventario inventario = new Inventario();

        ItemInventario item1 = new ItemInventario(
                "FLO-001",
                "FLORACION",
                floracion.nombre(),
                floracion.nivelNectar()
        );

        ItemInventario item2 = new ItemInventario(
                colmena1.codigo(),
                "COLMENA",
                colmena1.ubicacion(),
                colmena1.rendimientoActualKg()
        );

        ItemInventario item3 = new ItemInventario(
                colmena2.codigo(),
                "COLMENA",
                colmena2.ubicacion(),
                colmena2.rendimientoActualKg()
        );

        ItemInventario item4 = new ItemInventario(
                insumo1.codigo(),
                "INSUMO",
                insumo1.nombre(),
                insumo1.cantidad()
        );

        ItemInventario item5 = new ItemInventario(
                insumo2.codigo(),
                "INSUMO",
                insumo2.nombre(),
                insumo2.cantidad()
        );

        inventario.registrar(item1);
        inventario.registrar(item2);
        inventario.registrar(item3);
        inventario.registrar(item4);
        inventario.registrar(item5);

        // 3. LIST: mostrar los registros del inventario

        System.out.println("\n1. LIST - REGISTROS DEL INVENTARIO");

        List<ItemInventario> registros = inventario.registros();

        registros.forEach(System.out::println);

        // 4. SET: mostrar códigos únicos y ordenados

        System.out.println("\n2. SET - CODIGOS UNICOS");

        Set<String> codigos = inventario.codigosUnicos();

        codigos.forEach(System.out::println);

        System.out.println("Total de registros: " + registros.size());
        System.out.println("Total de codigos unicos: " + codigos.size());

        // 5. MAP: calcular cantidades acumuladas por tipo

        System.out.println("\n3. MAP - TOTAL POR TIPO");

        Map<String, Double> totales = inventario.totalPorTipo();

        totales.forEach((tipo, total) ->
                System.out.println(tipo + ": " + total)
        );

        // 6. COMPARATOR: ordenar por cantidad descendente

        System.out.println("\n4. COMPARATOR - ORDEN POR CANTIDAD");

        List<ItemInventario> ordenados =
                inventario.ordenadosPorCantidad();

        ordenados.forEach(System.out::println);

        // 7. DEQUE: programar colmenas para cosecha

        System.out.println("\n5. DEQUE - COLMENAS PROGRAMADAS");

        inventario.encolarDespacho(item2);
        inventario.encolarDespacho(item3);

        System.out.println("Colmenas pendientes: "
                + inventario.pendientesDespacho());

        Optional<ItemInventario> despacho1 =
                inventario.despacharSiguiente();

        System.out.println("Primer despacho: "
                + despacho1.map(ItemInventario::descripcion)
                .orElse("No hay elementos"));

        Optional<ItemInventario> despacho2 =
                inventario.despacharSiguiente();

        System.out.println("Segundo despacho: "
                + despacho2.map(ItemInventario::descripcion)
                .orElse("No hay elementos"));

        System.out.println("Colmenas pendientes: "
                + inventario.pendientesDespacho());

        // 8. OPTIONAL: comprobar el resultado del despacho

        System.out.println("\n6. OPTIONAL - CONSULTA DE DESPACHO");

        Optional<ItemInventario> siguiente =
                inventario.despacharSiguiente();

        System.out.println("¿Existe otro despacho? "
                + siguiente.isPresent());

        // 9. LIST.COPYOF: comprobar lista inmutable

        System.out.println("\n7. LIST.COPYOF - LISTA INMUTABLE");

        List<ItemInventario> copiaInmutable =
                List.copyOf(inventario.registros());

        System.out.println("Cantidad de elementos en la copia: "
                + copiaInmutable.size());

        try {
            copiaInmutable.add(item1);
        } catch (UnsupportedOperationException e) {
            System.out.println(
                    "No se permiten modificaciones: lista inmutable."
            );
        }

        System.out.println("\n======================================");
        System.out.println("   FIN DE LA DEMOSTRACION");
        System.out.println("======================================");
    }
}


