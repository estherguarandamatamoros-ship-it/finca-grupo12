package ec.uagraria.costos;

import java.util.List;

public record Lote(
        String codigo,
        Cultivo cultivo,
        double hectareas,
        List<Insumo> insumos
) {

    public Lote {
        if (codigo == null || cultivo == null || insumos == null) {
            throw new IllegalArgumentException("Los datos no pueden ser nulos");
        }

        if (hectareas <= 0) {
            throw new IllegalArgumentException("Las hectáreas deben ser mayores a cero");
        }

        insumos = List.copyOf(insumos);
    }

    public double costoInsumos() {
        return insumos.stream()
                .mapToDouble(Insumo::costo)
                .sum();
    }

    public int jornalesPorHectarea() {
        return switch (cultivo) {
            case Banano b -> 18;
            case Cacao c when c.organico() -> 25;
            case Cacao c -> 20;
            case Arroz a -> 12 * a.ciclosPorAnio();
        };
    }
}
