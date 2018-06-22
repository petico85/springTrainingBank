package bank;

import bank.backend.BankDao;
import bank.backend.BankService;
import bank.backend.Client;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BankServiceTest {


    @Test
    public void testAddClient() {

        //Given
        BankDaoStub bankDao = new BankDaoStub();
        BankService bankService = new BankService(bankDao, null);

        //When
        bankService.addClient("John Doe");

        //Then
        assertEquals("John Doe", bankDao.getClient().getName());


    }


    //BankDao-t nem teszteljük. Hanem csak azt lenneőrizzük, hogy meghívja e a BankDaot-t
    //BankDaot ki kell cserélni egy kamu objektumra amivel csak azt ellenőrizzük a hívás megtörténik e
    //ehhez a bankdaoból interface-t csinálunk és itt implementálunk egy verziót (Stub)
    //rakunk bele egy gettert a megfelelő objektumra, hogy ellenőrizni tudjuk
    public static class BankDaoStub implements BankDao {

        private Client client;

        @Override
        public void addClient(Client client) {
            this.client = client;
        }

        @Override
        public List<Client> listClients() {
            return null;
        }

        public Client getClient() {
            return client;
        }


    }
}
