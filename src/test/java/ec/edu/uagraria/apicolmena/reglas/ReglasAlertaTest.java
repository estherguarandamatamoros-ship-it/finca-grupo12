package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReglasAlertaTest {

    private final LocalDate fechaEvaluacion =
            LocalDate.of(2026, 11, 10);

    @Test
    void varroaMenorAlUmbralNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.9, fechaEvaluacion.minusDays(5), 10, 12
        );

        ReglaVarroaAlta regla = new ReglaVarroaAlta();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    @Test
    void varroaMayorAlUmbralGeneraAlerta() {
        Colmena colmena = crearColmena(
                3.1, fechaEvaluacion.minusDays(5), 10, 12
        );

        ReglaVarroaAlta regla = new ReglaVarroaAlta();

        assertTrue(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
        assertEquals("VARROA_ALTA", regla.obtenerTipoAlerta());
        assertTrue(regla.generarMensaje(colmena).contains("COL-001"));
    }

    @Test
    void varroaIgualAlUmbralNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                3.0, fechaEvaluacion.minusDays(5), 10, 12
        );

        ReglaVarroaAlta regla = new ReglaVarroaAlta();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    @Test
    void treintaDiasSinInspeccionNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(30), 10, 12
        );

        ReglaSinInspeccion regla = new ReglaSinInspeccion();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    @Test
    void treintaYUnDiasSinInspeccionGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(31), 10, 12
        );

        ReglaSinInspeccion regla = new ReglaSinInspeccion();

        assertTrue(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
        assertEquals("SIN_INSPECCION", regla.obtenerTipoAlerta());
    }

    @Test
    void caidaExactamenteDelVeintePorCientoNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(5), 8, 10
        );

        ReglaCaidaRendimiento regla = new ReglaCaidaRendimiento();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    @Test
    void caidaMayorAlVeintePorCientoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(5), 7.9, 10
        );

        ReglaCaidaRendimiento regla = new ReglaCaidaRendimiento();

        assertTrue(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
        assertEquals("CAIDA_RENDIMIENTO", regla.obtenerTipoAlerta());
    }

    @Test
    void caidaMenorAlVeintePorCientoNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(5), 8.5, 10
        );

        ReglaCaidaRendimiento regla = new ReglaCaidaRendimiento();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    @Test
    void rendimientoAnteriorCeroNoGeneraAlerta() {
        Colmena colmena = crearColmena(
                2.0, fechaEvaluacion.minusDays(5), 0, 0
        );

        ReglaCaidaRendimiento regla = new ReglaCaidaRendimiento();

        assertFalse(regla.debeGenerarAlerta(colmena, fechaEvaluacion));
    }

    private Colmena crearColmena(
            double varroa,
            LocalDate ultimaInspeccion,
            double rendimientoActual,
            double rendimientoAnterior
    ) {
        return new Colmena(
                "COL-001",
                "Sector norte",
                varroa,
                ultimaInspeccion,
                rendimientoActual,
                rendimientoAnterior
        );
    }
}