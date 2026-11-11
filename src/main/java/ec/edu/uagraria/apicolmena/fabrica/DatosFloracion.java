package ec.edu.uagraria.apicolmena.fabrica;

import java.time.LocalDate;

public record DatosFloracion(
        String nombre,
        String especieVegetal,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        double nivelNectar
) implements DatosProducto {
}

