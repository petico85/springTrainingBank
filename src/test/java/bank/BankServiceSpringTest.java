package bank;

import bank.backend.Address;
import bank.backend.BankService;
import bank.backend.Client;
import bank.backend.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

/*
* Így automatikusan lekéri a konfig osztály beanjeit függőségeit
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@Sql(statements = "delete from clients") //az @Sql antonáció minden teszteset előtt lefuttatja a megadott scriptet. Most épp kitisztítja az clientstáblát
@Sql(statements = "delete from logs")
public class BankServiceSpringTest {

    @Autowired
    private BankService bankService;//autómatikusan létrejön. Field injection csak tesztben

    @Test
    public void afterAddShouldList() {
        bankService.addClient("John Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClients();
        assertEquals(Arrays.asList("John Doe", "Jane Doe", "Jack Doe"),
                clients.stream().map(c -> c.getName()).collect(Collectors.toList())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        bankService.addClient("    ");
    }

    @Test
    public void saveWidthAddree() {
        bankService.addClient("Gipsz Jakab", new Address("Budapest","Fő út 30")
                , new Address("Csákvár","Vörösmarty utca 4")
                , new Address("Pécs","Király utca 30"));

        Client client = bankService.listClients().get(0);

        System.out.println(client.getAddresses().get(0).getCity());
    }
}
