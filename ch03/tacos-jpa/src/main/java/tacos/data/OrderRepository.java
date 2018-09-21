package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
