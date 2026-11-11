package ec.edu.uagraria.apicolmena.alertas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ObservadoresAlertaTest {

    @TempDir
    Path directorioTemporal;

    private Alerta crearAlerta() {
        return new Alerta(
                "VARROA_ALTA",
                "COL-001",
                "El porcentaje de varroa supero el limite permitido",
                LocalDateTime.of(2026, 11, 10, 19, 30)
        );
    }

    @Test
    void observadorConsolaDebeMostrarLaAlerta() {
        ObservadorConsola observador = new ObservadorConsola();
        Alerta alerta = crearAlerta();

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;

        try {
            System.setOut(new PrintStream(salida, true, StandardCharsets.UTF_8));
            observador.actualizar(alerta);
        } finally {
            System.setOut(salidaOriginal);
        }

        String resultado = salida.toString(StandardCharsets.UTF_8);

        assertTrue(resultado.contains("VARROA_ALTA"));
        assertTrue(resultado.contains("COL-001"));
        assertTrue(resultado.contains("supero el limite permitido"));
    }

    @Test
    void observadorArchivoDebeCrearYGuardarLaAlerta() throws Exception {
        Path archivo = directorioTemporal.resolve("alertas.log");
        ObservadorArchivo observador = new ObservadorArchivo(archivo);

        observador.actualizar(crearAlerta());

        assertTrue(Files.exists(archivo));

        String contenido = Files.readString(archivo, StandardCharsets.UTF_8);

        assertTrue(contenido.contains("VARROA_ALTA"));
        assertTrue(contenido.contains("COL-001"));
    }

    @Test
    void observadorArchivoDebeAgregarAlertasSinBorrarLasAnteriores() throws Exception {
        Path archivo = directorioTemporal.resolve("alertas.log");
        ObservadorArchivo observador = new ObservadorArchivo(archivo);

        observador.actualizar(crearAlerta());

        Alerta segundaAlerta = new Alerta(
                "SIN_INSPECCION",
                "COL-002",
                "La colmena necesita una nueva inspeccion",
                LocalDateTime.of(2026, 11, 10, 20, 0)
        );

        observador.actualizar(segundaAlerta);

        String contenido = Files.readString(archivo, StandardCharsets.UTF_8);

        assertTrue(contenido.contains("VARROA_ALTA"));
        assertTrue(contenido.contains("SIN_INSPECCION"));
        assertTrue(contenido.contains("COL-001"));
        assertTrue(contenido.contains("COL-002"));
    }

    @Test
    void observadorConsolaDebeRechazarAlertaNula() {
        ObservadorConsola observador = new ObservadorConsola();

        assertThrows(
                IllegalArgumentException.class,
                () -> observador.actualizar(null)
        );
    }

    @Test
    void observadorArchivoDebeRechazarAlertaNula() {
        Path archivo = directorioTemporal.resolve("alertas.log");
        ObservadorArchivo observador = new ObservadorArchivo(archivo);

        assertThrows(
                IllegalArgumentException.class,
                () -> observador.actualizar(null)
        );
    }
}