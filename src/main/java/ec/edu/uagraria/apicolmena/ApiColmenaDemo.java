package ec.edu.uagraria.apicolmena;

import ec.edu.uagraria.apicolmena.alertas.BitacoraAlertas;
import ec.edu.uagraria.apicolmena.alertas.MotorAlertas;
import ec.edu.uagraria.apicolmena.alertas.ObservadorAlerta;
import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.fabrica.FabricaObservadores;
import ec.edu.uagraria.apicolmena.fabrica.FabricaProductos;
import ec.edu.uagraria.apicolmena.fabrica.DatosColmena;
import ec.edu.uagraria.apicolmena.fabrica.TipoProducto;
import ec.edu.uagraria.apicolmena.reglas.ReglaCaidaRendimiento;
import ec.edu.uagraria.apicolmena.reglas.ReglaSinInspeccion;
import ec.edu.uagraria.apicolmena.reglas.ReglaVarroaAlta;

import java.nio.file.Path;
import java.time.LocalDate;

public class ApiColmenaDemo {

    public static void main(String[] args) {

        Colmena colmena = (Colmena) FabricaProductos.crear(
                TipoProducto.COLMENA,
                new DatosColmena(
                        "Apiario central",
                        5.0,
                        LocalDate.now().minusDays(40),
                        80.0,
                        100.0
                )
        );

        MotorAlertas motor = new MotorAlertas();

        motor.agregarRegla(new ReglaVarroaAlta());
        motor.agregarRegla(new ReglaSinInspeccion());
        motor.agregarRegla(new ReglaCaidaRendimiento());

        ObservadorAlerta consola =
                FabricaObservadores.crearConsola();

        ObservadorAlerta archivo =
                FabricaObservadores.crearArchivo(Path.of("alertas.log"));

        BitacoraAlertas bitacora =
                FabricaObservadores.crearBitacora();

        motor.suscribir(consola);
        motor.suscribir(archivo);
        motor.suscribir(bitacora);

        motor.evaluarColmena(colmena, LocalDate.now());

        System.out.println("Total de alertas registradas: "
                + bitacora.obtenerAlertas().size());
    }
}

