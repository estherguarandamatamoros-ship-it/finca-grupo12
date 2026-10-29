package Semana2.finca;

public record Producto(
        String codigo,
        String nombre,
        double precioUnitario,
        int unidades
) implements Activo {

    public Producto {
        if (codigo == null || codigo.isBlank()
                || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El código y el nombre son obligatorios");
        }

        if (!Double.isFinite(precioUnitario)
                || precioUnitario < 0 || unidades < 0) {
            throw new IllegalArgumentException(
                    "El precio y las unidades no pueden ser negativos");
        }
    }
}
