package tacos.data;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.User;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

  Mono<User> findByUsername(String username);
  
  Mono<User> findByEmail(String email);
  
}
