package ec.edu.uagraria.apicolmena.alertas;

import java.time.LocalDateTime;

public record Alerta(
        String tipo,
        String codigoColmena,
        String mensaje,
        LocalDateTime fechaGeneracion
) {
    public Alerta {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de alerta es obligatorio");
        }

        if (codigoColmena == null || codigoColmena.isBlank()) {
            throw new IllegalArgumentException("El codigo de colmena es obligatorio");
        }

        if (mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("El mensaje es obligatorio");
        }

        if (fechaGeneracion == null) {
            throw new IllegalArgumentException("La fecha de generacion es obligatoria");
        }
    }
}