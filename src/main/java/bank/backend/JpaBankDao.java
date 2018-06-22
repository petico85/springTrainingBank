package bank.backend;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //van még egyszerűbb út, most arra váltunk
public class JpaBankDao implements BankDao {

    @PersistenceContext //ezzel injektál egy entitymanagert - Ez field injection.Nem unit tesztelhető, de Dao-t nem is kell
    private EntityManager em;

    //ami ettőllefut az mind egy tranzakcióban lesz. tehát amit ez hív az is és visszatérésig
    //transactional antonáció paraméterei.
    //  - propagácio: megkövetelt, itt minden egy tranzakcióban lefut alapértelmezett működés
    //    propagation = Propagation.REQUIRES_NEW - ezzel megmondjuk hogy ez mindenképp új külön tranzakció legyen.
    //  - izolációs szint: párhuzamosan futó tranzakcióknál problémák. dirtyread(egyik tranzakció látja a másik módosítását)
    //    nonrepetable read (tranzakció elején beolvasunk valamit, és a végén is, de nem ugyanaz jön vissza. pl.: Közbe más insertelt )
    //    phantomread (eltűnik az olvasott adat feloolgozás előtt)
    //    ilyen problémák ellen lehet álítani az izolációs szintet
    //    readuncomitid
    //    readcomited
    //    repetableread
    //  - visszagörgetési szabályok
    //    Springben a rollbacket kivétel dobással lehet előifézni. És meg lehet mondani, hogy
    //    melyik kivételre legyen rollback és melyikekre ne.
    //  - timeout
    //    az adatbázis művelet nem végződik megadott idő alatt akkor dobjon timeout expection-t
    //  - readonly
    //  - a selectet elcacheli így ha kiadom mégegyszer a tranzakción belül ugyanazt a selectet adja vissza újra
    //@Transactional //kikommenteltük, mert a log miatt már az BankService.addClient-ből indul a tranzakció //innen veszi észre hogy ebből lesz egy insert parancs
    @Override
    public void addClient(Client client) {
        em.persist(client); //JPA megnézi, hogy clientet kell menteni. MIlyen mezői vannak? milyen osztálynak van Table annotációja. Összerakja az sql-et
    }

    @Override
    public List<Client> listClients() {
        return em.createQuery("select distinct c from Client c left join fetch c.addresses", Client.class).getResultList();
        //elég a c osztály addresses attributumát is lekérni és már joinol is
        //file  --> Project structure --> + JPA --> kiválasztod a projectet és ok
        //sql lekérdezést készít az osztály szerkezete alapján és az eredményt is ennek megfelelően adja vissza
        //em.createNativeQuery() itt lehetne rendes sql-t adni, de az adatbázis függetlenség ugrik
        //sok értelme nincs, akkor inkább jbdc
    }
}
