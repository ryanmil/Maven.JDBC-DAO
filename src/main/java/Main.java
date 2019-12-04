import daos.CarDAO;
import models.Car;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Connector connector = new Connector("jdbc:postgresql://localhost/crudlab", "ryan", "");
        Connection connection =  connector.getConnection();
        CarDAO carDAO = new CarDAO(connection);

        Car myCar = carDAO.findById(1);
        List<Car> cars = carDAO.findAll();

        Car test = new Car(0, "yep", "oi", "yeh", 1990);
        carDAO.create(test);

        cars = carDAO.findAll();
        myCar = carDAO.findById(11);
        myCar.setModel("hoi");
        carDAO.update(myCar);

        cars = carDAO.findAll();

        carDAO.delete(11);

        cars = carDAO.findAll();
    }

}
