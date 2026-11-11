package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;

public class ReglaCaidaRendimiento implements ReglaAlerta {

    private final ConfiguracionAlertas configuracion;

    public ReglaCaidaRendimiento() {
        this(ConfiguracionAlertas.PREDETERMINADA);
    }

    public ReglaCaidaRendimiento(
            ConfiguracionAlertas configuracion
    ) {
        if (configuracion == null) {
            throw new IllegalArgumentException(
                    "La configuracion no puede ser nula"
            );
        }

        this.configuracion = configuracion;
    }

    @Override
    public boolean debeGenerarAlerta(
            Colmena colmena,
            LocalDate fechaEvaluacion
    ) {
        if (colmena == null) {
            throw new IllegalArgumentException(
                    "La colmena es obligatoria"
            );
        }

        double rendimientoAnterior = colmena.rendimientoAnteriorKg();

        if (rendimientoAnterior <= 0) {
            return false;
        }

        double caida = (
                (rendimientoAnterior - colmena.rendimientoActualKg())
                        / rendimientoAnterior
        ) * 100;

        return caida > configuracion.umbralCaidaRendimiento();
    }

    @Override
    public String obtenerTipoAlerta() {
        return "CAIDA_RENDIMIENTO";
    }

    @Override
    public String generarMensaje(Colmena colmena) {
        return "Alerta: la colmena " + colmena.codigo()
                + " presenta una caida de rendimiento. Anterior: "
                + colmena.rendimientoAnteriorKg()
                + " kg; actual: "
                + colmena.rendimientoActualKg() + " kg";
    }
}
