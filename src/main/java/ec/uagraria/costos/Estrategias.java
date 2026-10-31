package ec.uagraria.costos;

import java.util.function.Function;

public final class Estrategias {

    public static final double VALOR_JORNAL = 18.50;
    public static final double IVA = 0.15;

    private Estrategias() {
    }

    public static double costoDirecto(Lote lote) {
        return lote.insumos().stream()
                .mapToDouble(Insumo::costo)
                .sum();
    }

    public static double costoConManoDeObra(Lote lote) {
        double costoInsumos = costoDirecto(lote);

        double manoObra = lote.jornalesPorHectarea()
                * lote.hectareas()
                * VALOR_JORNAL;

        return costoInsumos + manoObra;
    }

    public static double costoPorHectarea(Lote lote) {
        return costoConManoDeObra(lote) / lote.hectareas();
    }

    public static double costoConSubsidioFertilizante(
            Lote lote, double porcentaje) {

        double costoUrea = lote.insumos().stream()
                .filter(i -> i.nombre().toLowerCase().contains("urea"))
                .mapToDouble(Insumo::costo)
                .sum();

        double subsidio = costoUrea * porcentaje;

        return costoConManoDeObra(lote) - subsidio;
    }

    public static double conIva(double base) {
        return base * (1 + IVA);
    }
}
