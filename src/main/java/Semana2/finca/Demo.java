package Semana2.finca;

import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<Activo> activos = List.of(
                new Producto(
                        "PR-001", "Saco de urea 50 kg",
                        32.50, 40),

                new Cultivo(
                        "CU-001", "Cacao CCN-51",
                        3.5, 1800.0),

                new Animal(
                        "AN-001", "Bovino",
                        420.0, 1.85),

                new Cultivo(
                        "CU-002", "Arroz de Daule",
                        2.0, 1500.0)
        );

        double total = 0;

        for (Activo activo : activos) {
            double valor = Valorador.valor(activo);
            total += valor;

            System.out.printf(
                    "%s -> USD %.2f%n", activo, valor);
        }

        System.out.printf(
                "Valor total de la finca: USD %.2f%n", total);

        System.out.printf(
                "10 qq = %.1f kg%n",
                UnidadMedida.QUINTAL.aKilos(10));
    }
}

