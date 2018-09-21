package tacos.data;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.PaymentMethod;

public interface PaymentMethodRepository 
         extends ReactiveCrudRepository<PaymentMethod, UUID> {
  Mono<PaymentMethod> findByUserUsername(String username);
}
