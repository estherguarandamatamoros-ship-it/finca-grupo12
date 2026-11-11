
package ec.edu.uagraria.apicolmena.fabrica;

import ec.edu.uagraria.apicolmena.alertas.BitacoraAlertas;
import ec.edu.uagraria.apicolmena.alertas.ObservadorAlerta;
import ec.edu.uagraria.apicolmena.alertas.ObservadorArchivo;
import ec.edu.uagraria.apicolmena.alertas.ObservadorConsola;

import java.nio.file.Path;

public final class FabricaObservadores {

    private FabricaObservadores() {
    }

    public static ObservadorAlerta crearConsola() {
        return new ObservadorConsola();
    }

    public static ObservadorAlerta crearArchivo(Path ruta) {
        return new ObservadorArchivo(ruta);
    }

    public static BitacoraAlertas crearBitacora() {
        return new BitacoraAlertas();
    }
}

