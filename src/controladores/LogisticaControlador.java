package controladores;

import modelos.DatosEnvio;
import servicios.IEnvioServicio;
import vistas.LogisticaVista;

public class LogisticaControlador {

    private final LogisticaVista vista;
    private final IEnvioServicio servicio;

    public LogisticaControlador(LogisticaVista vista, IEnvioServicio servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.vista.setGuardarEnvioClick(evento -> guardarEnvio());
        this.vista.setRetirarEnvioClick(evento -> retirarEnvio());
        mostrarEnvios();
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(servicio.getDatos(), servicio.getEncabezados());
    }

    private void guardarEnvio() {
        var datos = new DatosEnvio(vista.getCodigo(), vista.getCliente(),
                vista.getPeso(), vista.getDistancia());
        try {
            servicio.agregar(vista.getTipoSeleccionado(), datos);
            mostrarEnvios();
            vista.limpiarFormulario();
            vista.ocultarEdicionEnvio();
        } catch (IllegalArgumentException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    private void retirarEnvio() {
        var codigo = vista.solicitarCodigo();
        if (codigo == null) {
            return;
        }
        if (servicio.eliminar(codigo)) {
            mostrarEnvios();
        } else {
            vista.mostrarMensaje("El código " + codigo + " no existe");
        }
    }
}
