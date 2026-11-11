package ec.edu.uagraria.apicolmena.dominio;

import java.time.LocalDate;

public record Colmena(
        String codigo,
        String ubicacion,
        double porcentajeVarroa,
        LocalDate fechaUltimaInspeccion,
        double rendimientoActualKg,
        double rendimientoAnteriorKg
) {

    public Colmena {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El codigo de la colmena es obligatorio"
            );
        }

        if (ubicacion == null || ubicacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La ubicacion de la colmena es obligatoria"
            );
        }

        if (!Double.isFinite(porcentajeVarroa)
                || porcentajeVarroa < 0 || porcentajeVarroa > 100) {
            throw new IllegalArgumentException(
                    "El porcentaje de varroa debe estar entre 0 y 100"
            );
        }

        if (fechaUltimaInspeccion == null) {
            throw new IllegalArgumentException(
                    "La fecha de inspeccion es obligatoria"
            );
        }

        if (!Double.isFinite(rendimientoActualKg)
                || rendimientoActualKg < 0) {
            throw new IllegalArgumentException(
                    "El rendimiento actual no puede ser negativo"
            );
        }

        if (!Double.isFinite(rendimientoAnteriorKg)
                || rendimientoAnteriorKg < 0) {
            throw new IllegalArgumentException(
                    "El rendimiento anterior no puede ser negativo"
            );
        }
    }
}