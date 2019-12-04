package daos;
import models.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class CarDAOTest {
    CarDAO carDAO;

    @Before
    public void setup() {
        Connector connector = new Connector("jdbc:postgresql://localhost/crudlab", "ryan", "");
        Connection connection =  connector.getConnection();
        carDAO = new CarDAO(connection);
    }

    @Test
    public void findById() {
        Car myCar = carDAO.findById(1);
        Assert.assertEquals("BMW", myCar.getMake());

    }

    @Test
    public void findAll() {
        List<Car> cars = carDAO.findAll();

        Assert.assertEquals("BMW", cars.get(0).getMake());
        Assert.assertEquals("GMC", cars.get(2).getMake());
        Assert.assertEquals("Hyundai", cars.get(6).getMake());
    }

    @Test
    public void update() {
        Car test = new Car(0, "yep", "oi", "yeh", 1990);
        carDAO.create(test);

        List<Car> cars = carDAO.findAll();

        test = cars.get(cars.size() - 1);
        test.setModel("hoi");
        carDAO.update(test);

        Assert.assertEquals("yep", cars.get(cars.size() - 1).getMake());
        Assert.assertEquals("hoi", cars.get(cars.size() - 1).getModel());
        Assert.assertEquals("yeh", cars.get(cars.size() - 1).getColor());
        Assert.assertEquals(1990, cars.get(cars.size() - 1).getYear());

        carDAO.delete((int)cars.get(cars.size() - 1).getId());
    }

    @Test
    public void create() {
        Car test = new Car(0, "yep", "oi", "yeh", 1990);
        carDAO.create(test);

        List<Car> cars = carDAO.findAll();

        Assert.assertEquals("yep", cars.get(cars.size() - 1).getMake());
        Assert.assertEquals("oi", cars.get(cars.size() - 1).getModel());
        Assert.assertEquals("yeh", cars.get(cars.size() - 1).getColor());
        Assert.assertEquals(1990, cars.get(cars.size() - 1).getYear());

        carDAO.delete((int)cars.get(cars.size() - 1).getId());
    }

    @Test
    public void delete() {

        List<Car> cars1 = carDAO.findAll();

        Car test = new Car(0, "yep", "oi", "yeh", 1990);
        carDAO.create(test);

        List<Car> cars2 = carDAO.findAll();

        carDAO.delete((int)cars2.get(cars2.size() - 1).getId());

        List<Car> cars3 = carDAO.findAll();

        Assert.assertEquals(cars1.size(), cars3.size());
        
    }
}