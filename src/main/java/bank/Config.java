package bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


//itt megmondjuk neki hogy ezt a két osztályt szeretnénk majd bean-ként használni és az egymásra hivatkozás
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Config {

    //ezt kelhet használni ha thirdparty osztályokat használunk amiket nem tudunk stereotype antonációkat megadni
    // stereotype antonációk
    //  --@Service
    //  --@Repository
    //  --@Component
    //  --@Controller

/*    @Bean
    public BankDao bankDao() {
        return new InMemoryankDao();
    }

    @Bean
    public BankService bankService() {
        return new BankService(bankDao());
    }*/


}
