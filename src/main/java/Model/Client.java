package Model;

import java.util.Objects;

public class Client {
    /**
     * Aceasta clasa este folosita pentru reprezentarea clientului.
     */

    private int id;
    private int varsta;
    private String nrTelefon = new String();
    private String nume = new String();
    private String prenume = new String();
    private String email = new String();
    private String adresa = new String();

    public Client(){};

    /**
     * Date genereale despre client
     * @param id = id-ul clientului
     * @param name = numele clientului
     * @param prenume = prenumele clientului
     * @param age = varsta clientului
     * @param email = adresa de email a clientului
     * @param adress = adresa fizica  a clientului
     * @param tel = numarul de telefon al clientului
     */
    public Client(int id, String name, String prenume, int age, String email, String adress, String tel) {
        this.id = id;
        this.varsta = age;
        this.nrTelefon = tel;
        this.nume = name;
        this.prenume = prenume;
        this.email = email;
        this.adresa = adress;
    }

    /**
     * Aceasta metoda returneaza id-ul clientului
     * @return id int
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString(){
        return "Client[id="+this.id+", Nume="+this.nume+", prenume="+this.prenume+", varsta="+this.varsta+
                ", email="+this.email+", adresa="+this.adresa+", telefon="+this.nrTelefon+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId() == client.getId() && getVarsta() == client.getVarsta() && getNrTelefon().equals(client.getNrTelefon()) && getNume().equals(client.getNume()) && getPrenume().equals(client.getPrenume()) && getEmail().equals(client.getEmail()) && getAdresa().equals(client.getAdresa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVarsta(), getNrTelefon(), getNume(), getPrenume(), getEmail(), getAdresa());
    }
}