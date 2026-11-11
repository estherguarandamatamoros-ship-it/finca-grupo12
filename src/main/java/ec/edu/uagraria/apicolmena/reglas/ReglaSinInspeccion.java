
package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReglaSinInspeccion implements ReglaAlerta {

    private final ConfiguracionAlertas configuracion;

    public ReglaSinInspeccion() {
        this(ConfiguracionAlertas.PREDETERMINADA);
    }

    public ReglaSinInspeccion(ConfiguracionAlertas configuracion) {
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
        if (colmena == null || fechaEvaluacion == null) {
            throw new IllegalArgumentException(
                    "La colmena y la fecha de evaluacion son obligatorias"
            );
        }

        long diasTranscurridos = ChronoUnit.DAYS.between(
                colmena.fechaUltimaInspeccion(),
                fechaEvaluacion
        );

        return diasTranscurridos
                > configuracion.diasMaximosSinInspeccion();
    }

    @Override
    public String obtenerTipoAlerta() {
        return "SIN_INSPECCION";
    }

    @Override
    public String generarMensaje(Colmena colmena) {
        return "Alerta: la colmena " + colmena.codigo()
                + " lleva mas de "
                + configuracion.diasMaximosSinInspeccion()
                + " dias sin inspeccion";
    }
}
