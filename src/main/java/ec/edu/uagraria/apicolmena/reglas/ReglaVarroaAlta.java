package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;

public class ReglaVarroaAlta implements ReglaAlerta {

    private static final double UMBRAL_VARROA = 3.0;

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

        return colmena.porcentajeVarroa() >= UMBRAL_VARROA;
    }

    @Override
    public String obtenerTipoAlerta() {
        return "VARROA_ALTA";
    }

    @Override
    public String generarMensaje(Colmena colmena) {
        return "Alerta: la colmena " + colmena.codigo()
                + " presenta un nivel de varroa de "
                + colmena.porcentajeVarroa() + "%";
    }
}