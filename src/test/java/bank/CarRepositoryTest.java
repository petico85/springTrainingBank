package bank;

import bank.backend.Car;
import bank.backend.CarRepository;
import bank.backend.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
@Sql(statements = "delete from cars")
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void test() {
        carRepository.save(new Car("Ford", "Mondeo"));
        carRepository.save(new Car("Opel", "Astra"));

        List<Car> cars = carRepository.findByBrand("Ford");
        assertEquals(1, cars.size());
        assertEquals("Mondeo", cars.get(0).type);

    }
}
