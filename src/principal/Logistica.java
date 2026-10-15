package principal;

import javax.swing.SwingUtilities;

import controladores.LogisticaControlador;
import fabricas.EnvioFabricaRegistro;
import servicios.EnvioServicio;
import vistas.LogisticaVista;

public class Logistica {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var vista = new LogisticaVista();
            var servicio = new EnvioServicio(new EnvioFabricaRegistro());
            new LogisticaControlador(vista, servicio);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}
