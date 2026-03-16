package lesson5;
import java.util.*;

public interface OrderRepository {

    void save(Order order);

    List<Order> findAll();
}