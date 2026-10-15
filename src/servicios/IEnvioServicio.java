package servicios;

import modelos.DatosEnvio;
import modelos.TipoEnvio;

public interface IEnvioServicio {

    String[] getEncabezados();

    String[][] getDatos();

    void agregar(TipoEnvio tipo, DatosEnvio datos);

    boolean eliminar(String codigo);
}
