package bank;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {


    //CrudRepository nem tartalmaz select függvényt, de adhatunk hozzá
    //összerakáshoz: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Car> findByBrand(String brand); //nem kell új függvényt írni feltétlen, elég jól elnevezni a SpringData meg megérti
}
