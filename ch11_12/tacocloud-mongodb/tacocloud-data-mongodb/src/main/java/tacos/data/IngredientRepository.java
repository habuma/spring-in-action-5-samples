package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import tacos.Ingredient;

@CrossOrigin(origins="*")
public interface IngredientRepository 
         extends ReactiveCrudRepository<Ingredient, String> {

}