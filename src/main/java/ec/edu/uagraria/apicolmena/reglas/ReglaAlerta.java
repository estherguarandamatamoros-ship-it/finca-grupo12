package ec.edu.uagraria.apicolmena.reglas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;

import java.time.LocalDate;

public interface ReglaAlerta {

    boolean debeGenerarAlerta(
            Colmena colmena,
            LocalDate fechaEvaluacion
    );

    String obtenerTipoAlerta();

    String generarMensaje(Colmena colmena);
}