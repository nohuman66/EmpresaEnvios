package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Aereo;

public class AereoFabrica extends EnvioFabrica {
    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Aereo(datos.codigo(), datos.cliente(), datos.peso(), datos.distancia());
    }
}
