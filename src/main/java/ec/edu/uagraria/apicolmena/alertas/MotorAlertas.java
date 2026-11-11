package ec.edu.uagraria.apicolmena.alertas;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.reglas.ReglaAlerta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MotorAlertas {

    private final List<ReglaAlerta> reglas = new ArrayList<>();
    private final List<ObservadorAlerta> observadores = new ArrayList<>();

    public void agregarRegla(ReglaAlerta regla) {
        if (regla == null) {
            throw new IllegalArgumentException("La regla no puede ser nula");
        }
        reglas.add(regla);
    }

    public void agregarObservador(ObservadorAlerta observador) {
        if (observador == null) {
            throw new IllegalArgumentException("El observador no puede ser nulo");
        }

        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(ObservadorAlerta observador) {
        observadores.remove(observador);
    }

    public void eliminarRegla(ReglaAlerta regla) {
        reglas.remove(regla);
    }

    public List<Alerta> evaluarColmena(Colmena colmena, LocalDate fechaEvaluacion) {
        if (colmena == null) {
            throw new IllegalArgumentException("La colmena no puede ser nula");
        }

        if (fechaEvaluacion == null) {
            throw new IllegalArgumentException("La fecha de evaluacion no puede ser nula");
        }

        List<Alerta> alertasGeneradas = new ArrayList<>();

        for (ReglaAlerta regla : reglas) {
            if (regla.debeGenerarAlerta(colmena, fechaEvaluacion)) {

                Alerta alerta = new Alerta(
                        regla.obtenerTipoAlerta(),
                        colmena.codigo(),
                        regla.generarMensaje(colmena),
                        LocalDateTime.now()
                );

                alertasGeneradas.add(alerta);

                for (ObservadorAlerta observador : observadores) {
                    observador.actualizar(alerta);
                }
            }
        }

        return List.copyOf(alertasGeneradas);
    }
}