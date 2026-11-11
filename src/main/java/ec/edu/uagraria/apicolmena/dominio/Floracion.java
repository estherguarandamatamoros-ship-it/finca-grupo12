package ec.edu.uagraria.apicolmena.dominio;

import java.time.LocalDate;

public record Floracion(
        String nombre,
        String especieVegetal,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        double nivelNectar
) {

    public Floracion {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la floracion es obligatorio"
            );
        }

        if (especieVegetal == null || especieVegetal.isBlank()) {
            throw new IllegalArgumentException(
                    "La especie vegetal es obligatoria"
            );
        }

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException(
                    "Las fechas de la floracion son obligatorias"
            );
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException(
                    "La fecha final no puede ser anterior a la fecha inicial"
            );
        }

        if (!Double.isFinite(nivelNectar)
                || nivelNectar < 0 || nivelNectar > 100) {
            throw new IllegalArgumentException(
                    "El nivel de nectar debe estar entre 0 y 100"
            );
        }
    }
}
