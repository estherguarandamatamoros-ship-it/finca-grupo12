package ec.edu.uagraria.apicolmena.dominio;

import java.util.UUID;

public interface Trazable {

    static String generarCodigo(String prefijo) {
        if (prefijo == null || prefijo.isBlank()) {
            throw new IllegalArgumentException(
                    "El prefijo del codigo es obligatorio"
            );
        }

        return prefijo.toUpperCase()
                + "-"
                + UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();
    }
}
