package bank.backend;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id //ezzzel megadjuk hogy a tábla id mezője az id változó legyen
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ezzel mondjuk hogy az adatbázis generálja az értékét
    private Long id;
    private String name;
    private long balance;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client") //ebból a springdata tudja, hogy össze van kötve az address táblával
    private List<Address> addresses = new ArrayList<>(); //az address osztályban is meg kell adni a kapcsolatot


    public void addAddress(Address address) {
        addresses.add(address);
        address.setClient(this);
    }


    //JPA miatt
    public  Client() {

    }

    public Client(Long id, String name, long balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> address) {
        this.addresses = addresses;
    }
}
