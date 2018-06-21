package bank;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


//itt megmondjuk neki hogy ezt a két osztályt szeretnénk majd bean-ként használni és az egymásra hivatkozás
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@EnableJpaRepositories //ez teszi lehetővé hogy a springdata megtalálja a jpa interface-einket
public class Config {


    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mariadb://localhost/bank", "bank", "bank");
    }

    //flyway inditása
    //a resources/db könyvtárban lévő V__ kezdetű sql fájlokat futtatja
    //ez egy default config felülbírálható, de alapból van valami, nem kell mindenáron konfigolni
    //schema_version táblát generálés abba logolja az sql futásokat
    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.migrate();
        return flyway;
    }


        //majd a Spring megoldja hogy az entity manager példányhoz hozzárendeli
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("bank");
        return entityManagerFactoryBean;
    }

    //ezt kelhet használni ha thirdparty osztályokat használunk amiket nem tudunk stereotype antonációkat megadni
    //most szükségtelen mert ezeket az ComponentScan egyébként is létrehozza az antonációik miatt
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
