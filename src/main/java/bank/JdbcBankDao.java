package bank;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

//@Repository  //most ne ez legyen hanem a JPAs
public class JdbcBankDao implements BankDao {

    private JdbcTemplate jdbcTemplate;


    //a configból jön a datasource bean és ezzel létrehozunk egy jdbctemplate-et
    public JdbcBankDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addClient(Client client) {
        //ennyi egy jdbc utasítás
        jdbcTemplate.update("insert into clients(name, balance) values (?, ?)",
                client.getName(),
                client.getBalance());
    }

    @Override
    public List<Client> listClients() {
        //rs resultset, i int --> (rs, i) --> végigmegy ciklusként az rs datasettel és i az incrementum
        //szóval a ciklus minden futásakor csinál egy új klienst és belerakja a listába
        return jdbcTemplate.query("select id, name, balance from clients",
                (rs, i) ->
            new Client(rs.getLong("id"), rs.getString("name"), rs.getLong("balance"))
            );
    }
}
