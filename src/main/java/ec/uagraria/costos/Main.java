package ec.uagraria.costos;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // Crear el lote de banano
        Lote lote = new Lote(
                "L-01",
                new Banano("Cavendish"),
                4.0,
                List.of(
                        new Insumo("Urea 50 kg", 12, "saco", 32.50),
                        new Insumo("Fungicida", 8, "litro", 21.75),
                        new Insumo("Semilla certificada", 1, "lote", 180.00)
                )
        );

        System.out.println("=== SISTEMA DE COSTOS AGRICOLAS ===");
        System.out.println("Cultivo: " + lote.cultivo().nombreComun());
        System.out.println("Hectareas: " + lote.hectareas());

        // Comprobar Optional vacío
        CalculadoraCostos sinEstrategia =
                new CalculadoraCostos(null);

        System.out.println("\n1. Sin estrategia:");
        System.out.println(sinEstrategia.calcular(lote));

        // Costo directo
        EstrategiaCosto directa =
                Estrategias::costoDirecto;

        // Costo con mano de obra
        EstrategiaCosto conManoObra =
                Estrategias::costoConManoDeObra;

        // Costo por hectárea
        EstrategiaCosto porHectarea =
                Estrategias::costoPorHectarea;

        // Costo con subsidio del 20% en urea
        EstrategiaCosto conSubsidio =
                l -> Estrategias.costoConSubsidioFertilizante(l, 0.20);

        // Costo con IVA
        EstrategiaCosto conIva =
                l -> Estrategias.conIva(
                        Estrategias.costoConManoDeObra(l)
                );

        // Calculadora con estrategia directa
        System.out.println("\n2. Costo directo:");
        mostrar(new CalculadoraCostos(directa), lote);

        System.out.println("\n3. Costo con mano de obra:");
        mostrar(new CalculadoraCostos(conManoObra), lote);

        System.out.println("\n4. Costo por hectarea:");
        mostrar(new CalculadoraCostos(porHectarea), lote);

        System.out.println("\n5. Costo con subsidio del 20%:");
        mostrar(new CalculadoraCostos(conSubsidio), lote);

        System.out.println("\n6. Costo con IVA del 15%:");
        mostrar(new CalculadoraCostos(conIva), lote);

        // Ejemplo de cacao orgánico
        Lote loteCacao = new Lote(
                "L-02",
                new Cacao("Nacional", true),
                2.5,
                List.of(
                        new Insumo("Urea", 5, "saco", 32.50),
                        new Insumo("Fertilizante organico", 30, "saco", 9.80)
                )
        );

        System.out.println("\n7. Ejemplo de cacao organico:");
        System.out.println("Jornales por hectarea: "
                + loteCacao.jornalesPorHectarea());

        System.out.println("Costo total: "
                + CalculadoraCostos.redondear(
                Estrategias.costoConManoDeObra(loteCacao)
        ));
    }

    private static void mostrar(
            CalculadoraCostos calculadora, Lote lote) {

        Optional<Double> resultado = calculadora.calcular(lote);

        resultado.ifPresentOrElse(
                valor -> System.out.printf("%.2f%n",
                        CalculadoraCostos.redondear(valor)),
                () -> System.out.println("No se pudo calcular")
        );
    }
}

