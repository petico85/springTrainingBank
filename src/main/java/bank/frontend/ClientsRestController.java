package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientsRestController {

    private BankService bankService;

    public ClientsRestController(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping("/clients")
    public List<Client> listClients() {//jsonbe dobja vissza mert ez a default
        return bankService.listClients();
    }

    @RequestMapping("/clients/{id}")
    public Client getClient(@PathVariable long id) {
        return  bankService.listClients().stream().filter(c -> c.getId() == id).findFirst().get();
        //ez egy csúnya megoldás hogy lekérünk minden adatot és utólag filterezzük ki, de most nem írtuk és vezettük végig a getClient by Id metódust
    }
}
