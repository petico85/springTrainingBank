package bank.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
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

    private JpaLogEntryDao logEntryDao;

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

    @Transactional
    public void addClient(String name, Address... addresses) {
        Client client = new Client(name);
        Arrays.stream(addresses).forEach(a -> client.addAddress(a));//hozzáadja az összes címet a klienshez
        bankDao.addClient(client);
    }

    @Transactional
    public void addClient(String name){
        Client client = new Client(name);
        bankDao.addClient(client);
        if(applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(new ClientHasCreatedEvent(this, name));//eldobjuk az eventet.
        }

        if(logEntryDao != null) {
            logEntryDao.log("client has created: " + name );
        }

        if(name.trim().equals("")) {
           throw new IllegalArgumentException("Name cannot be empty"); //ezt az elején kellene, de példa kedvéért
        }
    }

    public List<Client> listClients() {
        return bankDao.listClients();
    }

    @Transactional
    public void addClient(Client client) {
        bankDao.addClient(client);
    }

    @Autowired
    public void setLogEntryDao(JpaLogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }
}
