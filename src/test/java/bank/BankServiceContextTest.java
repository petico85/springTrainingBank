package bank;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;


//ez is spring fe nem így csináljuk
public class BankServiceContextTest {

    @Test
    public void afterAddShouldList() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);//ez a spring konténer, a doboz, a map
        BankService bankService = context.getBean(BankService.class);//kiszedjük a BankService bean-t. ez már tudja hogy ahhoz kell a BankDao, mert a configban ez is el van magyarázva
        bankService.addClient("John Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClients();
        assertEquals(Arrays.asList("John Doe", "Jane Doe", "Jack Doe"),
                clients.stream().map(c -> c.getName()).collect(Collectors.toList())
        );
    }
}
