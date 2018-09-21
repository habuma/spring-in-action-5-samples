package tacos.web.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="*")
public class IngredientController {

  private IngredientRepository repo;

  @Autowired
  public IngredientController(IngredientRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public Flux<Ingredient> allIngredients() {
    return repo.findAll();
  }
  
  @GetMapping("/{id}")
  public Mono<Ingredient> byId(@PathVariable String id) {
    return repo.findById(id);
  }
  
  @PutMapping("/{id}")
  public void updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient) {
    if (!ingredient.getId().equals(id)) {
      throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path.");
    }
    repo.save(ingredient);
  }
  
  @PostMapping
  public Mono<ResponseEntity<Ingredient>> postIngredient(@RequestBody Mono<Ingredient> ingredient) {
    return ingredient
        .flatMap(repo::save)
        .map(i -> {
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(URI.create("http://localhost:8080/ingredients/" + i.getId()));
          return new ResponseEntity<Ingredient>(i, headers, HttpStatus.CREATED);          
        });
  }
  
  @DeleteMapping("/{id}")
  public void deleteIngredient(@PathVariable String id) {
    repo.deleteById(id);
  }
  
}
