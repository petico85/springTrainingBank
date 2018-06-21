package bank;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class BankServiceIntegrationTest {

    @Test
    public void afterAddShouldList(){
        BankDao bankDao = new InMemoryankDao();
        BankService bankService = new BankService(bankDao, null);
        bankService.addClient("John Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClients();
        assertEquals(Arrays.asList("John Doe", "Jane Doe", "Jack Doe"),
                clients.stream().map(c -> c.getName()).collect(Collectors.toList())
        );
    }
}
