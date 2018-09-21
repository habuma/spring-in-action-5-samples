package tacos.data;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.User;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

  @AllowFiltering
  Mono<User> findByUsername(String username);
  
  @AllowFiltering
  Mono<User> findByEmail(String email);
  
}
