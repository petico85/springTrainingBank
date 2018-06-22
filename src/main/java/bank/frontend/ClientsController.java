package bank.frontend;

import bank.backend.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class ClientsController {

    private BankService bankService;

    public ClientsController(BankService bankService) {//dependency injection
        this.bankService = bankService;
    }

    @ResponseBody
    @RequestMapping("/")
    public String listClients() {
        return bankService.listClients().stream().map(c -> c.getName()).collect(Collectors.joining(", "));
        //az összes ügyfél vesszővel konkatenálva
    }
}
