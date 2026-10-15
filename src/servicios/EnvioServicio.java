package servicios;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fabricas.EnvioFabricaRegistro;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio implements IEnvioServicio {

    private static final String[] ENCABEZADOS = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};

    private final List<Envio> envios = new ArrayList<>();
    private final EnvioFabricaRegistro fabricas;

    public EnvioServicio(EnvioFabricaRegistro fabricas) {
        this.fabricas = fabricas;
    }

    @Override
    public String[] getEncabezados() {
        return ENCABEZADOS;
    }

    @Override
    public String[][] getDatos() {
        return envios.stream()
                .map(this::aFila)
                .toArray(String[][]::new);
    }

    @Override
    public void agregar(TipoEnvio tipo, DatosEnvio datos) {
        validar(datos);
        if (existe(datos.codigo())) {
            throw new IllegalArgumentException("Ya existe un envío con ese código");
        }

        envios.add(fabricas.getFabrica(tipo).crearEnvio(datos));
    }

    @Override
    public boolean eliminar(String codigo) {
        return envios.removeIf(envio -> envio.getCodigo().equals(codigo));
    }

    private boolean existe(String codigo) {
        return envios.stream().anyMatch(envio -> envio.getCodigo().equals(codigo));
    }

    private void validar(DatosEnvio datos) {
        boolean validos = datos.codigo() != null && !datos.codigo().isBlank()
                && datos.cliente() != null && !datos.cliente().isBlank()
                && datos.peso() > 0
                && datos.distancia() > 0;
        if (!validos) {
            throw new IllegalArgumentException("No se puede agregar el envío. Verifique que todos los datos "
                    + "estén completos y que el peso y la distancia sean mayores que cero");
        }
    }

    private String[] aFila(Envio envio) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[]{
                envio.getTipo().toString(),
                envio.getCodigo(),
                envio.getCliente(),
                df.format(envio.getPeso()),
                df.format(envio.getDistancia()),
                "$ " + df.format(envio.calcularTarifa())
        };
    }
}
