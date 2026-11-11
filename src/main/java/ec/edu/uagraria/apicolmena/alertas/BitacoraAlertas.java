
package ec.edu.uagraria.apicolmena.alertas;

import java.util.ArrayList;
import java.util.List;

public class BitacoraAlertas implements ObservadorAlerta {

    private final List<Alerta> alertas = new ArrayList<>();

    @Override
    public void actualizar(Alerta alerta) {
        if (alerta == null) {
            throw new IllegalArgumentException("La alerta no puede ser nula");
        }

        alertas.add(alerta);
    }

    public List<Alerta> obtenerAlertas() {
        return List.copyOf(alertas);
    }

    public void limpiar() {
        alertas.clear();
    }
}
