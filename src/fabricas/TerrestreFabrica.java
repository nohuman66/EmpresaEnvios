package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Terrestre;

public class TerrestreFabrica extends EnvioFabrica {
    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Terrestre(datos.codigo(), datos.cliente(), datos.peso(), datos.distancia());
    }
}
