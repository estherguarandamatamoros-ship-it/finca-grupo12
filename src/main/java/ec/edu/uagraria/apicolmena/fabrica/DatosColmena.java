package ec.edu.uagraria.apicolmena.fabrica;

import java.time.LocalDate;

public record DatosColmena(
        String ubicacion,
        double porcentajeVarroa,
        LocalDate fechaUltimaInspeccion,
        double rendimientoActualKg,
        double rendimientoAnteriorKg
) implements DatosProducto {
}
