package bank;

public class Client {

    private String name;
    private Long id;
    private long balance;

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
