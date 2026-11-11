package ec.edu.uagraria.apicolmena.dominio;

public record InsumoApicola(
        String codigo,
        String nombre,
        double cantidad,
        String unidad,
        double precioUnitario
) {

    public InsumoApicola {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El codigo del insumo es obligatorio"
            );
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del insumo es obligatorio"
            );
        }

        if (!Double.isFinite(cantidad) || cantidad < 0) {
            throw new IllegalArgumentException(
                    "La cantidad no puede ser negativa"
            );
        }

        if (unidad == null || unidad.isBlank()) {
            throw new IllegalArgumentException(
                    "La unidad de medida es obligatoria"
            );
        }

        if (!Double.isFinite(precioUnitario) || precioUnitario < 0) {
            throw new IllegalArgumentException(
                    "El precio unitario no puede ser negativo"
            );
        }
    }

    public double calcularCosto() {
        return cantidad * precioUnitario;
    }
}