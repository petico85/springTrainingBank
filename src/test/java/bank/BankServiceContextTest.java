package bank;

import bank.backend.BankService;
import bank.backend.Client;
import bank.backend.Config;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;


//ez is spring fe nem így csináljuk
public class BankServiceContextTest {

    @Test
    public void afterAddShouldList() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);//ez a spring konténer, a doboz, a map

        //kiürítjük a táblát a teszt előtt
        DataSource dataSource = context.getBean(DataSource.class);
        JdbcTemplate jbdcTemplate = new JdbcTemplate(dataSource);
        jbdcTemplate.execute("delete from clients");


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
