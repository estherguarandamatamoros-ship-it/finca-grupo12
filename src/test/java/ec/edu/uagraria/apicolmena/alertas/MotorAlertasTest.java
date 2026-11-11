package ec.edu.uagraria.apicolmena.alertas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.reglas.ReglaAlerta;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorAlertasTest {

    @Test
    void debeGenerarAlertaYNotificarObservador() {

        MotorAlertas motor = new MotorAlertas();
        List<Alerta> recibidas = new ArrayList<>();

        motor.agregarRegla(new ReglaAlerta() {
            @Override
            public boolean debeGenerarAlerta(Colmena colmena, LocalDate fechaEvaluacion) {
                return true;
            }

            @Override
            public String obtenerTipoAlerta() {
                return "PRUEBA";
            }

            @Override
            public String generarMensaje(Colmena colmena) {
                return "Alerta de prueba";
            }
        });

        motor.agregarObservador(recibidas::add);

        Colmena colmena = new Colmena(
                "COL-001",
                "Sector norte",
                4.0,
                LocalDate.now(),
                20.0,
                25.0
        );

        List<Alerta> generadas = motor.evaluarColmena(
                colmena,
                LocalDate.now()
        );

        assertEquals(1, generadas.size());
        assertEquals(1, recibidas.size());
        assertEquals("PRUEBA", recibidas.get(0).tipo());
        assertEquals("COL-001", recibidas.get(0).codigoColmena());
    }

    @Test
    void noDebeNotificarCuandoNoSeGeneranAlertas() {

        MotorAlertas motor = new MotorAlertas();
        List<Alerta> recibidas = new ArrayList<>();

        motor.agregarRegla(new ReglaAlerta() {
            @Override
            public boolean debeGenerarAlerta(Colmena colmena, LocalDate fechaEvaluacion) {
                return false;
            }

            @Override
            public String obtenerTipoAlerta() {
                return "PRUEBA";
            }

            @Override
            public String generarMensaje(Colmena colmena) {
                return "Alerta de prueba";
            }
        });

        motor.agregarObservador(recibidas::add);

        Colmena colmena = new Colmena(
                "COL-002",
                "Sector sur",
                1.0,
                LocalDate.now(),
                30.0,
                30.0
        );

        List<Alerta> generadas = motor.evaluarColmena(
                colmena,
                LocalDate.now()
        );

        assertTrue(generadas.isEmpty());
        assertTrue(recibidas.isEmpty());
    }
    @Test
    void debeGenerarVariasAlertasParaUnaColmena() {

        MotorAlertas motor = new MotorAlertas();
        List<Alerta> recibidas = new ArrayList<>();

        ReglaAlerta regla1 = new ReglaAlerta() {
            @Override
            public boolean debeGenerarAlerta(Colmena colmena, LocalDate fechaEvaluacion) {
                return true;
            }

            @Override
            public String obtenerTipoAlerta() {
                return "VARROA_ALTA";
            }

            @Override
            public String generarMensaje(Colmena colmena) {
                return "Nivel de varroa elevado";
            }
        };

        ReglaAlerta regla2 = new ReglaAlerta() {
            @Override
            public boolean debeGenerarAlerta(Colmena colmena, LocalDate fechaEvaluacion) {
                return true;
            }

            @Override
            public String obtenerTipoAlerta() {
                return "CAIDA_RENDIMIENTO";
            }

            @Override
            public String generarMensaje(Colmena colmena) {
                return "Caida del rendimiento";
            }
        };

        motor.agregarRegla(regla1);
        motor.agregarRegla(regla2);
        motor.agregarObservador(recibidas::add);

        Colmena colmena = new Colmena(
                "COL-003",
                "Sector central",
                5.0,
                LocalDate.now(),
                15.0,
                25.0
        );

        List<Alerta> generadas = motor.evaluarColmena(
                colmena,
                LocalDate.now()
        );

        assertEquals(2, generadas.size());
        assertEquals(2, recibidas.size());
        assertEquals("VARROA_ALTA", generadas.get(0).tipo());
        assertEquals("CAIDA_RENDIMIENTO", generadas.get(1).tipo());
    }
    @Test
    void noDebeNotificarAUnObservadorEliminado() {

        MotorAlertas motor = new MotorAlertas();
        List<Alerta> recibidas = new ArrayList<>();

        ObservadorAlerta observador = recibidas::add;

        motor.agregarObservador(observador);
        motor.eliminarObservador(observador);

        motor.agregarRegla(new ReglaAlerta() {
            @Override
            public boolean debeGenerarAlerta(Colmena colmena, LocalDate fechaEvaluacion) {
                return true;
            }

            @Override
            public String obtenerTipoAlerta() {
                return "PRUEBA";
            }

            @Override
            public String generarMensaje(Colmena colmena) {
                return "Alerta de prueba";
            }
        });

        Colmena colmena = new Colmena(
                "COL-004",
                "Sector este",
                4.0,
                LocalDate.now(),
                20.0,
                25.0
        );

        List<Alerta> generadas = motor.evaluarColmena(
                colmena,
                LocalDate.now()
        );

        assertEquals(1, generadas.size());
        assertTrue(recibidas.isEmpty());
    }
    @Test
    void debeRechazarUnObservadorNulo() {

        MotorAlertas motor = new MotorAlertas();

        assertThrows(
                IllegalArgumentException.class,
                () -> motor.agregarObservador(null)
        );
    }
    @Test
    void debeRechazarUnaReglaNula() {

        MotorAlertas motor = new MotorAlertas();

        assertThrows(
                IllegalArgumentException.class,
                () -> motor.agregarRegla(null)
        );
    }
}