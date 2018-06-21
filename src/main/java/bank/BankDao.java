package bank;

import java.util.List;


//springebn interface-ben interfészre csak akkor van szükség, ha több implementációt akarunk létrehozni
public interface BankDao {
    void addClient(Client client);

    List<Client> listClients();
}
