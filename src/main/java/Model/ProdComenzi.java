package Model;

public class ProdComenzi {
    int id;
    int idProdus;
    String numeProdus;
    int cantProdComanda;

    public ProdComenzi(){}

    public ProdComenzi(int id, int idProdus, String numeProdus, int cantProdComanda) {
        this.id = id;
        this.idProdus = idProdus;
        this.numeProdus = numeProdus;
        this.cantProdComanda = cantProdComanda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantProdComanda() {
        return cantProdComanda;
    }

    public void setCantProdComanda(int cantProdComanda) {
        this.cantProdComanda = cantProdComanda;
    }

    @Override
    public String toString() {
        return "ProdComenzi{" +
                "id=" + id +
                ", idProdus=" + idProdus +
                ", numeProdus='" + numeProdus + '\'' +
                ", cantProdComanda=" + cantProdComanda +
                '}';
    }
}
