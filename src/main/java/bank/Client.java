package bank;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id //ezzzel megadjuk hogy a tábla id mezője az id változó legyen
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ezzel mondjuk hogy az adatbázis generálja az értékét
    private Long id;
    private String name;
    private long balance;

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
}
