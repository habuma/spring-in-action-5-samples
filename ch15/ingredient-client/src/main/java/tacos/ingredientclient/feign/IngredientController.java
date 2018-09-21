package tacos.ingredientclient.feign;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ingredients")
@Profile("feign")
@Slf4j
public class IngredientController {

  private IngredientClient client;

  public IngredientController(IngredientClient client) {
    this.client = client;
  }
  
  @GetMapping
  public String showAllIngredients(Model model) {
    log.info("Fetched all ingredients from a Feign client.");
    model.addAttribute("ingredients", client.getAllIngredients());
    return "ingredientList";
  }
  
  @GetMapping("/{id}")
  public String getIngredientById(@PathVariable("id") String id, Model model) {
    log.info("Fetched an ingredient from a Feign client.");
    model.addAttribute("ingredient", client.getIngredient(id));
    return "ingredientDetail";
  }
  
}
