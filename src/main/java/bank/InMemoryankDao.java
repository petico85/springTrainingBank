package bank;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryankDao implements BankDao {

    private List<Client> clients =
     Collections.synchronizedList(new ArrayList<>());

    @Override
    public void addClient(Client client)
    {
        clients.add(client);
    }

    @Override
    public List<Client> listClients() {
        return new ArrayList<>(clients);
    }

}
