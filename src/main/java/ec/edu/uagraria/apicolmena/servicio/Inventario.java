
package ec.edu.uagraria.apicolmena.servicio;

import java.util.*;

public class Inventario {

    private final List<ItemInventario> registros = new ArrayList<>();
    private final Deque<ItemInventario> colaDespacho = new ArrayDeque<>();

    // 1. LIST: registrar productos
    public void registrar(ItemInventario item) {
        registros.add(Objects.requireNonNull(item));
    }

    // 2. SET: obtener codigos unicos
    public Set<String> codigosUnicos() {
        Set<String> codigos = new TreeSet<>();

        for (ItemInventario i : registros) {
            codigos.add(i.codigo());
        }

        return codigos;
    }

    // 3. MAP: calcular el total por tipo
    public Map<String, Double> totalPorTipo() {
        Map<String, Double> totales = new TreeMap<>();

        for (ItemInventario i : registros) {
            totales.merge(i.tipo(), i.cantidad(), Double::sum);
        }

        return totales;
    }

    // 4. COMPARATOR: ordenar de mayor a menor cantidad
    public List<ItemInventario> ordenadosPorCantidad() {
        List<ItemInventario> copia = new ArrayList<>(registros);

        copia.sort(
                Comparator.comparingDouble(ItemInventario::cantidad)
                        .reversed()
                        .thenComparing(ItemInventario::codigo)
        );

        return copia;
    }

    // 5. DEQUE: agregar a la cola de despacho
    public void encolarDespacho(ItemInventario item) {
        colaDespacho.offerLast(Objects.requireNonNull(item));
    }

    // Despachar el primer elemento (FIFO)
    public Optional<ItemInventario> despacharSiguiente() {
        return Optional.ofNullable(colaDespacho.pollFirst());
    }

    // Consultar pendientes
    public int pendientesDespacho() {
        return colaDespacho.size();
    }

    // Devolver una lista inmutable
    public List<ItemInventario> registros() {
        return List.copyOf(registros);
    }
}

