package ec.edu.uagraria.apicolmena.alertas;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ObservadorArchivo implements ObservadorAlerta {

    private final Path rutaArchivo;

    public ObservadorArchivo() {
        this(Path.of("alertas.log"));
    }

    public ObservadorArchivo(Path rutaArchivo) {
        if (rutaArchivo == null) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser nula");
        }
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void actualizar(Alerta alerta) {
        if (alerta == null) {
            throw new IllegalArgumentException("La alerta no puede ser nula");
        }

        String registro = String.format(
                "[%s] Tipo: %s | Colmena: %s | Mensaje: %s%n",
                alerta.fechaGeneracion(),
                alerta.tipo(),
                alerta.codigoColmena(),
                alerta.mensaje()
        );

        try {
            Files.writeString(
                    rutaArchivo,
                    registro,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo guardar la alerta en el archivo", e);
        }
    }
}