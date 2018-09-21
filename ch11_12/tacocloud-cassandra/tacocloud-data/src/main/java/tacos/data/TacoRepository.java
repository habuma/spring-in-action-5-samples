package tacos.data;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import tacos.Taco;


public interface TacoRepository 
         extends ReactiveCrudRepository<Taco, UUID> {

}
