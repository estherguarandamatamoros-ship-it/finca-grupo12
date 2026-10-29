package Semana2.finca;

import java.util.Objects;

public record Animal(
        String codigo,
        String especie,
        double pesoKg,
        double precioPorKg
) implements Activo {

    public Animal {
        Objects.requireNonNull(codigo, "El código es obligatorio");
        Objects.requireNonNull(especie, "La especie es obligatoria");

        if (codigo.isBlank() || especie.isBlank()) {
            throw new IllegalArgumentException(
                    "El código y la especie no pueden estar vacíos"
            );
        }

        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser positivo"
            );
        }

        if (!Double.isFinite(precioPorKg)
                || precioPorKg < 0) {
            throw new IllegalArgumentException(
                    "El precio por kilogramo no puede ser negativo"
            );
        }
    }
}
