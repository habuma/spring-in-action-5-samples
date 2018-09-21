package tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;
import tacos.User;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

  // tag::findByUser_paged[]
  List<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);
  // end::findByUser_paged[]

  /*
  // tag::findByUser[]
  List<Order> findByUserOrderByPlacedAtDesc(User user);
  // end::findByUser[]
   */

}
