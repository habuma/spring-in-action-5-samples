package tacos.data;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import tacos.Order;
import tacos.User;

public interface OrderRepository 
         extends ReactiveCrudRepository<Order, UUID> {

  Flux<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);

}
