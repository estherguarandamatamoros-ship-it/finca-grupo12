package Semana2.finca;

public record Animal(
        String codigo,
        String especie,
        double pesoKg,
        double precioPorKg
) implements Activo {

    public Animal {
        if (codigo == null || codigo.isBlank()
                || especie == null || especie.isBlank()) {
            throw new IllegalArgumentException(
                    "El código y la especie son obligatorios");
        }

        if (!Double.isFinite(pesoKg)
                || !Double.isFinite(precioPorKg)
                || pesoKg <= 0 || precioPorKg < 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser positivo y el precio no negativo");
        }
    }
}

