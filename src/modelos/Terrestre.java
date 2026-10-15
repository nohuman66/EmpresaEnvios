package modelos;

public class Terrestre extends Envio {

    private static final double TARIFA_POR_KM = 1500;
    private static final double RECARGO_POR_KG = 2000;

    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public TipoEnvio getTipo() {
        return TipoEnvio.TERRESTRE;
    }

    @Override
    public double calcularTarifa() {
        return calcular(TARIFA_POR_KM, RECARGO_POR_KG);
    }
}
