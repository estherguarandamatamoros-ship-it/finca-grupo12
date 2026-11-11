package ec.edu.uagraria.apicolmena.fabrica;

public record DatosInsumoApicola(
        String nombre,
        double cantidad,
        String unidad,
        double precioUnitario
) implements DatosProducto {
}
