package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReglaSinInspeccion implements ReglaAlerta {

    private static final long DIAS_MAXIMOS = 30;

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

        return diasTranscurridos > DIAS_MAXIMOS;
    }

    @Override
    public String obtenerTipoAlerta() {
        return "SIN_INSPECCION";
    }

    @Override
    public String generarMensaje(Colmena colmena) {
        return "Alerta: la colmena " + colmena.codigo()
                + " lleva mas de 30 dias sin inspeccion";
    }
}