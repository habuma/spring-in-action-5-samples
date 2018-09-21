package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import tacos.Taco;


public interface TacoRepository 
         extends ReactiveCrudRepository<Taco, String> {

}
