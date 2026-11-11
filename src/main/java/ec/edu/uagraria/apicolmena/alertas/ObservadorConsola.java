package ec.edu.uagraria.apicolmena.alertas;

public class ObservadorConsola implements ObservadorAlerta {

    @Override
    public void actualizar(Alerta alerta) {
        if (alerta == null) {
            throw new IllegalArgumentException("La alerta no puede ser nula");
        }

        System.out.println("========== NUEVA ALERTA ==========");
        System.out.println("Tipo: " + alerta.tipo());
        System.out.println("Colmena: " + alerta.codigoColmena());
        System.out.println("Mensaje: " + alerta.mensaje());
        System.out.println("Fecha: " + alerta.fechaGeneracion());
        System.out.println("==================================");
    }
}