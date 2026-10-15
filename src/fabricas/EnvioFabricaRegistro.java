package fabricas;

import java.util.EnumMap;
import java.util.Map;

import modelos.TipoEnvio;


public class EnvioFabricaRegistro {

    private final Map<TipoEnvio, EnvioFabrica> fabricas = new EnumMap<>(TipoEnvio.class);

    public EnvioFabricaRegistro() {
        fabricas.put(TipoEnvio.TERRESTRE, new TerrestreFabrica());
        fabricas.put(TipoEnvio.AEREO, new AereoFabrica());
        fabricas.put(TipoEnvio.MARITIMO, new MaritimoFabrica());
    }

    public EnvioFabrica getFabrica(TipoEnvio tipo) {
        EnvioFabrica fabrica = fabricas.get(tipo);
        if (fabrica == null) {
            throw new IllegalArgumentException("Tipo de envío no soportado: " + tipo);
        }
        return fabrica;
    }
}
