package ec.edu.uagraria.apicolmena.servicio;

/**
 * Representa un producto registrado en el inventario.
 * Se utiliza un record para mantener los datos inmutables.
 */
public record ItemInventario(
        String codigo,
        String tipo,
        String descripcion,
        double cantidad
) {
    public ItemInventario {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                    "La cantidad no puede ser negativa"
            );
        }
    }
}
