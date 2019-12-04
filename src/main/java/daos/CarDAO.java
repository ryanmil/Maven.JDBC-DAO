package daos;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO<Car> {

    private Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    private Car getFromResultSet(ResultSet rs) {
         try {
             return new Car(
                     rs.getLong("id"),
                     rs.getString("make"),
                     rs.getString("model"),
                     rs.getString("color"),
                     rs.getInt("year")
             );
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }

    @Override
    public Car findById(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car WHERE id=" + id);
            if(rs.next())
            {
                return getFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car");

            ArrayList<Car> cars = new ArrayList<>();

            while(rs.next())
            {
                Car car = getFromResultSet(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(Car dto) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE car SET make=?, model=?, year=?, color=? WHERE id=?");
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            ps.setLong(5, dto.getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean create(Car dto) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO car (make, model, year, color) VALUES (?, ?, ?, ?)");
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(int id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM car WHERE id=" + id);
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
