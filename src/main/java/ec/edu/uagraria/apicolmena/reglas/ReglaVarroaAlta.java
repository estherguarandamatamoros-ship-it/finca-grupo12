package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;

public class ReglaVarroaAlta implements ReglaAlerta {

    private final ConfiguracionAlertas configuracion;

    public ReglaVarroaAlta() {
        this(ConfiguracionAlertas.PREDETERMINADA);
    }

    public ReglaVarroaAlta(ConfiguracionAlertas configuracion) {
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

        return colmena.porcentajeVarroa()
                > configuracion.umbralVarroa();
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
