package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.PaymentMethod;

public interface PaymentMethodRepository 
         extends ReactiveCrudRepository<PaymentMethod, String> {
  Mono<PaymentMethod> findByUserId(String userId);
}
