package models;

public class Pais {
    private String nombre;
    private String moneda;
    private double conversion;
    public Pais(String nombre, String moneda, double conversion){
        this.nombre = nombre;
        this.moneda = moneda;
        this.conversion = conversion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getConversion() {
        return conversion;
    }
}
