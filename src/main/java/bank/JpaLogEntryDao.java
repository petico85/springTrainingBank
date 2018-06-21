package bank;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaLogEntryDao {

    @PersistenceContext
    private EntityManager em;

    //ez ilyenkor mindig új tranzakció futását igényli, tehát megakadna a fő, ez akkor is lefut
    //tehát hiába dobtunk expectiont a felhasználó hozzáadásánál a log-olás meg fog történni
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message) {
        em.persist(new LogEntry(message));
    }
}
