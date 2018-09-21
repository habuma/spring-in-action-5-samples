package tacos.web.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import tacos.Ingredient.Type;
import tacos.IngredientUDT;
import tacos.Taco;
import tacos.data.TacoRepository;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers=DesignTacoController.class)
@ContextConfiguration(classes=DesignTacoController.class)
public class DesignTacoControllerWebTest {

  @Autowired
  private WebTestClient testClient;
  
  @MockBean
  private TacoRepository tacoRepo;

  @Test
  public void shouldReturnRecentTacos() throws IOException {
    Taco[] tacos = {
        testTaco(1L), testTaco(2L), testTaco(3L), testTaco(4L),
        testTaco(5L), testTaco(6L), testTaco(7L), testTaco(8L),
        testTaco(9L), testTaco(10L), testTaco(11L), testTaco(12L)};

    Mockito.when(tacoRepo.findAll())
      .thenReturn(Flux.fromArray(tacos));
    
    testClient.get().uri("/design/recent")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isOk()
      .expectBodyList(Taco.class).contains(tacos);
  }
  
  private Taco testTaco(Long number) {
    Taco taco = new Taco();
    taco.setId(UUID.randomUUID());
    taco.setName("Taco " + number);
    List<IngredientUDT> ingredients = new ArrayList<>();
    ingredients.add(
        new IngredientUDT("Ingredient A", Type.WRAP));
    ingredients.add(
        new IngredientUDT("Ingredient B", Type.PROTEIN));
    taco.setIngredients(ingredients);
    return taco;
  }
}
