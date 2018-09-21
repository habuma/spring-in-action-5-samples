package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {
  PaymentMethod findByUserId(Long userId);
}
