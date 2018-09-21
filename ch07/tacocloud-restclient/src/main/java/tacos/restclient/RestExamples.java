package tacos.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

  public static void main(String[] args) {
    SpringApplication.run(RestExamples.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
    return args -> {
      log.info("----------------------- GET -------------------------");
      log.info("GETTING INGREDIENT BY IDE");
      log.info("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"));
      log.info("GETTING ALL INGREDIENTS");
      List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
      log.info("All ingredients:");
      for (Ingredient ingredient : ingredients) {
        log.info("   - " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      log.info("----------------------- PUT -------------------------");
      Ingredient before = tacoCloudClient.getIngredientById("LETC");
      log.info("BEFORE:  " + before);
      tacoCloudClient.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
      Ingredient after = tacoCloudClient.getIngredientById("LETC");
      log.info("AFTER:  " + after);
    };
  }
  
  @Bean
  public CommandLineRunner addAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      log.info("----------------------- POST -------------------------");
      Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
      Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
      log.info("AFTER=1:  " + chixAfter);
//      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
//      URI uri = tacoCloudClient.createIngredient(beefFajita);
//      log.info("AFTER-2:  " + uri);      
//      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
//      Ingredient shrimpAfter = tacoCloudClient.createIngredient(shrimp);
//      log.info("AFTER-3:  " + shrimpAfter);      
    };
  }

  
  @Bean
  public CommandLineRunner deleteAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      log.info("----------------------- DELETE -------------------------");
      // start by adding a few ingredients so that we can delete them later...
      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
      tacoCloudClient.createIngredient(beefFajita);
      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
      tacoCloudClient.createIngredient(shrimp);

      
      Ingredient before = tacoCloudClient.getIngredientById("CHIX");
      log.info("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      Ingredient after = tacoCloudClient.getIngredientById("CHIX");
      log.info("AFTER:  " + after);
      before = tacoCloudClient.getIngredientById("BFFJ");
      log.info("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      after = tacoCloudClient.getIngredientById("BFFJ");
      log.info("AFTER:  " + after);
      before = tacoCloudClient.getIngredientById("SHMP");
      log.info("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      after = tacoCloudClient.getIngredientById("SHMP");
      log.info("AFTER:  " + after);
    };
  }
  
  //
  // Traverson examples
  //
  
  @Bean
  public Traverson traverson() {
    Traverson traverson = new Traverson(
        URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
    return traverson;
  }
  
  @Bean
  public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient) {
    return args -> {
      Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
      log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
      for (Ingredient ingredient : ingredients) {
        log.info("   -  " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner traversonSaveIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      Ingredient pico = tacoCloudClient.addIngredient(
          new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
      List<Ingredient> allIngredients = tacoCloudClient.getAllIngredients();
      log.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
      for (Ingredient ingredient : allIngredients) {
        log.info("   -  " + ingredient);
      }
      tacoCloudClient.deleteIngredient(pico);
    };
  }
  
  @Bean
  public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
    return args -> {
      Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
      log.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
      for (Taco taco : recentTacos) {
        log.info("   -  " + taco);
      }
    };
  }

}
