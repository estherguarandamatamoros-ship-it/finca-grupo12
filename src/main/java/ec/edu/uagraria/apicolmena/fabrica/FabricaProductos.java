package ec.edu.uagraria.apicolmena.fabrica;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.dominio.Floracion;
import ec.edu.uagraria.apicolmena.dominio.InsumoApicola;

import java.time.LocalDate;

public final class FabricaProductos {

    private FabricaProductos() {
    }

    public static Floracion crearFloracion(
            String nombre,
            String especieVegetal,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            double nivelNectar
    ) {
        return new Floracion(
                nombre,
                especieVegetal,
                fechaInicio,
                fechaFin,
                nivelNectar
        );
    }

    public static Colmena crearColmena(
            String codigo,
            String ubicacion,
            double porcentajeVarroa,
            LocalDate fechaUltimaInspeccion,
            double rendimientoActualKg,
            double rendimientoAnteriorKg
    ) {
        return new Colmena(
                codigo,
                ubicacion,
                porcentajeVarroa,
                fechaUltimaInspeccion,
                rendimientoActualKg,
                rendimientoAnteriorKg
        );
    }

    public static InsumoApicola crearInsumoApicola(
            String codigo,
            String nombre,
            double cantidad,
            String unidad,
            double precioUnitario
    ) {
        return new InsumoApicola(
                codigo,
                nombre,
                cantidad,
                unidad,
                precioUnitario
        );
    }
}