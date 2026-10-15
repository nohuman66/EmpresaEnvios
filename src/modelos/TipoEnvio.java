package modelos;

public enum TipoEnvio {
    TERRESTRE("Terrestre"),
    AEREO("Aéreo"),
    MARITIMO("Marítimo");

    private final String etiqueta;

    TipoEnvio(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
