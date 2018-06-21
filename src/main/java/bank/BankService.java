package bank;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLOutput;
import java.util.List;

@Service
public class BankService {


    //private BankDao bankDao = new BankDao();//túl erős kapcsolat korszerűtlen. Ez a kapcsolat nem laza a Low Coupeling nem érvényesül
                                            //Ezt nem lehet kicserélni másik BankDao-ra.
                                            //így nem unittestelhető, mert ha ezt junitban teszteljük, akkor nem egy osztályt teszteljü
                                           // hanem mindkét osztályt és azok kapcsolatát.
                                            //ha nem példányosítja maga:
                                            // megkapja konstruktorral: CONSTRUCTOR INJECTION
                                            // megkapja setterben: setter injection
                                            //Springben ...
    private BankDao bankDao;

    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() { //ez akkor is lefut ha nem hívja senki. jobb mint a konstruktor mert: a spring injektálás után fut (konstruktor és setter injection után), így nem null a bankdao
        System.out.println("Bankservice has benn created");
    }
    //postconstruct a konstructor és a wireing (összekapcsolások) lefutása után lefut.

    //constructor injection. Ha egy konstruktor van akkor a Spring azzal példányosít, ha több akkor @Autowired-del jelezni kell
    public BankService(BankDao bankDao, ApplicationEventPublisher applicationEventPublisher) {
        this.bankDao = bankDao;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void addClient(String name){
        Client client = new Client(name);
        bankDao.addClient(client);
        if(applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(new ClientHasCreatedEvent(this, name));//eldobjuk az eventet.
        }
    }

    public List<Client> listClients() {
        return bankDao.listClients();
    }
}
