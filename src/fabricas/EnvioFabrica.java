package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;

public abstract class EnvioFabrica {

    public abstract Envio crearEnvio(DatosEnvio datos);
}
