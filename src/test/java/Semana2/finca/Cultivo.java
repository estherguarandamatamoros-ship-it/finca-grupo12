package Semana2.finca;

public record Cultivo(
        String codigo,
        String nombre,
        double hectareas,
        double costoPorHectarea
) implements Activo {

    public Cultivo {
        if (codigo == null || codigo.isBlank()
                || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El código y el nombre son obligatorios");
        }

        if (!Double.isFinite(hectareas)
                || !Double.isFinite(costoPorHectarea)
                || hectareas <= 0 || costoPorHectarea < 0) {
            throw new IllegalArgumentException(
                    "Las hectáreas deben ser positivas y el costo no negativo");
        }
    }
}

