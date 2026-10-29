package Semana2.finca;

public class Valorador {

    public static double valor(Activo activo) {
        return switch (activo) {
            case Producto p ->
                    p.precioUnitario() * p.unidades();

            case Cultivo c ->
                    c.hectareas() * c.costoPorHectarea();

            case Animal a ->
                    a.pesoKg() * a.precioPorKg();
        };
    }
}

