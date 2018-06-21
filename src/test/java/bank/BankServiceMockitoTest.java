package bank;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

public class BankServiceMockitoTest {

    @Test
    public void testAddClient() {
        BankDao bankDao = Mockito.mock(BankDao.class);
        BankService bankService = new BankService(bankDao, null);

        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class); //mockitonak nagyon jó a dokumentációja, onna lehet meríteni

        //WHEN
        bankService.addClient("John Doe");

        //THEN
        Mockito.verify(bankDao).addClient(captor.capture()); //ellenőrizzük hogy a bankDao addclient metódusa hívódott e meg John Doe papaméterrel
        assertEquals("John Doe", captor.getValue().getName());
    }
}
