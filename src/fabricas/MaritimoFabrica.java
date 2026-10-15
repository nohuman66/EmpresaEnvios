package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Maritimo;

public class MaritimoFabrica extends EnvioFabrica {
    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Maritimo(datos.codigo(), datos.cliente(), datos.peso(), datos.distancia());
    }
}
