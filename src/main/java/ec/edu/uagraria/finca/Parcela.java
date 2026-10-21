package ec.edu.uagraria.finca;

/** Clase mínima del Taller 1: sirve para probar el entorno. */
public class Parcela {

    private static final double METROS_CUADRADOS_POR_HECTAREA = 10_000;

    private final String codigo;
    private final double hectareas;

    public Parcela(String codigo, double hectareas) {
        if (hectareas <= 0) {
            throw new IllegalArgumentException(
                    "Las hectáreas deben ser mayores que cero");
        }
        this.codigo = codigo;
        this.hectareas = hectareas;
    }

    public String getCodigo() { return codigo; }

    public double areaEnMetrosCuadrados() {
        return hectareas * METROS_CUADRADOS_POR_HECTAREA;
    }
}
