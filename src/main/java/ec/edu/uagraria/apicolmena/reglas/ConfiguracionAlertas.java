
package ec.edu.uagraria.apicolmena.reglas;

public record ConfiguracionAlertas(
        double umbralVarroa,
        long diasMaximosSinInspeccion,
        double umbralCaidaRendimiento
) {

    public static final ConfiguracionAlertas PREDETERMINADA =
            new ConfiguracionAlertas(3.0, 30, 20.0);

    public ConfiguracionAlertas {
        if (!Double.isFinite(umbralVarroa)
                || umbralVarroa < 0 || umbralVarroa > 100) {
            throw new IllegalArgumentException(
                    "El umbral de varroa debe estar entre 0 y 100"
            );
        }

        if (diasMaximosSinInspeccion < 0) {
            throw new IllegalArgumentException(
                    "Los dias maximos no pueden ser negativos"
            );
        }

        if (!Double.isFinite(umbralCaidaRendimiento)
                || umbralCaidaRendimiento < 0
                || umbralCaidaRendimiento > 100) {
            throw new IllegalArgumentException(
                    "El umbral de caida debe estar entre 0 y 100"
            );
        }
    }
}
