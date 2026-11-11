package ec.edu.uagraria.apicolmena.fabrica;

import ec.edu.uagraria.apicolmena.dominio.Colmena;
import ec.edu.uagraria.apicolmena.dominio.Floracion;
import ec.edu.uagraria.apicolmena.dominio.InsumoApicola;
import ec.edu.uagraria.apicolmena.dominio.Trazable;

public final class FabricaProductos {

    private FabricaProductos() {
    }

    public static Object crear(TipoProducto tipo, DatosProducto datos) {

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "El tipo de producto es obligatorio"
            );
        }

        if (datos == null) {
            throw new IllegalArgumentException(
                    "Los datos del producto son obligatorios"
            );
        }

        return switch (tipo) {

            case FLORACION -> {
                if (!(datos instanceof DatosFloracion d)) {
                    throw new IllegalArgumentException(
                            "Los datos no corresponden a una floracion"
                    );
                }

                yield crearFloracion(
                        d.nombre(),
                        d.especieVegetal(),
                        d.fechaInicio(),
                        d.fechaFin(),
                        d.nivelNectar()
                );
            }

            case COLMENA -> {
                if (!(datos instanceof DatosColmena d)) {
                    throw new IllegalArgumentException(
                            "Los datos no corresponden a una colmena"
                    );
                }

                yield crearColmena(
                        Trazable.generarCodigo("COLMENA"),
                        d.ubicacion(),
                        d.porcentajeVarroa(),
                        d.fechaUltimaInspeccion(),
                        d.rendimientoActualKg(),
                        d.rendimientoAnteriorKg()
                );
            }

            case INSUMO_APICOLA -> {
                if (!(datos instanceof DatosInsumoApicola d)) {
                    throw new IllegalArgumentException(
                            "Los datos no corresponden a un insumo apicola"
                    );
                }

                yield crearInsumoApicola(
                        Trazable.generarCodigo("INSUMO"),
                        d.nombre(),
                        d.cantidad(),
                        d.unidad(),
                        d.precioUnitario()
                );
            }
        };
    }

    public static Floracion crearFloracion(
            String nombre,
            String especieVegetal,
            java.time.LocalDate fechaInicio,
            java.time.LocalDate fechaFin,
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
            java.time.LocalDate fechaUltimaInspeccion,
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