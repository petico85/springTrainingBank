package bank.frontend;

import bank.backend.BankService;
import bank.backend.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class ClientsController {

    private BankService bankService;

    public ClientsController(BankService bankService) {//dependency injection
        this.bankService = bankService;
    }

    /*
    //hagyományos mód
    @ResponseBody //nem view-ba megy hanem rögtön a böngészőbe
    @RequestMapping("/")
    public String listClients() {
        return bankService.listClients().stream().map(c -> c.getName()).collect(Collectors.joining(", "));
        //az összes ügyfél vesszővel konkatenálva
    }
    */


    //ez a kontroller. lekéri az adatbázist a repositoryból, majd továbbítja a view-bnak
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listClients() {
        //View
        ModelAndView modelAndView = new ModelAndView("clients");//thymeleaf template neve
        //Model: List<Client>
        modelAndView.addObject("clients", bankService.listClients());//itt adjuk meg a clients nevű listát amit majd a thymeleaf template-en ciklusban végig tudunk nézni

        modelAndView.addObject("clientForm", new Client()); ///ez feltölti üres elemekkel a formot, kell a modelhez
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addClient(@ModelAttribute  Client client) {
        bankService.addClient(client);
        return "redirect:/";
    }


}
