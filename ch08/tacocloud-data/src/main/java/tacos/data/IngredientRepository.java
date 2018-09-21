package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import tacos.Ingredient;

@CrossOrigin(origins="*")
public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
