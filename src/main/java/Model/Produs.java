package Model;

import java.util.Objects;

public class Produs {
    private int id;
    private String numeProdus;
    private int pretProdus;
    private int cantitate;

    public Produs(){}

    public Produs(int id, String nume ,int price, int cant){
        this.id=id;
        this.numeProdus=nume;
        this.pretProdus=price;
        this.cantitate=cant;
    }

    @Override
    public String toString(){
        return "Produs[id="+this.id+", nume="+this.numeProdus+", pret="+this.pretProdus+", cantitate="+this.cantitate+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs)) return false;
        Produs produs = (Produs) o;
        return getId() == produs.getId() && getPretProdus() == produs.getPretProdus() && getCantitate() == produs.getCantitate() && getNumeProdus().equals(produs.getNumeProdus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumeProdus(), getPretProdus(), getCantitate());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(int pretProdus) {
        this.pretProdus = pretProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
